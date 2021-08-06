package entity;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "articolo_has_tag")
@EntityListeners(AuditingEntityListener.class)
public class articoloTag {
	
	@EmbeddedId
	private EmbeddableIdTags id;
	
	
    @ManyToOne
    @MapsId("Articolo_id")
    @JoinColumn(name = "Articolo_id")
    private Articolo art;

    @ManyToOne
    @MapsId("Tag_id")
    @JoinColumn(name = "Tag_id")
    private Tag tag;

    
    
    public articoloTag() {}
    
	public articoloTag(Articolo art, Tag tag) {
		this.id = new EmbeddableIdTags(art.getId(), tag.getId());
		this.art = art;
		this.tag = tag;
	}


	
	public EmbeddableIdTags getId() { return id; }
	public void setId(EmbeddableIdTags id) { this.id = id; }

	public Articolo getArt() { return art; }
	public void setArt(Articolo art) { this.art = art; }

	public Tag getTag() { return tag; }
	public void setTag(Tag tag) { this.tag = tag; }



	@Override
	public String toString() { return "articoloTag [id=" + id + ", art=" + art + ", tag=" + tag + "]"; }
    

}

package entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class EmbeddableIdTags implements Serializable {  
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	@Column(name = "Articolo_id")
	private int art;
	
	@Column(name = "Tag_id")
	private int tag;

	
	
	public EmbeddableIdTags() {}
	public EmbeddableIdTags(int art, int tag) {
		this.art = art;
		this.tag = tag;
	}
	

	public int getArt() {
		return art;
	}

	public void setArt(int art) {
		this.art = art;
	}

	public int getTag() {
		return tag;
	}

	public void setTag(int tag) {
		this.tag = tag;
	}
	
	
	@Override
	public String toString() {
		return "EmbeddableIdTags [art=" + art + ", tag=" + tag + "]";
	}
    
}

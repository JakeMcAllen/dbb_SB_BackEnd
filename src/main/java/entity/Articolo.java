package entity;

import java.io.Serializable;
import java.sql.Time;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import com.sun.istack.NotNull;

@Entity
@Table(name = "articolo")
@EntityListeners(AuditingEntityListener.class)
public class Articolo implements Serializable {
	
	
	private static final long serialVersionUID = 1L;
	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "titolo")
	private String Titolo;
	
	@Column(name = "article_text")
	private String TextContext;
	
	@Column(name = "pubblication_date", nullable = false, updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@NotNull
	private Date pubblication;
	
	@Column(name = "pubblication_hour", nullable = false, updatable = false)
	private Time hour;
	
	@OneToMany(mappedBy = "tag", fetch=FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.REFRESH, CascadeType.PERSIST})
	private List<Tag> Tags;
	
	@OneToMany(mappedBy = "articolo", fetch=FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.REFRESH, CascadeType.PERSIST})
	private List<immagine> photos;
	
	@OneToMany(mappedBy = "art", fetch=FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.REFRESH, CascadeType.PERSIST})
	private List<commento> comments;
	
	@Column(name = "Likes")
	private int likes;
	
	@Column(name = "article_layout", updatable = false)
	private int ArticleLayout;
	
	

	public Articolo() {}

	public Articolo(int id, String titolo, String textContext, Date pubblication, Time hour, List<Tag> tags,
			List<immagine> photos, List<commento> comments, int likes, int layout) {
		super();
		this.id = id;
		Titolo = titolo;
		TextContext = textContext;
		this.pubblication = pubblication;
		this.hour = hour;
		Tags = tags;
		this.photos = photos;
		this.comments = comments;
		this.likes = likes;
		this.ArticleLayout = layout;
	}




	public int getId() { return id; }
	public void setId(int id) { this.id = id; }

	public String getTitolo() { return Titolo; }
	public void setTitolo(String titolo) { Titolo = titolo; }

	public String getTextContext() { return TextContext; }
	public void setTextContext(String textContext) { TextContext = textContext; }

	public Date getPubblication() { return pubblication; }
	public void setPubblication(Date pubblication) { this.pubblication = pubblication; }

	public List<Tag> getTags() { return Tags; }
	public void setTags(List<Tag> tags) { Tags = tags; }

	public List<immagine> getPhotos() { return photos; }
	public void setPhotos(List<immagine> photos) { this.photos = photos; }

	public int getLike() { return likes; }
	public void setLike(int like) { this.likes = like; }

	public int getLayout() { return ArticleLayout; }
	public void setLayout(int layout) { this.ArticleLayout = layout; }

	public Time getHour() { return hour; }
	public void setHour(Time hour) { this.hour = hour; }


	
	@Override
	public String toString() {
		return "Articolo [id=" + id + ", Titolo=" + Titolo + ", TextContext=" + TextContext + ", pubblication="
				+ pubblication + ", hour=" + hour + ", Tags=" + Tags + ", photos=" + photos + ", comments=" + comments
				+ ", likes=" + likes + ", layout=" + ArticleLayout + "]";
	}


}
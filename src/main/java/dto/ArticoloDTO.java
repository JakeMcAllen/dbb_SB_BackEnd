package dto;

import java.sql.Time;
import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonRootName;

import entity.Tag;
import entity.immagine;



@XmlRootElement(name = "Articolo")
@JsonRootName("Articolo")
public class ArticoloDTO {
	
	private int id_art;
	private String Titolo_art;
	private String TextContext_art;
	public Date pubblication_art;
	public Time hour_art;
	public List<Tag> Tags_art;
	public List<immagine> photos_art;
	public int like_art;
	public int layout_art;

	
	
	public ArticoloDTO() {}	

	public ArticoloDTO(int id, String titolo, String textContext, Date pubblication, Time hour, List<Tag> tags,
			List<immagine> photos, int like, int layout) {
		super();
		this.id_art = id;
		Titolo_art = titolo;
		TextContext_art = textContext;
		this.pubblication_art = pubblication;
		this.hour_art = hour;
		Tags_art = tags;
		this.photos_art = photos;
		this.like_art = like;
		this.layout_art = layout;
	}




	public int getId() { return id_art; }
	public void setId(int id) { this.id_art = id; }

	public String getTitolo() { return Titolo_art; }
	public void setTitolo(String titolo) { Titolo_art = titolo; }

	public String getTextContext() { return TextContext_art; }
	public void setTextContext(String textContext) { TextContext_art = textContext; }

	public Date getPubblication() { return pubblication_art; }
	public void setPubblication(Date pubblication) { this.pubblication_art = pubblication; }

	public List<Tag> getTags() { return Tags_art; }
	public void setTags(List<Tag> tags) { Tags_art = tags; }

	public List<immagine> getPhotos() { return photos_art; }
	public void setPhotos(List<immagine> photos) { this.photos_art = photos; }

	public int getLike() { return like_art; }
	public void setLike(int like) { this.like_art = like; }

	public int getLayout() { return layout_art; }
	public void setLayout(int layout) { this.layout_art = layout; }

	public Time getHour() { return hour_art; }
	public void setHour(Time hour) { this.hour_art = hour; }



	@Override
	public String toString() {
		return "ArticoloDTO [id=" + id_art + ", Titolo=" + Titolo_art + ", TextContext=" + TextContext_art + ", pubblication="
				+ pubblication_art + ", hour=" + hour_art + ", Tags=" + Tags_art + ", photos=" + photos_art + ", like=" + like_art
				+ ", layout=" + layout_art + "]";
	}

	
}
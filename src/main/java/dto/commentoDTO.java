package dto;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonRootName;

@XmlRootElement(name = "commento")
@JsonRootName("commento")
public class commentoDTO {

	
	private int id;
	private String testo;
	private Date timestamp;
	private int art;
	private String u;
	private int idU;
	
	
	public commentoDTO() {}

	public commentoDTO(int id, String testo, Date timestamp, int art, String u, int idU ) {
		this.id = id;
		this.testo = testo;
		this.timestamp = timestamp;
		this.art = art;
		this.u = u;
		this.idU = idU;
	}

	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTesto() {
		return testo;
	}

	public void setTesto(String testo) {
		this.testo = testo;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public int getArt() {
		return art;
	}

	public void setArt(int art) {
		this.art = art;
	}
	
	public String getU() {
		return u;
	}

	public void setU(String u) {
		this.u = u;
	}

	public int getIdU() {
		return idU;
	}

	public void setIdU(int idU) {
		this.idU = idU;
	}

	
	
	@Override
	public String toString() {
		return "commentoDTO [id=" + id + ", testo=" + testo + ", timestamp=" + timestamp + ", art=" + art + ", u=" + u
				+ ", idU=" + idU + "]";
	}
	
}

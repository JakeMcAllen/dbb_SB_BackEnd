package dto;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonRootName;


@XmlRootElement(name = "Domada")
@JsonRootName("Domanda")
public class DomandaDTO {
	
	private int id;
	private String titolo;
	private String risposta;
	private Date time; 
	
	
	
	public DomandaDTO() {}

	public DomandaDTO(int id, String titolo, String risposta, Date time) {
		this.id = id;
		this.titolo = titolo;
		this.risposta = risposta;
		this.time = time;
	}

	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitolo() {
		return titolo;
	}

	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}

	public String getRisposta() {
		return risposta;
	}

	public void setRisposta(String risposta) {
		this.risposta = risposta;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	
	
	@Override
	public String toString() {
		return "DomandaDTO [id=" + id + ", titolo=" + titolo + ", risposta=" + risposta + ", time=" + time + "]";
	}
	
}

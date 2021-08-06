package entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "domanda")
public class Domanda {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "Titolo")
	private String titolo;
	
	@Column(name = "Risposta")
	private String risposta;
	
	@Column(name = "timestamp", nullable = false, updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@CreatedDate
	@DateTimeFormat(pattern = "yyyy-MM-gg")
	private Date tm; 
	
	
	
	public Domanda() {}

	public Domanda(int id, String titolo, String risposta, Date time) {
		this.id = id;
		this.titolo = titolo;
		this.risposta = risposta;
		this.tm = time;
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
		return tm;
	}

	public void setTime(Date time) {
		this.tm = time;
	}

	
	
	@Override
	public String toString() {
		return "Domanda [id=" + id + ", titolo=" + titolo + ", risposta=" + risposta + ", time=" + tm + "]";
	}
	
}

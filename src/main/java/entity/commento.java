package entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "commento")
public class commento {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String testo;
	
	@Column(nullable = false, updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@CreatedDate
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date timestamp;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "Articolo_id", nullable = false)
	private Articolo art;
	
	@ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "Utente_id", nullable = false)
	private User u;
	
	
	
	public commento() {}

	public commento(int id, String testo, Date timestamp, Articolo art, User u) {
		this.id = id;
		this.testo = testo;
		this.timestamp = timestamp;
		this.art = art;
		this.u = u;
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


	public Articolo getArt() {
		return art;
	}


	public void setArt(Articolo art) {
		this.art = art;
	}


	public User getU() {
		return u;
	}


	public void setU(User u) {
		this.u = u;
	}


	
	@Override
	public String toString() {
		return "commento [id=" + id + ", testo=" + testo + ", timestamp=" + timestamp + ", art=" + art + ", u=" + u + "]";
	}
	
}

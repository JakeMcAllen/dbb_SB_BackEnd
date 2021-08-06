package dto;

import java.util.Arrays;

import javax.xml.bind.annotation.XmlRootElement;


import com.fasterxml.jackson.annotation.JsonRootName;


@XmlRootElement(name = "addNewArt")
@JsonRootName("addNewArt")
public class addNewArt {
	
	// articolo
	private String titolo;
	private String testo;
	private String pageformat;
	private String img1;
	private String descimg1;
	
	// immagini extra	
	private String[] lstimg;
	private int val;
	
	// user 
	private String username;
	private String password;
	
	//tags
	private String[] tags;
	
	// date
	private String date;
	private String hour;
	
	
	public addNewArt() {}



	public addNewArt(String titolo, String testo, String pageformat, String img1, String descimg1, String[] lstimg,
			int val, String username, String password, String[] tags, String date, String hour) {
		this.titolo = titolo;
		this.testo = testo;
		this.pageformat = pageformat;
		this.img1 = img1;
		this.descimg1 = descimg1;
		this.lstimg = lstimg;
		this.val = val;
		this.username = username;
		this.password = password;
		this.tags = tags;
		this.date = date;
		this.hour = hour;
	}

	

	public String getTitolo() {
		return titolo;
	}


	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}


	public String getTesto() {
		return testo;
	}


	public void setTesto(String testo) {
		this.testo = testo;
	}
	

	public String getPageformat() {
		return pageformat;
	}

	
	public void setPageformat(String pageformat) {
		this.pageformat = pageformat;
	}
	

	public String getImg1() {
		return img1;
	}


	public void setImg1(String img1) {
		this.img1 = img1;
	}


	public String getDescimg1() {
		return descimg1;
	}


	public void setDescimg1(String descImg1) {
		this.descimg1 = descImg1;
	}


	public String[] getLstimg() {
		return lstimg;
	}


	public void setLstimg(String[] lstImg) {
		this.lstimg = lstImg;
	}


	public int getVal() {
		return val;
	}


	public void setnVal(int val) {
		this.val = val;
	}

	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public String[] getTags() {
		return tags;
	}

	public void setTags(String[] tags) {
		this.tags = tags;
	}

	public void setVal(int val) {
		this.val = val;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
	
	public String getHour() {
		return hour;
	}

	public void setHour(String hour) {
		this.hour = hour;
	}



	@Override
	public String toString() {
		return "addNewArt [titolo=" + titolo + ", testo=" + testo + ", pageformat=" + pageformat + ", img1=" + img1
				+ ", descimg1=" + descimg1 + ", lstimg=" + titolo + ", val=" + val + ", username="
				+ username + ", password=" + password + ", tags=" + Arrays.toString(tags) + ", date=" + date + ", hour="
				+ hour + "]";
	}


}

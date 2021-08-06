package dto;

import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonRootName;

@XmlRootElement(name = "Immagine")
@JsonRootName("Immagine")
public class ImmagineDTO {

	
	
	private int id;
	
	private String descrizione;
	
	private byte[] img;
	
	private int heigth;
	
	private int width;
	
	private boolean copertina;
	
	private int articolo;
	
	
	
	public ImmagineDTO() {}

	public ImmagineDTO(int id, String descrizione, byte[] img, int length, int width, boolean copertina, int articolo) {
		this.id = id;
		this.descrizione = descrizione;
		this.img = img;
		this.heigth = length;
		this.width = width;
		this.copertina = copertina;
		this.articolo = articolo;
	}


	
	public int getId() { return id; }
	public void setId(int id) { this.id = id; }

	public String getDescrizione() { return descrizione; }
	public void setDescrizione(String descrizione) { this.descrizione = descrizione; }

	public byte[] getImg() { return img; }
	public void setImg(byte[] img) { this.img = img; }

	public int getLength() { return heigth; }
	public void setLength(int length) { this.heigth = length; }

	public int getWidth() { return width; }
	public void setWidth(int width) { this.width = width; }

	public boolean isCopertina() { return copertina; }
	public void setCopertina(boolean copertina) { this.copertina = copertina; }

	public int getArticolo() { return articolo; }
	public void setArticolo(int articolo) { this.articolo = articolo; }


	
	@Override
	public String toString() {
		return "ImmagineDTO [id=" + id + ", descrizione=" + descrizione + ", img=" + img + ", length=" + heigth
				+ ", width=" + width + ", copertina=" + copertina + ", articolo=" + articolo + "]";
	}
	
	
	
}

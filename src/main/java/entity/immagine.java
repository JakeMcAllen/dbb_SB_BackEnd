package entity;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.imgscalr.Scalr;

@Entity
@Table(name = "immagine")
public class immagine {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String descrizione;
	
	@Lob
    @Column(name="img", nullable=false, columnDefinition="mediumblob")
	private byte[] img;
	
	// img dim
	private int heigth;
	private int width;
	
	private boolean copertina;
	
	@ManyToOne(fetch=FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.REFRESH, CascadeType.PERSIST})
	@JoinColumn(name="Articolo_id")
	private Articolo articolo;
	

	
	public immagine() {}


	public immagine(int id, String descrizione, byte[] img, int heigth, int width, boolean copertina, Articolo articolo) {
		this.id = id;
		this.descrizione = descrizione;
		this.img = img;
		this.heigth = heigth;
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


	public int getHeigth() { return heigth; }
	public void setHeigth(int length) { this.heigth = length; }


	public int getWidth() { return width; }
	public void setWidth(int width) { this.width = width; }


	public boolean isCopertina() { return copertina; }
	public void setCopertina(boolean copertina) { this.copertina = copertina; }


	public Articolo getArticolo() { return articolo; }
	public void setArticolo(Articolo articolo) { this.articolo = articolo; }
	
	
	
	/**
	 * Converte un immagine in una risoluzione minore
	 * 
	 * @param originalImage (byte[]) Immagine originale da ridimensionare
	 * @param targetWidth (int) Dimensione orrizontale della nuova immagine
	 * 
	 * @return (byte[]) Immagine finale dell'immagine 
	 * @throws Exception
	 */
	public static byte[] simpleImageScaler(byte[] originalImage, int targetWidth) throws Exception {
        // convert byte[] back to a BufferedImage
        InputStream is = new ByteArrayInputStream(originalImage);
        BufferedImage newBi = ImageIO.read(is);
		
		
		BufferedImage bi = Scalr.resize(newBi, targetWidth);
		
		
        // convert BufferedImage to byte[]
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(bi, "png", baos);
        byte[] finalImage = baos.toByteArray();
		
		return finalImage;
	}
	


	@Override
	public String toString() {
		return "immagine [id=" + id + "img:  " + img.toString().substring(0, 20) +", descrizione=" + descrizione + ", length=" + heigth
				+ ", width=" + width + ", copertina=" + copertina + "]";
	}

	
	
}

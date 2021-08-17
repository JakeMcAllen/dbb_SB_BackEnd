package services;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dto.addNewArt;
import dto.addNewImgs;
import entity.Articolo;
import entity.Domanda;
import entity.Tag;
import entity.articoloTag;
import entity.immagine;
import exception.imgNotSaved;
import repository.ArticoloQuery;
import repository.ArticoloTagQuery;
import repository.DomandaQuery;
import repository.ImmaginiQuery;
import repository.TagQuery;



@Service
@Transactional
public class addService {
	
	
	@Autowired
	private ImmaginiQuery img;
	
	@Autowired
	private ArticoloQuery art;
	
	@Autowired
	private DomandaQuery dom;

	@Autowired
	private TagQuery tg;
	
	@Autowired
	private ArticoloTagQuery atq;
	
	// Numero dei tentativi di salvataggio di ogni singola immagine
	private int numTryImgSaving = 4;
	
	
	
	
	
	//   ________________________________________________________________________________
	//  /                                                                                \
	// |------------------------------------ ARTICOLO ------------------------------------|
	//  \________________________________________________________________________________/
	
	/**
	 * Aggiunge un nuovo articolo con immagini allegate al database
	 * 
	 * @param ana (addNewArt) tutte le informazioni da aggiungere
	 * @throws imgNotSaved 
	 */
	@Transactional
	public void addArt(addNewArt ana) throws imgNotSaved {
		
		
		
		// articolo
		Articolo a = new Articolo();
		a.setTitolo( ana.getTitolo() );
		a.setTextContext( ana.getTesto() );
		a.setLayout( ( ana.getPageformat().equals("F1") ) ? 1 : 2 );
		try { a.setPubblication( new SimpleDateFormat("yyyy-MM-dd").parse( ana.getDate() ) ); } 
		catch (ParseException e1) { e1.printStackTrace(); }
		a.setLike(0);
		
		art.save( a );
		

		
		// save tags
		for (String s : ana.getTags()) {
			Tag t = new Tag();
			t.setTag( s );
			tg.save( t );
			
			
			articoloTag at = new articoloTag();
			at.setTag( t );
			at.setArt( art.getLastArt() );
			atq.save( at );
			
		}
		
		
		
		// immagine di copertina
		immagine ic = new immagine();
		String base64Image = ana.getImg1().split(",")[1];
		byte[] imageBytes = javax.xml.bind.DatatypeConverter.parseBase64Binary(base64Image);

		try { 
			
			BufferedImage imgBI = ImageIO.read(new ByteArrayInputStream(imageBytes));
			ic.setHeigth( imgBI.getHeight() );
			ic.setWidth( imgBI.getWidth() );
		} catch (IOException e) { e.printStackTrace(); }		
		
		ic.setImg( imageBytes );
		ic.setDescrizione( ana.getDescimg1() );
		ic.setCopertina(true);
		ic.setArticolo( art.getLastArt() );
		
		saveImg(ic);
		
		
		
		// immagini in più
		for (int i = 0; i < ana.getVal(); i++) {
			
			immagine is = new immagine();
			String base64ImageLst = ana.getLstimg()[i].split(",")[1];
			byte[] imageBytesItm = javax.xml.bind.DatatypeConverter.parseBase64Binary(base64ImageLst);

			try { 
				
				BufferedImage imgBI = ImageIO.read(new ByteArrayInputStream( imageBytesItm ));
				is.setHeigth( imgBI.getHeight() );
				is.setWidth( imgBI.getWidth() );
			} catch (IOException e) { e.printStackTrace(); }
			
			is.setImg( imageBytesItm );
			is.setDescrizione( "Immagine secodaria articolo n°" + i + " ." );
			is.setCopertina( false );
			is.setArticolo( art.getLastArt() );
			
			saveImg(is);
		}
	}
	
	
	
	
	//   ________________________________________________________________________________
	//  /                                                                                \
	// |------------------------------------ IMMAGES -------------------------------------|
	//  \________________________________________________________________________________/
	
	/**
	 * Aggiunge una nuova immagine allegate al database
	 * 
	 * @param ana (addNewArt) tutte le informazioni da aggiungere
	 * @throws imgNotSaved 
	 */
	@Transactional
	public void addImgs(addNewImgs ana) throws imgNotSaved {
		
		// immagini in più
		for (int i = 0; i < ana.getVal(); i++) {
			
			immagine is = new immagine();
			String base64ImageLst = ana.getLstimg()[i].split(",")[1];
			byte[] imageBytesItm = javax.xml.bind.DatatypeConverter.parseBase64Binary(base64ImageLst);
			
			try {
				BufferedImage imgBI = ImageIO.read(new ByteArrayInputStream( imageBytesItm ));
				is.setHeigth( imgBI.getHeight() );
				is.setWidth( imgBI.getWidth() );
			
			} catch (IOException e) { e.printStackTrace(); }
			
			
			is.setImg( imageBytesItm );
			is.setDescrizione( "Immagine secodaria articolo n°" + i + " ." );
			is.setCopertina( (ana.getCop() == 1) ? true : false );
			is.setArticolo( art.getById( ana.getId() ) );
			
			saveImg(is);
		}		
	}
	
	
	
	/**
	 * Metodo interno alla classe per tentare il salvataggio di un immagine.
	 * Se il database rifiuta di salvare la stessa, viene applicata una dimuzione della propria risoluzione. 
	 * In fine viene effettuato un nuovo tentativo di upload.
	 * Il metodo tenta di diminuire per n volte la risoluzione dell'immagine e poi uplodarla sul db
	 * 
	 * 
	 * @param ic (immagine) 
	 * @throws imgNotSaved (Exception) Viene lanciata quando si tenta per n volte il salvataggio di un immagine
	 */
	private void saveImg(immagine ic) throws imgNotSaved {
		boolean imgSvd = false;
		
		for (int i=0; i < numTryImgSaving; i++ ) {
			try {
				// try to save img
				img.save( ic );
				imgSvd = true;
				break;
				
			} catch (Exception e) {
				// img downgrade
				try {
					
					int width = (int) ( ic.getWidth()  * 0.9 );
					ic.setHeigth( (int) ( ic.getHeigth() * 0.9 ) );
					ic.setWidth( width );
					
					ic.setImg( immagine.simpleImageScaler( ic.getImg() , width) );
				} catch (Exception e1) {}
				
			}
		}
		
		if (!imgSvd) throw new imgNotSaved("Immagine non salvata");
	}

	
	
	

	//   ________________________________________________________________________________
	//  /                                                                                \
	// |------------------------------------ DOMANDE -------------------------------------|
	//  \________________________________________________________________________________/	
	
	/**
	 * Salva una domanda su database
	 * 
	 * @param d (Domanda) domanda da salvare
	 */
	public void rispToDom(Domanda d) throws IllegalArgumentException {
		dom.save( d );	
	}
	
	
	/**
	 * Restituisce tutte le domande vuote che si trovano nel database
	 * 
	 * @return (List<Domanda>) lista delle domande da rispondere
	 */
	public List<Domanda> getDomandaWhite() {

		List<Domanda> listDom = dom.findAll();
		List<Domanda> listDomRt = new ArrayList<Domanda> ();
		
		for (int i = 0; i < listDom.size(); i++) {
			if ( listDom.get(i).getRisposta().isEmpty() || listDom.get(i).getRisposta() == null )
				listDomRt.add(listDom.get(i));
		}
		
		return listDomRt;		
	}
	
	

	
	
}

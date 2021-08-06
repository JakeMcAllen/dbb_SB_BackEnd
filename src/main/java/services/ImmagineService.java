package services;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import entity.Articolo;
import entity.immagine;
import repository.ArticoloQuery;
import repository.ImmaginiQuery;

@Service
@Transactional
public class ImmagineService {

	@Autowired
	private ImmaginiQuery immaginiQuery;
	
	@Autowired
    private ArticoloQuery articoloRepository;
	
	
	/**
	 * Restituisce l'immagine di copertina di un articolo conservata nel database
	 * 
	 * @param idArt (int) id dell'articolo
	 * @return L'immagine di copertina dell'articolo
	 */
	public immagine getImgCopertina(int idArt) { return immaginiQuery.lastImgArt(idArt); }


	/**
	 * Restituisce l'ultima immagine di copertina pubblicata sul database
	 * 
	 * @return (immagine) Oggetto immagine corrispondente all'ultimo articolo
	 */
	@SuppressWarnings("deprecation")
	public immagine getLastImgCopertina() { 
		
		List<Articolo> lstArt = articoloRepository.findAll();

		Articolo art = null;
		for (Articolo a : lstArt) {
			
			
		    Date d = a.getPubblication();
		    d.setHours( Integer.parseInt( a.getHour().toString().split(":")[0] ) );
		    d.setMinutes( Integer.parseInt( a.getHour().toString().split(":")[1] ) );
		    		    
		    if ( !a.getPubblication().before( new Date() ) || d.compareTo( new Date() ) > 0 ) continue;
		    
		    
			if (art == null) 
				art = a;
			else 
				if ( art.getPubblication().before( d ) ) art = a;
			
		}
		
		
		return immaginiQuery.getImgByArtId(art.getId()).get(0); 
	}
	
	
	/**
	 * Restituisce la lista delle immagini secondarie di un articolo
	 * 
	 * @param id (int) id dell'articolo
	 * @return (List<immagine>) lista delle immagini
	 */
	public List<Integer> getLstImgByArtId(int id) { return immaginiQuery.getLstImgByArtId( id ); }


	/**
	 * Select an img by id of immage dal database
	 * 
	 * @param idImg (int) of img
	 * @return (immagine) immagine richiesta
	 */
	public immagine getImgById(int idImg) {
		List<immagine> img = immaginiQuery
						.findAll()
						.stream()
						.filter( i -> i.getId() == idImg )
						.collect(Collectors.toList());
		
		System.out.println("\n\nImmagine: ");
		
		return img.get(0);
	}
	
	
}

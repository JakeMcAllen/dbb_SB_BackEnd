package services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import entity.Articolo;
import repository.ArticoloQuery;

@Service
@Transactional
public class ArticoloService {

	@Autowired
    private ArticoloQuery articoloRepository;
	
	
	
	/**
	 * Permette l'aggiunta di un nuovo articolo al database
	 * Se l'articolo inserito ha un id, un titolo e un testo uguali ad un altro articolo viene fermata l'aggiunta dell'articolo.
	 * 
	 * @param a (Articolo) Il nuovo articolo da aggiungere
	 */
	public void registraArticolo(Articolo a) { 
		
		List<Articolo> lstArt = articoloRepository.findAll();
		for (int i = 0; i < lstArt.size(); i++) {
			
			if ( lstArt.get(i).getId() == a.getId()  && 
					lstArt.get(i).getTitolo().equals(a.getTitolo()) && 
					lstArt.get(i).getTextContext().equals(a.getTextContext()) ) 
			{
				throw new RuntimeException("Articolo gi� presente");
			}
			
		}
		
		
		articoloRepository.save(a);
	}
	
	
	
	/**
	 * Restituisce l'articolo con l'id specificato nel database
	 * 
	 * @param id (int) id dell'articolo che si cerca
	 * @return (Articolo) l'articolo corrispondente
	 */
	public Articolo getArticoloById(int id) { return articoloRepository.findById(id).get(); }
	
	
	
	/**
	 * Cancella un articolo sapendo l'id dello stesso
	 * Se non � presente lancia un eccezione
	 * 
	 * @param id (int) dell'articolo
	 */
	public void deleteArticolo(int id) { 
		if (articoloRepository.findById(id).get() == null) throw new RuntimeException("Articolo per id inesistente");  
		
		articoloRepository.deleteById(id);
	}
	
	
	
	/**
	 * Restituisce la lista di tutti gli articoli presenti nel database
	 * 
	 * @return (List<Articolo>) lista degli articoli
	 */
	@SuppressWarnings("deprecation")
	public List<Articolo> getArticoloList() { 
		
		List<Articolo> lstArt = articoloRepository.findAll();
		List<Articolo> lstAC = new ArrayList<Articolo> ();
		
		for (Articolo a : lstArt) {
			
		    Date d = a.getPubblication();
		    d.setHours( Integer.parseInt( a.getHour().toString().split(":")[0] ) );
		    d.setMinutes( Integer.parseInt( a.getHour().toString().split(":")[1] ) );
		    		    
		    if ( !a.getPubblication().before( new Date() ) || d.compareTo( new Date() ) > 0 ) continue;
		    
		    
			lstAC.add(a);
		}
		
		
		return lstAC; 
	}
	
	
	
	/**
	 * Restituisce l'ultimo articolo pubblicato che si trova sul database
	 * 
	 * @return (Articolo) l'ultimo articolo pubblicato
	 */
	@SuppressWarnings("deprecation")
	public Articolo getLastArt() { 
		
		
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
		
		
		return art; 
	}

	

	/**
	 * Incrementa il contatore di like di un articolo
	 * 
	 * @param id (int) L'id dell'articolo
	 */
	@Transactional
	public void putLikeByArt(int id) { 
		Articolo a = articoloRepository.getById(id);
		a.setLike(articoloRepository.getById(id).getLike() + 1);
		
		articoloRepository.save( a );
	}

	

	
	/**
	 * Restituisce il numero di like per un articolo
	 * 
	 * 
	 * @param id (INT) numero di id dell'articolo
	 * @return (int) numero di like
	 */
	public int getLkByArt(int id) { 
		return articoloRepository.getById(id).getLike(); 
	}
	
	
	
}

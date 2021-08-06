package services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import entity.commento;
import repository.CommentoQuery;


@Service
@Transactional
public class CommentoService {

	
	@Autowired
    private CommentoQuery commentoQuery;
	
	
	
	/**
	 * 
	 * Restituisce una lista di domande 
	 * 
	 * 
	 * @param numPerPage (int) numero di messaggi per pagine 
	 * @param page (int) numero di pagina
	 * @return lista di pagine
	 */
	public List<commento> getListArt(int numPerPage, int id, int page) {		
		List<commento> c = commentoQuery.getListArt(id);		
		return c;
	}
	
	
	/**
	 * 
	 * Aggiunge un commento al database
	 * 
	 * 
	 * @param c (commento)
	 */
	@Transactional
	public void addComment(commento c) {
		commentoQuery.save(c);
	}
	
	
	
	
}

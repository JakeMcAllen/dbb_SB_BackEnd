package services;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import entity.Domanda;
import repository.DomandaQuery;

@Service
@Transactional
public class DomandeService {

	@Autowired
	private DomandaQuery domandaQuery;
	
	
	/**
	 * Permette di ottenere una lista limitata delle domande effettuate dagli utenti. 
	 * 
	 * @param page (int) numero di pagina raggiunta dall'utente
	 * @param num (int) nuemro di domande richieste per pagina
 	 * @return Lista limitata di domande
	 */
	public List<Domanda> getDomandaForPage(int page, int num) {
		
		List<Domanda> listDom = domandaQuery.findAll();
		List<Domanda> listDomRt = new ArrayList<Domanda> ();
		
		for (int i = page * num; (i < (page * num) + num && i<listDom.size()); i++) {
			listDomRt.add(listDom.get(i));
		}
		
		
		return listDomRt;
	}
	
	
	/**
	 * Inserisce una domanda sul database
	 * 
	 * @param d (Domanda) domanda fatta dall'utente
	 */
	public void addDomanda(Domanda d) {
		
		List<Domanda> listDom = domandaQuery.findAll();
		
		for (Domanda dm : listDom) {
			if (d.getTitolo() == dm.getTitolo()) throw new RuntimeException("Esiste una domanda uguale già presente sul database");
		}
		
		domandaQuery.save(d);
	}
	
	
	/**
	 * Permette di aggiungere una risposta ad una domanda esistente nel database
	 * 
	 * @param d (Domanda) Domanda con risposta
	 */
	public void addRisposta(Domanda d) {
		
		if (d.getRisposta() == null) throw new RuntimeException("La domanda non contiene la risposta");
		List<Domanda> listDom = domandaQuery.findAll();
		
		boolean exst = false;
		for (Domanda dm : listDom) { if (d.getTitolo() == dm.getTitolo()) exst = true; }
		
		if (!exst) throw new RuntimeException("Non esiste la domanda nel database");
		
		domandaQuery.save(d);	
	}


	/**
	 * Restituisce tutte le domande e risposta che contengono una o più parole della string specificata
	 * 
	 * 
	 * @param str
	 * @param i
	 * @return
	 */
	public List<Domanda> getDomandaByStr(String str) {
		
		List<Domanda> listDom = domandaQuery.findAll();
		List<Domanda> ListDomOK = new ArrayList<Domanda>();
		
		for (Domanda dm : listDom) {
			if ( dm.getTitolo().toLowerCase().contains(str.toLowerCase()) 
					|| dm.getRisposta().toLowerCase().contains(str.toLowerCase()) )
				ListDomOK.add(dm);
		}
		
		
		return ListDomOK;
	}
	
	
	/**
	 * Restituisce il numero di domande complessive nel database
	 * 
	 * @return (int) numero di domande
	 */
	public int length() {
		return domandaQuery.findAll().size();
	}


	/**
	 * Restituisce una specifica
	 * 
	 * @param id
	 * @return (Domanda) la domanda corrispondente
	 */
	public Domanda getDomById(int id) {
		System.out.println("dom: " + domandaQuery.getById(id).toString());
		
		return domandaQuery.getById(id);
	}
	
	
}

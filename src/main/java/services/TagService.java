package services;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import entity.Tag;
import entity.articoloTag;
import repository.ArticoloTagQuery;
import repository.TagQuery;



@Service
@Transactional
public class TagService {

	@Autowired
    private TagQuery tagQuery;

	@Autowired
	private ArticoloTagQuery articoloTagQuery;
	
	
	
	/**
	 * 
	 * Restituisce tuple id_Tag e id_articolo
	 * 
	 * @param id (int) numero dell'id
	 * @return Lista degli articoloTag
	 */
	public List<articoloTag> getArticoloTagById(int id) { return articoloTagQuery.findByIdArticolo(id); }
	
	
	/**
	 * 
	 * Articoli per id di un articolo
	 * 
	 * @param id (int) numero dell'articolo
	 * @return Lista dei tag per un articolo
	 */
	public List<Tag> getTagByArtId (int id) {
		List<articoloTag> lst = getArticoloTagById(id);
		List<Tag> lstTg = new ArrayList<Tag> ();
		
		for (articoloTag at : lst) lstTg.add( tagQuery.getById(at.getId().getTag()) );
		
		return lstTg;
	}
	
	
}
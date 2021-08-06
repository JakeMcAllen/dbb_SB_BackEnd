package RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonPropertyDescription;

import dto.ArticoloDTO;
import dto.commentoDTO;
import entity.Articolo;
import entity.Tag;
import entity.commento;
import services.ArticoloService;
import services.CommentoService;
import services.TagService;
import services.UserService;


@RestController
@RequestMapping(path = "/artc")
@CrossOrigin(origins = "https://deblogbrothers.herokuapp.com/")
public class ArticoloRestController {
	
	
	@Autowired
	private ArticoloService as;
	
	@Autowired
	private CommentoService cs;
	
	@Autowired
	private UserService us;
	
	@Autowired
	private TagService ts;
	
	private int numDomByPg = 10;
	
	
	
	@GetMapping(path = "/last", produces = {"Application/xml", "Application/json"})
	public ResponseEntity<ArticoloDTO> getLastArt() {
		
		try {			
			Articolo lstArt = as.getLastArt();
			
			ArticoloDTO lstArtDTO = new ArticoloDTO(lstArt.getId(), 
					lstArt.getTitolo(), 
					lstArt.getTextContext(),
					lstArt.getPubblication(),
					lstArt.getHour(),
					null,
					null,
					lstArt.getLike(),
					lstArt.getLayout() );
			
			return new ResponseEntity<ArticoloDTO> (lstArtDTO, HttpStatus.OK);
		} catch (Exception e) {			
			
			System.out.println("Error: " + e);

			HttpHeaders headers = new HttpHeaders();
			headers.add("msgException", e.getMessage());
			return new ResponseEntity<ArticoloDTO> (headers, HttpStatus.CONFLICT);	
		}
	}
	
	
	@GetMapping(path = "/list")
	@JsonPropertyDescription
	public ResponseEntity<List<ArticoloDTO>> getArtcList() {
		
		try {
			List<Articolo> artcList = as.getArticoloList();
			List<ArticoloDTO> artcListDTO = new ArrayList<ArticoloDTO> ();
			
			for (int i = ( artcList.size() - 1); i >= 0; i--) {
				
				Articolo lstArt = artcList.get(i);
				ArticoloDTO artDTO = new ArticoloDTO(lstArt.getId(), 
						lstArt.getTitolo(), 
						lstArt.getTextContext(),
						lstArt.getPubblication(),
						lstArt.getHour(),
						null,
						null,
						lstArt.getLike(),
						lstArt.getLayout() );
								
				artcListDTO.add(artDTO);
			}
			
			
			return new ResponseEntity<List<ArticoloDTO>> (artcListDTO, HttpStatus.OK);
			
		} catch (Exception e) {			
			
			System.out.println("Error: " + e);
			
			HttpHeaders headers = new HttpHeaders();
			headers.add("msgException", e.getMessage());
			return new ResponseEntity<List<ArticoloDTO>> (headers, HttpStatus.BAD_REQUEST);
		}
	}
	
	
	@GetMapping(path = "/getArt/{id}", produces = {"Application/xml", "Application/json"})
	public ResponseEntity<ArticoloDTO> getArtById(@PathVariable int id) {
		
		try {
			
			Articolo lstArt = as.getArticoloById(id);
			ArticoloDTO artDTO = new ArticoloDTO(lstArt.getId(), 
					lstArt.getTitolo(), 
					lstArt.getTextContext(),
					lstArt.getPubblication(),
					lstArt.getHour(),
					null,
					null,
					lstArt.getLike(),
					lstArt.getLayout() );
			
			return new ResponseEntity<ArticoloDTO> (artDTO, HttpStatus.OK);
			
		} catch (Exception e) {			
			
			System.out.println("Error: " + e);
			
			HttpHeaders headers = new HttpHeaders();
			headers.add("msgException", e.getMessage());
			return new ResponseEntity<ArticoloDTO> (headers, HttpStatus.BAD_REQUEST);
		}
	} 
	
	
	
	
	
	
	
	
	
	
	/**
	 * 
	 * 
	 * LIKES SECTION
	 * 
	 * 
	 */
	
	@GetMapping(path = "/addLike/{id}", produces = {"Application/xml", "Application/json"})
	public ResponseEntity<Object> addLikeArt(@PathVariable int id) {
		
		try {
			
			as.putLikeByArt(id);
		
			return new ResponseEntity<Object> (null, HttpStatus.OK);
		} catch (Exception e) {
			
			System.out.println("Error11: " + e);
			
			HttpHeaders headers = new HttpHeaders();
			headers.add("msgException", e.getMessage());
			return new ResponseEntity<Object> (headers, HttpStatus.BAD_REQUEST);
		}
		
	}
	
	
	@GetMapping(path = "/getLike/{id}")
	@JsonPropertyDescription
	public ResponseEntity<Integer> getLikeArt(@PathVariable int id) {
		
		
		try {
			
			int lk = as.getLkByArt(id);
			
			System.out.println("lk: " + lk);
		
			return new ResponseEntity<Integer> (lk, HttpStatus.OK);
		} catch (Exception e) {
			
			System.out.println("Error12: " + e);
			
			HttpHeaders headers = new HttpHeaders();
			headers.add("msgException", e.getMessage());
			return new ResponseEntity<Integer> (headers, HttpStatus.BAD_REQUEST);
		}
		
	}
	
	
	
	
	
	
	
	
	
	
	
	/**
	 * 
	 * 
	 * COMMENTS SECTION
	 * 
	 * 
	 */
	
	
	@GetMapping(path = "/comm/id={id}/pg={pg}")
	@JsonPropertyDescription
	public ResponseEntity<List<commentoDTO>> getSubList(@PathVariable int id, @PathVariable int pg) {
		
		try {
						
			List<commento> lc = cs.getListArt(numDomByPg, id, pg);
			List<commentoDTO> lcDTO = new ArrayList<commentoDTO> ();
			
			
			for (commento cDTO : lc) {
				lcDTO.add( new commentoDTO(
							cDTO.getId(),
							cDTO.getTesto(),
							cDTO.getTimestamp(),
							cDTO.getArt().getId(),
							cDTO.getU().getUsername(),
							cDTO.getU().getId()
						) );
			}
			
			return new ResponseEntity<List<commentoDTO>> (lcDTO, HttpStatus.OK);
		} catch (Exception e) {
			
			System.out.println("Error: " + e);
			
			HttpHeaders headers = new HttpHeaders();
			headers.add("msgException", e.getMessage());
			return new ResponseEntity<List<commentoDTO>> (headers, HttpStatus.BAD_REQUEST);
		}
	}
	
	
	
	@PostMapping(path = "/addComm/{idU}", consumes = {"Application/xml", "Application/json"})
	public ResponseEntity<Object> addComment(@RequestBody commentoDTO cm, @PathVariable int idU) {
		
		try {
			
			commento c = new commento ();
						
			c.setTesto(cm.getTesto());
			c.setArt(as.getArticoloById( cm.getArt() ));
			c.setTimestamp(new Date());
			c.setU(us.getUserById( idU ));
			
						
			cs.addComment(c);
			
			return new ResponseEntity<Object> (null, HttpStatus.OK);
		} catch (Exception e) {
			
			System.out.println("Error: " + e);
			
			HttpHeaders headers = new HttpHeaders();
			headers.add("msgException", e.getMessage());
			return new ResponseEntity<Object> (headers, HttpStatus.BAD_REQUEST);
		}
		
	}
	
	
	
	/**
	 * 
	 * 
	 * TAGS 
	 * 
	 * 
	 */
	@GetMapping(path = "/tagsArtid/{id}")
	@JsonPropertyDescription
	public ResponseEntity<List<String>> getTagsByArtID(@PathVariable int id) {
		
		try {
			
			List<String> li = new ArrayList<String> ();
			List<Tag> lstTg = ts.getTagByArtId(id);
						
			for (Tag tag : lstTg) li.add( tag.getTag() );
			
			return new ResponseEntity<List<String>> (li, HttpStatus.OK);
		} catch (Exception e) {
			
			System.out.println("Error: " + e);
			
			HttpHeaders headers = new HttpHeaders();
			headers.add("msgException", e.getMessage());
			return new ResponseEntity<List<String>> (headers, HttpStatus.BAD_REQUEST);
		}
	}
	
}

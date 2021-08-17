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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonPropertyDescription;

import dto.DomandaDTO;
import dto.addFaq;
import dto.addNewArt;
import dto.addNewImgs;
import entity.Domanda;
import entity.User;
import exception.imgNotSaved;
import services.UserService;
import services.addService;


@RestController
@RequestMapping(path = "/add")
@CrossOrigin(origins = {"https://deblogbrothers.herokuapp.com/", "https://privateareadbb.herokuapp.com/AddPhoto"} )
public class addRestController {

	@Autowired
	private addService as;
	
	@Autowired
	private UserService us;
	
	
	
	@PostMapping(path = "/Art")
	@JsonPropertyDescription
	public ResponseEntity<Object> addNewArt(@RequestBody addNewArt nArt) {

		try {
			
			// controllo dei dati utente e aggiunta di dati al database
			User u = us.getUserByUsernameAndPassword(nArt.getUsername(), nArt.getPassword() );
			if ( u != null && u.getPermessi() < 2) as.addArt(nArt);
			
			return new ResponseEntity<Object> (true, HttpStatus.OK);
		} catch (imgNotSaved e) {
			
			HttpHeaders headers = new HttpHeaders();
			headers.add("Il database ha rifiutato l'upload.", e.getMessage());
			return new ResponseEntity<Object> (headers, HttpStatus.CONFLICT);
		} catch (Exception e) {
			
			HttpHeaders headers = new HttpHeaders();
			headers.add("Errore generico.", e.getMessage());
			return new ResponseEntity<Object> (headers, HttpStatus.CONFLICT);
		}
	}
	
	
	
	@PostMapping(path = "/Faq")
	@JsonPropertyDescription
	public ResponseEntity<Object> addFaq(@RequestBody addFaq dom) {

		try {
		
			Domanda d = new Domanda();
			d.setTitolo( dom.getTitolo() );
			d.setRisposta( dom.getRisposta() );
			d.setTime( new Date() );
			
			// controllo dei dati utente e aggiunta di dati al database
			User u = us.getUserByUsernameAndPassword( dom.getUsername(), dom.getPassword() );
			if ( u != null && u.getPermessi() < 2) as.rispToDom(d);
			
			return new ResponseEntity<Object> (null, HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			
			HttpHeaders headers = new HttpHeaders();
			headers.add("Il database ha rifiutato l'upload.", e.getMessage());
			return new ResponseEntity<Object> (headers, HttpStatus.CONFLICT);
		} catch (Exception e) {			
			
			HttpHeaders headers = new HttpHeaders();
			headers.add("Errore generico.", e.getMessage());
			return new ResponseEntity<Object> (headers, HttpStatus.BAD_REQUEST);
			
		}
	}
	
	
	@PostMapping(path = "/imgs")
	@JsonPropertyDescription
	public ResponseEntity<Object> addImgs(@RequestBody addNewImgs imgs) {

		
		System.out.println("img: " + imgs.toString() );
		
		try {
			
			// controllo dei dati utente e aggiunta di dati al database
			User u = us.getUserByUsernameAndPassword( imgs.getUsername(), imgs.getPassword() );
			if ( u != null && u.getPermessi() < 2) as.addImgs(imgs);
			
			return new ResponseEntity<Object> (null, HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			
			HttpHeaders headers = new HttpHeaders();
			headers.add("Il database ha rifiutato l'upload.", e.getMessage());
			return new ResponseEntity<Object> (headers, HttpStatus.CONFLICT);
		} catch (Exception e) {			
			
			HttpHeaders headers = new HttpHeaders();
			headers.add("Errore generico.", e.getMessage());
			return new ResponseEntity<Object> (headers, HttpStatus.BAD_REQUEST);
			
		}
	}
	
	
	/**
	 * Restituisce tutte le domande senza una risposta
	 * 
	 * @param page
	 * @return
	 */
	@GetMapping(path = "/getDomW")
	@JsonPropertyDescription
	public ResponseEntity<List<DomandaDTO>> getDomWhite() {
		
		try {			


			List<Domanda> domList = as.getDomandaWhite();
			List<DomandaDTO> domListDTO = new ArrayList<DomandaDTO> ();
			
			
			for (int i = 0; i < domList.size(); i++) {
				
				Domanda dArt = domList.get(i);
				DomandaDTO artDTO = new DomandaDTO(
										dArt.getId(), 
										dArt.getTitolo(),
										dArt.getRisposta(),
										dArt.getTime()
									);
				
				domListDTO.add(artDTO);
			}
			
		
			return new ResponseEntity<List<DomandaDTO>> (domListDTO, HttpStatus.OK);
		} catch (Exception e) {			
			
			System.out.println("Error: " + e);

			HttpHeaders headers = new HttpHeaders();
			headers.add("msgException", e.getMessage());
			return new ResponseEntity<List<DomandaDTO>> (headers, HttpStatus.BAD_REQUEST);
		}
		
	}
	
	
}

package RestController;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonPropertyDescription;

import dto.DomandaDTO;
import entity.Domanda;
import services.DomandeService;

@RestController
@RequestMapping(path = "/dom")
@CrossOrigin(origins = {"https://deblogbrothers.herokuapp.com/", "http://localhost:3000/"})
public class DomandaRestController {
	
	@Autowired
	private DomandeService ds;
	
	private int domForPage = 10;
	
	
	
	@GetMapping(path = "/get/{page}")
	@JsonPropertyDescription
	public ResponseEntity<List<DomandaDTO>> getDomForPage(@PathVariable int page) {
		
		try {			


			List<Domanda> domList = ds.getDomandaForPage(page, domForPage);			
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
	
	
	@GetMapping(path = "/getDomByStr/{str}")
	@JsonPropertyDescription
	public ResponseEntity<List<DomandaDTO>> getDomandaContain(@PathVariable String str) {
		
		try {
			
			List<Domanda> domList = ds.getDomandaByStr(str);			
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
	
	
	@GetMapping(path= "/domById/{id}", produces = {"Application/xml", "Application/json"})
	public ResponseEntity<DomandaDTO> getDomById(@PathVariable int id) {
		
		try {
			
			Domanda dArt = ds.getDomById(id);			
			
			DomandaDTO artDTO = new DomandaDTO(
									dArt.getId(), 
									dArt.getTitolo(),
									dArt.getRisposta(),
									dArt.getTime()
								);
			
			return new ResponseEntity<DomandaDTO> (artDTO, HttpStatus.OK);
		} catch (Exception e) {			
			
			System.out.println("Error: " + e);
		
			HttpHeaders headers = new HttpHeaders();
			headers.add("msgException", e.getMessage());
			return new ResponseEntity<DomandaDTO> (headers, HttpStatus.BAD_REQUEST);
			
		}
	}
	
	
	
	@GetMapping(path = "/length")
	@JsonPropertyDescription
	public ResponseEntity<Integer> getNumPag() {
		
		try {
			
			int num = (int) Math.ceil( ds.length() / domForPage );
			
			return new ResponseEntity<Integer> (num, HttpStatus.OK);
		} catch (Exception e) {			
			
			System.out.println("Error: " + e);

			HttpHeaders headers = new HttpHeaders();
			headers.add("msgException", e.getMessage());
			return new ResponseEntity<Integer> (headers, HttpStatus.BAD_REQUEST);
			
		}
		
	}
	
	
	
}

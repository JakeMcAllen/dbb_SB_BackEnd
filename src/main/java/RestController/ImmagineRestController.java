package RestController;

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

import dto.ImmagineDTO;
import entity.immagine;
import services.ImmagineService;

@RestController
@RequestMapping(path = "/img")
@CrossOrigin(origins = "https://deblogbrothers.herokuapp.com/")
public class ImmagineRestController {

	@Autowired
	private ImmagineService is;
	
	
	/**
	 * 
	 * Restituisce l'immagine di copertina di un articolo by id
	 * 
	 * @param (int) id dell'atticolo di cui voglio ottenere la copertina
	 * 
	 */
	@GetMapping(path = "/getCpArt/{idArt}", produces = {"Application/xml", "Application/json"})
	public ResponseEntity<ImmagineDTO> getLastImg(@PathVariable int idArt) {
		
		try {
			immagine imgObj = is.getImgCopertina(idArt);
						
			ImmagineDTO imgDTO = new ImmagineDTO( imgObj.getId(), 
					imgObj.getDescrizione(), 
					imgObj.getImg(),
					imgObj.getHeigth(),
					imgObj.getWidth(),
					imgObj.isCopertina(),
					imgObj.getArticolo().getId() );
			
			return new ResponseEntity<ImmagineDTO> (imgDTO, HttpStatus.OK);
		} catch (Exception e) {
			
			System.out.println("Error: " + e);
			
			HttpHeaders headers = new HttpHeaders();
			headers.add("msgException", e.getMessage());
			return new ResponseEntity<ImmagineDTO> (headers, HttpStatus.BAD_REQUEST);
			
		}
	}



	/**
	 * 
	 * Restituisce la lista degli id delle immagini secondarie di un articolo
	 * 
	 * @param idArt (int) id dell'artticolo corrispondente
	 * 
	 */
	@GetMapping(path = "/getScArt/{idArt}")
	@JsonPropertyDescription
	public ResponseEntity<List<Integer>> getListImgArt(@PathVariable int idArt) {
		
		try {
			List<Integer> lstImg = is.getLstImgByArtId( idArt );
			
			return new ResponseEntity<List<Integer>> (lstImg, HttpStatus.OK);
			
		} catch (Exception e) {
			
			System.out.println("Error: " + e);
			
			HttpHeaders headers = new HttpHeaders();
			headers.add("msgException", e.getMessage());
			return new ResponseEntity<List<Integer>> (headers, HttpStatus.BAD_REQUEST);
			
		}
	}
	
	
	/**
	 * 
	 * Restituisce l'immagine corrispondente ad uno specifico id
	 * 
	 * @param idImg (int) id dell'immagine che si vuole selezionare
	 * 
	 */
	@GetMapping(path = "/getImgById/{idImg}", produces = {"Application/xml", "Application/json"})
	public ResponseEntity<ImmagineDTO> getImgById(@PathVariable int idImg) {
				
		try {
			immagine imgObj = is.getImgById( idImg );
			ImmagineDTO imgDTO = new ImmagineDTO( imgObj.getId(), 
					imgObj.getDescrizione(), 
					imgObj.getImg(),
					imgObj.getHeigth(),
					imgObj.getWidth(),
					imgObj.isCopertina(),
					imgObj.getArticolo().getId() );
			
			return new ResponseEntity<ImmagineDTO> (imgDTO, HttpStatus.OK);
		} catch (Exception e) {
			
			System.out.println("Error: " + e);
			
			HttpHeaders headers = new HttpHeaders();
			headers.add("msgException", e.getMessage());
			return new ResponseEntity<ImmagineDTO> (headers, HttpStatus.CONFLICT);
			
		}
	}
	
}

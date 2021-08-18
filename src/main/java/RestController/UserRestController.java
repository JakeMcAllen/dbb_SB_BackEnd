package RestController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dto.UserDTO;
import entity.User;
import services.UserService;

@RestController
@RequestMapping(path = "/usr")
@CrossOrigin(origins = {"https://deblogbrothers.herokuapp.com/", "http://localhost:3000/"})
public class UserRestController {

	@Autowired
	private UserService us;
	
	
	@PostMapping(path="/contUsr", consumes = {"Application/xml", "Application/json"}, produces = {"Application/xml", "Application/json"})
	public ResponseEntity<UserDTO> logUtente(@RequestBody UserDTO user) {
		
		System.out.println("User: " + user);
		
		try {
			
			if ( user.getUsername().isEmpty() 
					|| user.getPassword().isEmpty() 
					|| user.getUsername().isEmpty() 
					|| user.getPassword().isEmpty() 
				)
				throw new Exception("Username or password not specificate");
			
			
			User u = us.getUserByUsernameAndPassword(user.getUsername(), user.getPassword());
			UserDTO uDTO = new UserDTO(
						u.getId(),
						u.getNome(),
						u.getCognome(),
						u.getUsername(),
						u.getPassword(),
						u.getPermessi()
					);
			
			
			return new ResponseEntity<UserDTO> (uDTO, HttpStatus.OK);
		} catch (Exception e) {
			
			System.out.println("Error: " + e);

			HttpHeaders headers = new HttpHeaders();
			headers.add("msgException", e.getMessage());
			return new ResponseEntity<UserDTO> (headers, HttpStatus.BAD_REQUEST);
		}
		
		
	}
	
	
	@PostMapping(path = "/reg", consumes = {"Application/xml", "Application/json"})
	public ResponseEntity<Object> registraUtente(@RequestBody UserDTO user) {
		
		try {

			us.registraUtente( 
				new User(
					user.getId(),
					user.getNome(),
					user.getCognome(),
					user.getUsername(),
					user.getPassword(),
					2
				) );
			
			return new ResponseEntity<Object> (true, HttpStatus.OK);
		} catch (Exception e) {
			
			System.out.println("Error: " + e);
	
			HttpHeaders headers = new HttpHeaders();
			headers.add("msgException", e.getMessage());
			return new ResponseEntity<Object> (headers, HttpStatus.CONFLICT);
		}
		
	}
	
	
	
	
}

package services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import entity.User;
import repository.UserQuery;

@Service
@Transactional
public class UserService {

	@Autowired
    private UserQuery usr;
	
	
	
	
	
	/**
	 * Search a user whit exactly same username and password
	 * 
	 * @return (User) corisponded user
	 */
	public User getUserByUsernameAndPassword(String username, String password) {
		return usr.searchUserByUsernameAndPassword( username, password);
	}
	
	/**
	 * Registra un utente nel database
	 * 
	 * @param u (user) utente da registrare
	 */
	public void registraUtente ( User u ) { usr.save(u); }
	
	/**
	 * Seleziona un utente per l'id
	 * 
	 * @param id (int) l'id dell'utente
	 * @return (User) L'utente da registrare
	 */
	public User getUserById(int id) {
		return usr.getById(id);
	}
	
}

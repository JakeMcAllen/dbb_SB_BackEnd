package repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import entity.User;


public interface UserQuery extends JpaRepository<User, Integer> {
	
	@Query(value = "SELECT usr FROM User usr WHERE usr.username=?1 AND usr.password=?2", nativeQuery = false)
	User searchUserByUsernameAndPassword(String username, String password);
	
	
}

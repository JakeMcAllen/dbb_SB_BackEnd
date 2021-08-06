package repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import entity.commento;


public interface CommentoQuery extends JpaRepository<commento, Integer> {

	
	@Query(value = "SELECT cm FROM commento AS cm WHERE cm.art.id = ?1", nativeQuery = false)
	List<commento> getListArt(int id);

	
	
}
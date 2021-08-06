package repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import entity.Articolo;

@Repository
public interface ArticoloQuery extends JpaRepository<Articolo, Integer> {
    
	@Modifying
	@Query(value = "DELETE FROM Articolo AS a WHERE a.id = ?1", nativeQuery = false)
	public void deletyArtById(int id);

	
	@Transactional
	@Query(value = "SELECT a1 FROM Articolo AS a1 WHERE a1.pubblication = (SELECT MAX(a.pubblication) FROM Articolo a )", nativeQuery = false)
	public Articolo getLastArt();
	
	
	@Modifying	
	@Query(value = "UPDATE Articolo AS a SET a.likes = ?1 WHERE a.id = ?2 ", nativeQuery = false)
	public void setLkArtById(int like, int id);	
	
}
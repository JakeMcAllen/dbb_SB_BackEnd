package repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import entity.Domanda;

@Repository
public interface DomandaQuery extends JpaRepository<Domanda, Integer>{
	
	@Modifying
	@Query(value = "DELETE FROM Domanda dom WHERE dom.id = ?1", nativeQuery = false)
	public void delete(int id);

//	@Modifying
//	@Query(value = "UPDATE Domanda dom SET dom.Risposta = :risp WHERE dom.id = :id", nativeQuery = false)
//	public void rispondiDomanda(@Param("risp") String risposta, @Param("id") int id);
//	

	
}

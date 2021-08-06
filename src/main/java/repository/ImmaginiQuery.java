package repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import entity.immagine;


public interface ImmaginiQuery extends JpaRepository<immagine, Integer> {

	
	@Query(value = "SELECT img FROM immagine AS img WHERE img.articolo.id = ?1 AND img.copertina = true", nativeQuery = false)
	immagine lastImgArt(int idArt);

	@Query(value = "SELECT img.id FROM immagine AS img WHERE img.articolo.id = ?1 AND img.copertina = false", nativeQuery = false)
	List<Integer> getLstImgByArtId(int id);


	@Query(value = "SELECT img FROM immagine AS img WHERE img.articolo.id = ?1 ", nativeQuery = false)
	List<immagine> getImgByArtId(int id);	
	
	
}

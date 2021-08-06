package repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import entity.EmbeddableIdTags;
import entity.articoloTag;


@Repository
public interface ArticoloTagQuery extends JpaRepository<articoloTag, EmbeddableIdTags> {

	
	@Query(value = "SELECT a FROM articoloTag AS a WHERE a.id.art = ?1 ", nativeQuery = false)
	List<articoloTag> findByIdArticolo(int artId);

	
	@Query(value = "SELECT a FROM articoloTag AS a WHERE a.id.tag = ?1 ", nativeQuery = false)
	List<articoloTag> findByIdTag(int tagId);
	
}

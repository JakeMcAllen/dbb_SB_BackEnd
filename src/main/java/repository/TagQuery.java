package repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import entity.Tag;

@Repository
public interface TagQuery extends JpaRepository<Tag, Integer> {

}

package wt.project.sturting.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import wt.project.sturting.entity.Tag;

import java.util.Optional;

public interface TagRepository extends JpaRepository<Tag, Long> {
	Optional<Tag> findByTagName(String tagName);
}

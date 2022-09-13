package wt.project.sturting.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import wt.project.sturting.entity.Study;

public interface StudyRepository extends JpaRepository<Study, Long> {
}

package wt.project.sturting.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import wt.project.sturting.entity.StudyUser;

public interface StudyUserRepository extends JpaRepository<StudyUser, Long> {
}

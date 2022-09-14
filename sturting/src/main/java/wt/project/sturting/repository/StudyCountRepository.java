package wt.project.sturting.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import wt.project.sturting.entity.StudyCount;
import wt.project.sturting.entity.enums.StudyMethod;

import java.util.Optional;

public interface StudyCountRepository extends JpaRepository<StudyCount, Long> {
	Optional<StudyCount> findByStudyMethod(StudyMethod studyMethod);
}

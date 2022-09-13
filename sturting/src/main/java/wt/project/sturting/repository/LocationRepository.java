package wt.project.sturting.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import wt.project.sturting.entity.Location;

public interface LocationRepository extends JpaRepository<Location, Long> {
}

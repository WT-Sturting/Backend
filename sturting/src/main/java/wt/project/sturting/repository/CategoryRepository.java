package wt.project.sturting.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import wt.project.sturting.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

}
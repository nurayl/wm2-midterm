package az.ada.library.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import az.ada.library.Models.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
}

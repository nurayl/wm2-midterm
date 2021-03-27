package az.ada.library.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import az.ada.library.Models.Book;

public interface BookRepository extends JpaRepository<Book, Long>, BooksSearherRepository {
    List<Book> findByPickedUpById(Long pickedUpById);
}

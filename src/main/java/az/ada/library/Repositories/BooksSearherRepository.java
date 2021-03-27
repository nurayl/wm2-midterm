package az.ada.library.Repositories;

import java.util.List;

import org.springframework.stereotype.Repository;

import az.ada.library.Models.Book;

@Repository
public interface BooksSearherRepository {

    List<Book> findByBookNameAndAuthorAndCategoryId(String name, String authorName, Long categoryId);
}
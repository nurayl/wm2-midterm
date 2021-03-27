package az.ada.library.Services;

import java.util.List;

import az.ada.library.Controllers.dtos.BookCreateRequest;
import az.ada.library.Models.Book;
import az.ada.library.Services.dtos.CustomException;

public interface BookService {

    public List<Book> search(String name, String author, Long categoryId);

    public Book create(BookCreateRequest request);

    public void pickBook(Long bookId) throws CustomException;

    public void dropBook(Long bookId) throws CustomException;

    public List<Book> getBooksOfCurrentUser();

}

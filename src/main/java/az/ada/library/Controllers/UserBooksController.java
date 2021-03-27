package az.ada.library.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import az.ada.library.Controllers.dtos.BooksSearchResponse;
import az.ada.library.Models.Book;
import az.ada.library.Services.BookService;


@RestController
public class UserBooksController {

    @Autowired
    BookService bookService;

	// 2.5. Get the currently pick-up books from library(by logged in user)
    @GetMapping({ "/user/books" })
	public BooksSearchResponse booksOfCurrentUser() {
        List<Book> books = bookService.getBooksOfCurrentUser();
        return new BooksSearchResponse(books);
    }
}

package az.ada.library.Controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import az.ada.library.Controllers.dtos.BookCreateRequest;
import az.ada.library.Controllers.dtos.BooksSearchResponse;
import az.ada.library.Controllers.dtos.CommentCreateRequest;
import az.ada.library.Models.Book;
import az.ada.library.Models.Comment;
import az.ada.library.Models.Reply;
import az.ada.library.Services.BookService;
import az.ada.library.Services.CommentService;
import az.ada.library.Services.dtos.CustomException;

@RestController
public class BookController {
    @Autowired
    BookService bookService;

    @Autowired
    CommentService commentService;

    Logger logger = LoggerFactory.getLogger(BookController.class);

    // 2.2 library should provide information about the list of the books with
    // metadata infomation(if not available, who picked up the book)
    // 2.3 search by name, author name, and category
    @GetMapping({ "/books" }) // /books?name=B&categoryName=Sci-Fi&author=Authorname
    public BooksSearchResponse getAll(@RequestParam(required = false) String name,
            @RequestParam(required = false) String author, @RequestParam(required = false) Long categoryId) {
        List<Book> books = bookService.search(name, author, categoryId);
        return new BooksSearchResponse(books);
    }

    @GetMapping({ "/books/{bookId}" })
    public Book findbookById(@PathVariable Long bookId) {
        Book book = bookService.findWithComments(bookId);
        return book;
    }

    @PostMapping("/books/{bookId}/comment")
    public Comment addComment(@RequestBody CommentCreateRequest commentRequest,
            @PathVariable(name = "bookId") Long bookId) {
        Comment newComment = commentService.addComment(commentRequest, bookId);

        return newComment;
    }

    @PostMapping("/reply/{commentId}")
    public Reply addReply(@RequestBody CommentCreateRequest replyRequest,
            @PathVariable String commentId) {
        Reply newReply = commentService.addReply(replyRequest, commentId);

        return newReply;
    }

    @PostMapping({ "/books" })
    public Book create(@RequestBody BookCreateRequest request) {
        return bookService.create(request);
    }

    // 2.4. Ability to pick-up / drop-off books from library
    // if successfull, returns 200
    // if not successful, return 500 internal server error
    @PostMapping({ "/books/{bookId}/pick" })
    public ResponseEntity pick(@PathVariable Long bookId) {
        try {
            bookService.pickBook(bookId);
            return new ResponseEntity(HttpStatus.OK);
        } catch (CustomException e) {
            String errMsg = "Server Error while trying to PICK the book: " + e.getMessage();
            logger.error(errMsg, e);
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // if successfull, returns 200
    // if not successful, return 500 internal server error
    @PostMapping({ "/books/{bookId}/drop" })
    public ResponseEntity drop(@PathVariable Long bookId) {
        try {
            bookService.dropBook(bookId);
            return new ResponseEntity(HttpStatus.OK);
        } catch (CustomException e) {
            String errMsg = "Server Error while trying to DROP the book: " + e.getMessage();
            logger.error(errMsg, e);
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}

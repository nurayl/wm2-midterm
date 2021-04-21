package az.ada.library.Services.Impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import az.ada.library.Controllers.dtos.BookCreateRequest;
import az.ada.library.Models.Book;
import az.ada.library.Models.Category;
import az.ada.library.Models.Comment;
import az.ada.library.Models.LibraryBookActions;
import az.ada.library.Models.PickDropHistory;
import az.ada.library.Models.Reply;
import az.ada.library.Models.User;
import az.ada.library.Repositories.BookRepository;
import az.ada.library.Repositories.CategoryRepository;
import az.ada.library.Repositories.CommentRepository;
import az.ada.library.Repositories.PickDropHistoryRepository;
import az.ada.library.Repositories.ReplyRepository;
import az.ada.library.Services.BookService;
import az.ada.library.Services.dtos.CustomException;

@Service
public class BookServiceImpl extends BaseService implements BookService {
    @Autowired
    BookRepository bookRepo;

    @Autowired
    PickDropHistoryRepository historyRepo;

    @Autowired
    CategoryRepository categoryRepo;
    
    @Autowired
    CommentRepository commentRepository;
    
    @Autowired
    ReplyRepository replyRepository;
    
    // @Autowired
    // ReplyRepository replyRepository2;

    Logger logger = LoggerFactory.getLogger(BookServiceImpl.class);

    public List<Book> search(String name, String author, Long categoryId) {
        return bookRepo.findByBookNameAndAuthorAndCategoryId(name, author, categoryId);
    }

    @Override
    public Book findWithComments(Long bookId) {
        Book book = bookRepo.findById(bookId).get();
        List<Comment> comments = commentRepository.findByBookId(bookId);

        if (comments.size() > 0) {
            Comment ca = comments.get(0);
            List<Reply> replylar = replyRepository.findByCommentId(ca.getId());
            ca.setReplys(replylar);
        }
        book.setComments(comments);
        return book;
    }

    public Book create(BookCreateRequest request) {
        Book b = new Book(request.getName(), request.getAuthor(), request.getPublishDate());
        List<Category> categories = new ArrayList<Category>();

        for (Long id : request.getCategoryIds()) {
            categories.add(new Category(id));
        }
        b.setCategories(categories);
        bookRepo.save(b);

        return b;
    }


    public void pickBook(Long bookId) throws CustomException {
        long userId = this.getCurrentUserId();

        // if there is no book, error
        Optional<Book> book = bookRepo.findById(bookId);
        if (!book.isPresent()) {
            throw new CustomException("There is no such book");
        }
        Book b = book.get();
        // if it is not available, return error
        if (b.getPickedUpBy() != null) {
            throw new CustomException("Book is not available");
        }
        b.setPickedUpBy(new User(userId));
        bookRepo.save(b);
        logger.info("User " + userId + " picked the book " + b.getName());

        // save action in the history
        PickDropHistory historyRecord = new PickDropHistory(userId, LibraryBookActions.Pick);
        historyRepo.save(historyRecord);
    }

    public void dropBook(Long bookId) throws CustomException {
        long userId = this.getCurrentUserId();

        // if there is no book, error
        Optional<Book> book = bookRepo.findById(bookId);
        if (!book.isPresent()) {
            throw new CustomException("There is no such book");
        }

        Book b = book.get();
        // if it is not picked up, return error
        if (b.getPickedUpBy() == null) {
            throw new CustomException("Book is not picked up yet");
        }
        
        // if it is not picked up by current user, return error
        if (b.getPickedUpBy().getId() != userId) {
            throw new CustomException("Book is not picked up by this user. You can't drop it.");
        }

        b.setPickedUpBy(null);
        bookRepo.save(b);
        logger.info("User " + userId + " dropped the book " + b.getName());

        // save action in the history
        PickDropHistory historyRecord = new PickDropHistory(userId, LibraryBookActions.Drop);
        historyRepo.save(historyRecord);
    }

    public List<Book> getBooksOfCurrentUser() {
        Long userId = this.getCurrentUserId();
        return bookRepo.findByPickedUpById(userId);
    }
}

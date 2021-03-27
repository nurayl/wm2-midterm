package az.ada.library.Controllers.dtos;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import az.ada.library.Models.Book;
import az.ada.library.Models.Category;
import lombok.Data;

@Data
public class BooksSearchResponse {
    private List<BookDto> books;

    public BooksSearchResponse() {
    }

    public BooksSearchResponse(List<Book> books) {
        this.books = new LinkedList<BookDto>();
        for (Book book : books) {
            this.books.add(new BookDto(book));
        }
    }
}

@Data
class BookDto {
    private Long id;
    private String author;
    private String name;

    @JsonFormat(pattern = "yyyy/MM/dd")
    private Date publishDate;
    private boolean isAvailable;
    private String pickedUpBy;
    List<Category> categories;

    public BookDto() {
    }

    public BookDto(Book book) {
        this.id = book.getId();
        this.author = book.getAuthor();
        this.name = book.getName();
        this.publishDate = book.getPublishDate();
        this.categories = book.getCategories();

        this.isAvailable = (book.getPickedUpBy() == null);
        if (book.getPickedUpBy() != null) {
            this.pickedUpBy = book.getPickedUpBy().getFullName();
        }
    }
}

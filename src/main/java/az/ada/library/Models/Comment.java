package az.ada.library.Models;

import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

// https://mkyong.com/spring-boot/spring-boot-spring-data-mongodb-example/
@Data
@Document(collection = "comments")
public class Comment {
    private String id;

    private String text;

    private Long bookId;
    private List<Reply> replys;

    public Comment(String text, Long bookId) {
        this.text = text;
        this.bookId = bookId;
    }

    public Comment() {
    }

    public Comment(String text) {
        this.text = text;
    }

}

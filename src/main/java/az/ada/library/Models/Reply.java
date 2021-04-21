package az.ada.library.Models;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

// https://mkyong.com/spring-boot/spring-boot-spring-data-mongodb-example/
@Data
@Document(collection = "replys")
public class Reply {
    private String id;

    private String text;

    private String commentId;

    public Reply(String text, String commentId) {
        this.text = text;
        this.commentId = commentId;
    }

    public Reply() {
    }

}

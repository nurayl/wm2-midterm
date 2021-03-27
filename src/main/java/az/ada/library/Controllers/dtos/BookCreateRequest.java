package az.ada.library.Controllers.dtos;

import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class BookCreateRequest {
    private String name;
    private String author;
    private Date publishDate;
    private List<Long> categoryIds;
}

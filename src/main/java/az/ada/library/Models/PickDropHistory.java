package az.ada.library.Models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.Data;

@Data
@Entity(name = "pick_drop_history")
public class PickDropHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    private Date date;
    private LibraryBookActions action;

    public PickDropHistory() {
    }

    public PickDropHistory(User user, LibraryBookActions action) {
        this.user = user;
        this.action = action;
        this.date = new Date();
    }

    public PickDropHistory(Long userId, LibraryBookActions action) {
        this.user = new User(userId);
        this.action = action;
        this.date = new Date();
    }
}

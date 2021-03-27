package az.ada.library.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import az.ada.library.Controllers.dtos.UserPickDropHistoryResponse;
import az.ada.library.Models.PickDropHistory;
import az.ada.library.Services.HistoryService;


@RestController
public class UserHistoryController {

    @Autowired
    HistoryService historyService;

	// 2.5. Get the currently pick-up books from library(by logged in user)
    @GetMapping({ "/user/history" })
	public UserPickDropHistoryResponse booksOfCurrentUser() {
        List<PickDropHistory> history = historyService.getHistoryOfCurrentUser();
        UserPickDropHistoryResponse response = new UserPickDropHistoryResponse(history);
        return response;
    }
}

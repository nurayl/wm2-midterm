package az.ada.library.Services.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import az.ada.library.Models.PickDropHistory;
import az.ada.library.Repositories.PickDropHistoryRepository;
import az.ada.library.Services.HistoryService;

@Service
public class HistoryServiceImpl extends BaseService implements HistoryService {
    @Autowired
    PickDropHistoryRepository historyRepository;

    public List<PickDropHistory> getHistoryOfCurrentUser() {
        Long userId = this.getCurrentUserId();
        return historyRepository.findByUserId(userId);
    }
}

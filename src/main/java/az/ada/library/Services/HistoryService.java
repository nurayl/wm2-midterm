package az.ada.library.Services;

import java.util.List;

import org.springframework.stereotype.Service;

import az.ada.library.Models.PickDropHistory;

@Service
public interface HistoryService {
    public List<PickDropHistory> getHistoryOfCurrentUser();
}

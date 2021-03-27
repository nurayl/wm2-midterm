package az.ada.library.Controllers.dtos;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import az.ada.library.Models.LibraryBookActions;
import az.ada.library.Models.PickDropHistory;
import lombok.Data;

@Data
public class UserPickDropHistoryResponse {
    private List<HistoryRecord> records;
    
    public UserPickDropHistoryResponse(List<PickDropHistory> records) {
        this.records = new LinkedList<HistoryRecord>();
        for (PickDropHistory pickDropHistory : records) {
            this.records.add(new HistoryRecord(pickDropHistory));
        }
    }
}

@Data
class HistoryRecord {
    private Date date;
    private LibraryBookActions action;
    
    public HistoryRecord(Date date, LibraryBookActions action) {
        this.date = date;
        this.action = action;
    }
    
    public HistoryRecord(PickDropHistory pickDropHistory) {
        this.date = pickDropHistory.getDate();
        this.action = pickDropHistory.getAction();
    }
}


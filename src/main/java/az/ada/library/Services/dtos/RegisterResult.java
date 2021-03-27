package az.ada.library.Services.dtos;

import az.ada.library.Models.User;
import lombok.Data;

@Data
public class RegisterResult {
    private User user;
    private String errorMessage;

    public RegisterResult() {}
    
    public RegisterResult(User user, String error) {
        this.user = user;
        this.errorMessage = error;
    }
}

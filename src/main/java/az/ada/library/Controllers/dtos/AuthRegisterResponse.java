package az.ada.library.Controllers.dtos;

import lombok.Data;

@Data
public class AuthRegisterResponse {
    private String email;
    private String fullName;

    public AuthRegisterResponse(String email, String fullName) {
        this.email = email;
        this.fullName = fullName;
    }
}


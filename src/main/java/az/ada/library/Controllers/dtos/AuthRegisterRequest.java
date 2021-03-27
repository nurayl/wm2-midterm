package az.ada.library.Controllers.dtos;

import lombok.Data;

@Data
public class AuthRegisterRequest {
    private String fullName;
    private String email;
    private String password;
    private String passwordVerify;
}


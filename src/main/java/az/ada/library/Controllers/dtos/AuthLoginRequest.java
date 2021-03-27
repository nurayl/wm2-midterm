package az.ada.library.Controllers.dtos;

import lombok.Data;

@Data
public class AuthLoginRequest {
    private String email;
    private String password;
}


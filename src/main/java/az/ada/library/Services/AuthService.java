package az.ada.library.Services;

import az.ada.library.Services.dtos.RegisterResult;

public interface AuthService {
    public RegisterResult register(String email, String fullName, String password, String passwordVerify);

    public String login(String email, String password) throws Exception;
}

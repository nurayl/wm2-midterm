package az.ada.library.Controllers.dtos;

public class AuthLoginResponse {
    public String accessToken;

    public AuthLoginResponse(String accessToken) {
        this.accessToken = accessToken;
    }
}

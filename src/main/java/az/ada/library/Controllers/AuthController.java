package az.ada.library.Controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import az.ada.library.Controllers.dtos.AuthLoginRequest;
import az.ada.library.Controllers.dtos.AuthLoginResponse;
import az.ada.library.Controllers.dtos.AuthRegisterRequest;
import az.ada.library.Controllers.dtos.AuthRegisterResponse;
import az.ada.library.Controllers.dtos.ErrorResponse;
import az.ada.library.Models.User;
import az.ada.library.Services.AuthService;
import az.ada.library.Services.dtos.RegisterResult;

@RestController
public class AuthController {
	
	@Autowired
	AuthService authService;
	
	Logger logger = LoggerFactory.getLogger(AuthController.class);

	@PostMapping({ "/auth/register" })
	public ResponseEntity<?> register(@RequestBody AuthRegisterRequest request) {

		RegisterResult result = authService.register(request.getEmail(), request.getFullName(), request.getPassword(),
				request.getPasswordVerify());

		String err = result.getErrorMessage();
		if (err != null) {
			return ResponseEntity.badRequest().body(new ErrorResponse(err));
		}
		User user = result.getUser();
		return ResponseEntity.ok(new AuthRegisterResponse(user.getEmail(), user.getFullName()));
	}

	@PostMapping(value = "/auth/login")
	public ResponseEntity<?> login(@RequestBody AuthLoginRequest request) {

		try {
			String accToken = authService.login(request.getEmail(), request.getPassword());
			return ResponseEntity.ok(new AuthLoginResponse(accToken));
		} catch (Exception e) {
			logger.error("unsuccesful login for " + request.getEmail(), e);
			return ResponseEntity.status(401).body(new ErrorResponse("Login failed"));
		}
	}
}

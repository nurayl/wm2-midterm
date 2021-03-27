package az.ada.library.Services.Impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import az.ada.library.Models.User;
import az.ada.library.Repositories.UserRepository;
import az.ada.library.Security.JwtUtil;
import az.ada.library.Services.AuthService;
import az.ada.library.Services.dtos.RegisterResult;

@Service
public class AuthServiceImpl implements AuthService {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    UserRepository userRepository;

    @Autowired
    AuthenticationManager authManager;

    @Autowired
	JwtUtil jwtUtil;

    Logger logger = LoggerFactory.getLogger(AuthServiceImpl.class);

    public RegisterResult register(String email, String fullName, String password, String passwordVerify) {
        if (!password.equals(passwordVerify)) {
            return new RegisterResult(null, "Passwords not match");
        }
        if (userRepository.existsByEmail(email)) {
            return new RegisterResult(null, "email already exists");
        }

        User user = new User(email, fullName, passwordEncoder.encode(password));
        userRepository.save(user);
        logger.info("user registered " + user.getEmail());

        return new RegisterResult(user, null);
    }

    public String login(String email, String password) throws Exception {

        Authentication authentication = authManager
                .authenticate(new UsernamePasswordAuthenticationToken(email, password));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        // create new token
        String accessToken = jwtUtil.generateJwtToken(authentication);

        return accessToken;
    }
}

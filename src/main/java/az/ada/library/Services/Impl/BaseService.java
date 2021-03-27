package az.ada.library.Services.Impl;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import az.ada.library.Security.services.UserDetailsImpl;

public class BaseService {

    // get userId from current context
    protected Long getCurrentUserId() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userDetails = (UserDetailsImpl) auth.getPrincipal();
        long userId = userDetails.getId();

        return userId;
    }
}

package ru.dovion.projectmanager.auth;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

public class SecureUtil {
    public SecureUtil() {
    }

    public static UserDetails getActiveUserDetails() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (userDetails != null) {
            return userDetails;
        }

        throw new SecurityException("Авторизация отсутствует");
    }
}

package com.example.test.utils;


import com.example.test.enums.UserRole;
import com.example.test.service.user.UserService;
import com.raika.customexception.exceptions.CustomException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.UUID;


@Component
public class UserUtil {
    private final UserService userService;

    public UserUtil(UserService userService) {
        this.userService = userService;
    }

    public boolean checkUser(UUID userId) {
        var userRole = userService.getUserRoleById(userId);
        return (userRole != null && userRole.equals(UserRole.User));
    }

    public boolean checkAdmin(UUID userId) {
        var userRole = userService.getUserRoleById(userId);
        return (userRole != null && userRole.equals(UserRole.Admin));
    }

    public void isUser(UUID userId) {
        if (!checkUser(userId)) {
            throw new CustomException.NewException("UnAuthorized", HttpStatus.UNAUTHORIZED);
        }
    }

    public void isAdmin(UUID userId) {
        if (!checkAdmin(userId)) {
            throw new CustomException.NewException("UnAuthorized", HttpStatus.UNAUTHORIZED);
        }
    }
}
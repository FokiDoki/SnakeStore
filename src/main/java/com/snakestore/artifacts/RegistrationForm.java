package com.snakestore.artifacts;

import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@Component
public class RegistrationForm {
    @Size(min= 2, message="Username must be at least 2 characters long")
    private String username;
    @Size(min= 8, message="Password must be at least 8 characters long")
    private String password;
    private String passwordConfirm;

    public User toUser(PasswordEncoder passwordEncoder) {
        if (!password.equals(passwordConfirm)) {
            throw new IllegalArgumentException("Passwords do not match");
        }
        return new User(username, passwordEncoder.encode(password));
    }
}

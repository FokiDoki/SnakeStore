package com.snakestore.controllers;

import com.snakestore.artifacts.RegistrationForm;
import com.snakestore.artifacts.User;
import com.snakestore.database.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/signup")
public class RegistrationController {
    UserRepository userRepository;
    PasswordEncoder passwordEncoder;

    @Autowired
    public RegistrationController(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping()
    public String registration() {
        return "registration";
    }
    @PostMapping
    public String addUser(@Valid RegistrationForm form){

        try {
            User newUser = form.toUser(passwordEncoder);
            if (userRepository.existsByUsername(newUser.getUsername()))
                return "redirect:/signup?usernameAlreadyExists";
            userRepository.save(newUser);
        } catch (IllegalArgumentException e) {
            return "redirect:/signup?passwordsDoNotMatch";
        }


        return "redirect:/login?registrationSuccess";
    }
}

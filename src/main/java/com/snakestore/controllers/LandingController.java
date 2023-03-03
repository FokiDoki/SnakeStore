package com.snakestore.controllers;

import com.snakestore.artifacts.LoginPassEntity;
import com.snakestore.database.LoginPassRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/")
public class LandingController {
    LoginPassRepository loginPassRepository;

    @Autowired
    public LandingController(LoginPassRepository loginPassRepository) {
        this.loginPassRepository = loginPassRepository;
    }


    @GetMapping
    public String landingPage(){
        return "storage";
    }

    @PostMapping
    public String AddEntity(LoginPassEntity entity, Principal principal){
        entity.setUserName(principal.getName());
        entity.setLastPasswordChange(System.currentTimeMillis());
        loginPassRepository.save(entity);
        return "storage";
    }
}

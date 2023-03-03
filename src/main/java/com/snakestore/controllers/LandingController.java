package com.snakestore.controllers;

import com.snakestore.artifacts.LoginPassEntity;
import com.snakestore.database.LoginPassRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
    @ModelAttribute(name="keypairs")
    public List<LoginPassEntity> addUsersToModel() {
        List<LoginPassEntity> loginPassEntities = new ArrayList<LoginPassEntity>();
        loginPassRepository.findAll().forEach(loginPassEntities::add);
        return loginPassEntities;
    }

    @PostMapping
    public String AddEntity(LoginPassEntity entity, Principal principal){
        entity.setUserName(principal.getName());
        entity.setLastPasswordChange(System.currentTimeMillis());
        loginPassRepository.save(entity);
        return "redirect:/";
    }

    @PostMapping("/delete")
    public String deleteEntity(String entityId){
        Optional<LoginPassEntity> entity= loginPassRepository.findById(entityId);
        if (entity.isPresent()){
            loginPassRepository.delete(entity.get());
        }
        return "redirect:/";
    }

        @PostMapping("/passwordUpdate")
    public String updatePassword(@RequestParam("password") String newPassword, String entityId){
        Optional<LoginPassEntity> entityOptional= loginPassRepository.findById(entityId);
        if (entityOptional.isPresent()){
            LoginPassEntity entity = entityOptional.get();
            entity.setPassword(newPassword);
            entity.setLastPasswordChange(System.currentTimeMillis());
            loginPassRepository.save(entity);
        }
        return "redirect:/";
    }
}

package com.snakestore.artifacts;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.w3c.dom.Entity;

import java.util.Map;

@Data
public class LoginPassEntity {
    @Id
    private String id;
    private String login;
    private String password;
    private int lastPasswordChange;

}

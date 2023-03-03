package com.snakestore.artifacts;

import lombok.Data;
import org.w3c.dom.Entity;

import java.util.Map;

@Data
public class LoginPassEntity {
    private String login;
    private String password;
    private int lastPasswordChange;

}

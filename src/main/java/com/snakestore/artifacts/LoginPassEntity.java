package com.snakestore.artifacts;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.w3c.dom.Entity;

import java.util.Map;

@Data
@Document(collection = "entities")
public class LoginPassEntity {
    @Id
    private String id;
    private String login;
    private String password;
    private long lastPasswordChange;
    private String userName;

}

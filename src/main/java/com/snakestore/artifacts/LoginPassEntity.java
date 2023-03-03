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

    public String getWhenLastChangedAgoReadable(){
        long currentTime = System.currentTimeMillis();
        long timePassed = currentTime - lastPasswordChange;
        long secondsPassed = timePassed / 1000;
        long minutesPassed = secondsPassed / 60;
        long hoursPassed = minutesPassed / 60;
        long daysPassed = hoursPassed / 24;
        long monthsPassed = daysPassed / 30;
        long yearsPassed = monthsPassed / 12;
        if (yearsPassed > 0){
            return yearsPassed + " years ago";
        }
        if (monthsPassed > 0){
            return monthsPassed + " months ago";
        }
        if (daysPassed > 0){
            return daysPassed + " days ago";
        }
        if (hoursPassed > 0){
            return hoursPassed + " hours ago";
        }
        if (minutesPassed > 0){
            return minutesPassed + " minutes ago";
        }
        if (secondsPassed > 0){
            return secondsPassed + " seconds ago";
        }
        return "just now";
    }

}

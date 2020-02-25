package com.easyride.easyrideuserservice.model;

import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

@Component
@Document(collection = "User")
public class User {

    private String name;
    @Indexed(unique = true)
    private String email;
    private String avatarURL;
    private boolean isLoggedIn = false;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAvatarURL() {
        return avatarURL;
    }

    public void setAvatarURL(String avatarURL) {
        this.avatarURL = avatarURL;
    }

    public boolean isLoggedIn() {
        return isLoggedIn;
    }

    public void setLoggedIn(boolean isLoggedIn) {
        this.isLoggedIn = isLoggedIn;
    }

    public User(String name, String email, String avatarURL, boolean isLoggedIn) {
        super();
        this.name = name;
        this.email = email;
        this.avatarURL = avatarURL;
        this.isLoggedIn = isLoggedIn;
    }

    public User() {
        super();
    }

}


package com.constructionmanager.manager.model;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Component
public class SessionContext {
    private User currentUser;
    private String authToken;
    private LocalDateTime loginTime;

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    public String getAuthToken() {return authToken;}
    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    public LocalDateTime getLoginTime() {return loginTime;}
    public void setLoginTime(LocalDateTime loginTime) {
        this.loginTime = loginTime;
    }

    public void startSession(User user, String authToken) {
        this.currentUser = user;
        this.authToken = authToken;
        this.loginTime = LocalDateTime.now();
    }

    public void clear() {
        this.currentUser = null;
        this.authToken = null;
        this.loginTime = null;
    }

    public boolean isLoggedIn() {
        return currentUser != null;
    }

}

package com.constructionmanager.manager.service;

import com.constructionmanager.manager.model.SessionContext;
import com.constructionmanager.manager.model.User;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final SessionContext sessionContext;

    public AuthService(SessionContext sessionContext) {
        this.sessionContext = sessionContext;
    }

    public void login(User user, String token) {
        sessionContext.startSession(user, token);
    }

    public void logout() {
        sessionContext.clear();
    }

}

package com.constructionmanager.manager.model;

import com.constructionmanager.manager.ui.MainApp;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class Navigator {
    private final StageManager stageManager;
    private final SessionContext sessionContext;

    public Navigator(
            StageManager stageManager,
            SessionContext sessionContext) {
        this.stageManager = stageManager;
        this.sessionContext = sessionContext;
    }

    public void goToLogin() {
    }

    public void navigateTo(Route route) {
        if (route.isProtectedRoute() && !sessionContext.isLoggedIn()) {
            route = Route.LOGIN;
        }
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(route.getFxmlPath()));
            loader.setControllerFactory(MainApp.springContext::getBean);
            Parent root = loader.load();
            stageManager.setSceneRoot(root);
        } catch (IOException e) {
            throw new RuntimeException("Failed to navigate to " + route.name(), e);
        }

    }
}

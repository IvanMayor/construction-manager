package com.constructionmanager.manager.ui.controllers;

import com.constructionmanager.manager.ui.MainApp;
import com.constructionmanager.manager.ui.layout.MainLayout;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
public class NavigationController {
    private Stage stage;
    private Scene scene;
    private Parent root;
    private Map<String, Scene> views = new HashMap<>();

    private Scene loadView(String fxml, ActionEvent event) throws IOException {
        if (!views.containsKey(fxml)) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/" + fxml));
            loader.setControllerFactory(MainApp.springContext::getBean);
            root = loader.load();
            scene = new Scene(root);
            views.put(fxml, scene);
        } else {
            scene = views.get(fxml);
        }

        stage = (Stage) (((Node) event.getSource()).getScene().getWindow());
        stage.setScene(scene);

        return views.get(fxml);

    }

    public void switchToProjectScene(ActionEvent event) throws IOException {
        loadView("ProjectsView.fxml", event);
        stage.show();
    }

    public void switchToEmployeeScene(ActionEvent event) throws IOException {
        loadView("EmployeeView.fxml", event);
        stage.show();
    }
}

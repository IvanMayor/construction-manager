package com.constructionmanager.manager.ui.components;

import com.constructionmanager.manager.service.ProjectService;
import com.constructionmanager.manager.ui.MainApp;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class Sidebar extends VBox {

    private final ProjectService projectService = MainApp.springContext.getBean(ProjectService.class);

    public Sidebar() {

        Button projects = new Button("Projects");
        Button employees = new Button("Employee");

        projects.setOnAction(e -> getProjectScene());

        getChildren().addAll(projects, employees);

    }

    public Node getProjectScene() {
        return null;
    };

}

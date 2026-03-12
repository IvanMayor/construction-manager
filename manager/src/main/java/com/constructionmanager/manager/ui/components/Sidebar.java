package com.constructionmanager.manager.ui.components;

import com.constructionmanager.manager.service.ProjectService;
import com.constructionmanager.manager.ui.MainApp;
import com.constructionmanager.manager.ui.layout.MainLayout;
import com.constructionmanager.manager.ui.view.*;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class Sidebar extends HBox{
    public Sidebar() {

        Button projects = new Button("Projects");
        Button employees = new Button("Employee");

        projects.setOnAction(e -> {
            this.getChildren().add(new ProjectsView());
        });
        employees.setOnAction(e -> {
            this.getChildren().add(new EmployeeView());
        });

        getChildren().addAll(projects, employees);

    }
}

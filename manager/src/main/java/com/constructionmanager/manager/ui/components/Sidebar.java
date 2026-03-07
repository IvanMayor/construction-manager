package com.constructionmanager.manager.ui.components;

import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class Sidebar extends VBox {
    public Sidebar() {
        Button projects = new Button("Projects");
        Button employees = new Button("Employee");

        getChildren().addAll(projects, employees);
    }
}

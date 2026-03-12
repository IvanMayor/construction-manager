package com.constructionmanager.manager.ui.view;

import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class DashboardView extends VBox {
    public DashboardView() {

        getChildren().clear();
        getChildren().add(new ProjectsView());
    }

    public void setProjectView() {
        this.getChildren().clear();
        this.getChildren().add(new ProjectsView());
    }

    public void setEmployeeView() {
        this.getChildren().clear();
        this.getChildren().add(new EmployeeView());
    }
}

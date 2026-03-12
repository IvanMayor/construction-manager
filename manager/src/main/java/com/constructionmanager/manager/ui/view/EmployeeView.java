package com.constructionmanager.manager.ui.view;

import com.constructionmanager.manager.ui.components.Sidebar;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class EmployeeView extends BorderPane {

    public EmployeeView() {
        getChildren().clear();
        Label label = new Label("Employee View");
        label.setFont(Font.font("Arial", FontWeight.BOLD, 36));

        getChildren().add(label);
    }

}

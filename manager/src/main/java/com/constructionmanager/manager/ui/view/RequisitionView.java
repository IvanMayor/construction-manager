package com.constructionmanager.manager.ui.view;

import com.constructionmanager.manager.ui.components.Sidebar;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class RequisitionView extends VBox {
    public RequisitionView() {
        Label label = new Label("This is requisition View");
        label.setFont(Font.font("Arial", FontWeight.BOLD, 36));

        getChildren().add(label);

        BorderPane borderPane = new BorderPane();
        borderPane.setTop(new Sidebar());
        borderPane.setCenter(label);

        getChildren().add(borderPane);
    }
}

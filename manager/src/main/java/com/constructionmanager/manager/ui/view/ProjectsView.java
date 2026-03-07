package com.constructionmanager.manager.ui.view;

import com.constructionmanager.manager.model.Projects;
import com.constructionmanager.manager.service.ProjectService;
import com.constructionmanager.manager.ui.MainApp;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.util.List;

public class ProjectsView extends VBox {

    private final ProjectService projectService = MainApp.springContext.getBean(ProjectService.class);

    private BorderPane setCreateButton() {
        BorderPane createContainer = new BorderPane();

        Button createButton = new Button("Create");
        TextField nameField = new TextField("Type in name of the project");
        TextField addressField = new TextField("Type in project address");

        createContainer.setTop(nameField);
        createContainer.setCenter(addressField);
        createContainer.setBottom(createButton);

        return createContainer;
    }

    public ProjectsView() {

        setSpacing(10);
        setAlignment(Pos.CENTER);

        List<Projects> projectsList = projectService.getAllProjects();

        getChildren().add(setCreateButton());

        for (Projects project : projectsList) {
            String text = "ID: " + project.getId() +
                    " | " + project.getName() +
                    " | " + project.getAddress();

            Text textElement = new Text(text);
            textElement.setFont(Font.font("Times", 20));

            getChildren().add(textElement);
        }


    }
}

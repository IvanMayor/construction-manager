package com.constructionmanager.manager.ui.view;

import com.constructionmanager.manager.model.Projects;
import com.constructionmanager.manager.service.ProjectService;
import com.constructionmanager.manager.ui.MainApp;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import org.springframework.http.HttpStatus;

import java.time.LocalDate;
import java.util.List;

public class ProjectsView extends VBox {

    private final ProjectService projectService = MainApp.springContext.getBean(ProjectService.class);

    private TableView<Projects> tableView = new TableView<>();


    private BorderPane setCreateConstruction() {
        BorderPane createContainer = new BorderPane();
        DatePicker dateStarted = new DatePicker();
        DatePicker dateFinished = new DatePicker();
        ComboBox<Projects.JobType> jobTypeComboBox = new ComboBox<>();

        HBox hBoxName = new HBox();
        HBox hBoxAddress = new HBox();

        Label nameLabel = new Label("Project Name");
        Label addressLabel = new Label("Project Address");

        TextField nameField = new TextField();
        TextField addressField = new TextField();

        hBoxName.getChildren().addAll(nameLabel, nameField);
        hBoxAddress.getChildren().addAll(addressLabel, addressField);

        dateStarted.setPromptText("Select Projects Started");
        dateFinished.setPromptText("Select Project Finished");

        jobTypeComboBox.getItems().addAll(Projects.JobType.values());

        Button createButton = new Button("Create");

        createButton.setOnAction(e -> performProjectCreate(
                nameField.getText(),
                addressField.getText(),
                jobTypeComboBox.getValue(),
                dateStarted.getValue(),
                dateFinished.getValue() ));

        VBox vBox = new VBox();
        vBox.getChildren().addAll(hBoxName, hBoxAddress, jobTypeComboBox, dateStarted, dateFinished, createButton);

        createContainer.setCenter(vBox);


        return createContainer;
    }

    public ProjectsView() {

        setSpacing(10);
        setAlignment(Pos.CENTER);

        setUpTableView();

        getAllProjects();
        getChildren().add(setCreateConstruction());
        getChildren().add(tableView);



    }

    public void setUpTableView() {
        TableColumn<Projects, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<Projects, String> addressColumn = new TableColumn<>("Address");
        addressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));

        tableView.getColumns().addAll(nameColumn, addressColumn);
    }

    private void getAllProjects() {
        List<Projects> projectsList = projectService.getAllProjects();
        tableView.getItems().setAll(projectsList);
    }

    private HttpStatus performProjectCreate(
            String projectName,
            String projectAddress,
            Projects.JobType jobType,
            LocalDate dateStarted,
            LocalDate dateFinished) {
        projectService.createProject(
                projectName != null && !projectName.isBlank() ? projectName : null,
                projectAddress != null && !projectAddress.isBlank() ? projectAddress : null,
                jobType != null ? jobType : null,
                dateStarted != null ? dateStarted : null,
                dateFinished != null ? dateFinished : null,
                null
        );

        getAllProjects();

        return HttpStatus.CREATED;
    }

}

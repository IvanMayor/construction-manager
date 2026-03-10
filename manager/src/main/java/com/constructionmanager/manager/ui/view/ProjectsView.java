package com.constructionmanager.manager.ui.view;

import com.constructionmanager.manager.model.Projects;
import com.constructionmanager.manager.service.ProjectService;
import com.constructionmanager.manager.ui.MainApp;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import org.springframework.cglib.core.Local;
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

        TextField nameField = new TextField();
        nameField.setPromptText("Project Name");
        nameField.setMaxWidth(256);

        TextField addressField = new TextField();
        addressField.setPromptText("Project Address");
        addressField.setMaxWidth(256);

        dateStarted.setPromptText("Select Projects Started");
        dateFinished.setPromptText("Select Project Finished");

        jobTypeComboBox.getItems().addAll(Projects.JobType.values());
        jobTypeComboBox.setPromptText("Select Project Type");

        Button createButton = new Button("Create");

        createButton.setOnAction(e -> performProjectCreate(
                nameField.getText(),
                addressField.getText(),
                jobTypeComboBox.getValue(),
                dateStarted.getValue(),
                dateFinished.getValue() ));

        VBox vBox = new VBox();
        vBox.setSpacing(5);
        vBox.getChildren().addAll(nameField, addressField, jobTypeComboBox, dateStarted, dateFinished, createButton);

//        VBox.setMargin(nameField, new Insets(5,0,0,0));

        vBox.setStyle("-fx-background-color: #999999");
        createContainer.setStyle("-fx-background-color: #A9A9A9");

        BorderPane.setMargin(vBox, new Insets(5, 0, 0, 5));

        createContainer.setPrefWidth(1280);
        createContainer.setPrefHeight(720);


        createContainer.setTop(vBox);


        return createContainer;
    }

    public ProjectsView() {
        tableView.setEditable(true);
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
        nameColumn.setCellFactory(TextFieldTableCell.forTableColumn());

        TableColumn<Projects, String> addressColumn = new TableColumn<>("Address");
        addressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));
        addressColumn.setCellFactory(TextFieldTableCell.forTableColumn());

        TableColumn<Projects, Projects.JobType> jobTypeTableColumn = new TableColumn<>("Job Type");
        jobTypeTableColumn.setCellValueFactory(new PropertyValueFactory<>("jobType"));
        jobTypeTableColumn.setCellFactory(ComboBoxTableCell.forTableColumn(Projects.JobType.values()));

        TableColumn<Projects, LocalDate> dateStartedColumn = new TableColumn<>("Date Started");
        dateStartedColumn.setCellValueFactory(new PropertyValueFactory<>("dateStarted"));
        dateStartedColumn.setCellFactory(column -> new TableCell<Projects, LocalDate>() {

            private final DatePicker datePicker = new DatePicker();

            {
                datePicker.setOnAction(event -> {
                    commitEdit(datePicker.getValue());
                });
            }

            @Override
            protected void updateItem(LocalDate item, boolean empty) {
                super.updateItem(item, empty);

                if (empty) {
                    setText(null);
                    setGraphic(null);
                } else {
                    datePicker.setValue(item);
                    setGraphic(datePicker);
                    setText(null);
                }
            }
        });

        TableColumn<Projects, LocalDate> dateFinishedColumn = new TableColumn<>("Date Finished");
        dateFinishedColumn.setCellValueFactory(new PropertyValueFactory<>("dateFinished"));
        dateFinishedColumn.setCellFactory(column -> new TableCell<Projects, LocalDate>() {

            private final DatePicker datePicker = new DatePicker();

            {
                datePicker.setOnAction(event -> {
                    commitEdit(datePicker.getValue());
                });
            }

            @Override
            protected void updateItem(LocalDate item, boolean empty) {
                super.updateItem(item, empty);

                if (empty) {
                    setText(null);
                    setGraphic(null);
                } else {
                    datePicker.setValue(item);
                    setGraphic(datePicker);
                    setText(null);
                }
            }
        });

        TableColumn<Projects, Integer> idColumn = new TableColumn<>("ID");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));

        nameColumn.setOnEditCommit(event -> {
            Projects project = event.getRowValue();
            String newValue = event.getNewValue();

            if (newValue != null && !newValue.trim().isEmpty()) {
                project.setName(newValue.trim());
                projectService.updateProject(project.getId(), project);
            } else {
                project.setName(event.getOldValue());
                tableView.refresh();
            }

        });

        addressColumn.setOnEditCommit(event -> {
           Projects project = event.getRowValue();
           String newValue = event.getNewValue();

           if (newValue != null && !newValue.trim().isEmpty()) {
               project.setAddress(newValue.trim());
               projectService.updateProject(project.getId(), project);
           } else {
               project.setName(event.getOldValue());
               tableView.refresh();
           }
        });

        dateStartedColumn.setOnEditCommit(event -> {

            Projects project = event.getRowValue();
            LocalDate newDate = event.getNewValue();

            project.setDateStarted(newDate);

            projectService.updateProject(project.getId(), project);

        });

        dateFinishedColumn.setOnEditCommit(event -> {

            Projects project = event.getRowValue();
            LocalDate newDate = event.getNewValue();

            project.setDateStarted(newDate);

            projectService.updateProject(project.getId(), project);

        });



        tableView.getColumns().addAll(idColumn, nameColumn, addressColumn, jobTypeTableColumn, dateStartedColumn, dateFinishedColumn);
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

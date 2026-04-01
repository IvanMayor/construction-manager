package com.constructionmanager.manager.ui.controllers;

import com.constructionmanager.manager.model.Projects;
import com.constructionmanager.manager.model.SessionContext;
import com.constructionmanager.manager.service.ProjectService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import com.constructionmanager.manager.ui.MainApp;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ProjectTableViewController {

    private final ProjectService projectService;
    private final SessionContext sessionContext;

    private Parent root;
    private Scene scene;
    private Stage stage;
    private String id;

    public ProjectTableViewController(
            ProjectService projectService,
            SessionContext sessionContext) {
        this.projectService = projectService;
        this.sessionContext = sessionContext;
    }

    @FXML
    private TableView<Projects> projectsTable;
    @FXML
    private TableColumn<Projects, Integer> idColumn;
    @FXML
    private TableColumn<Projects, String> nameColumn;
    @FXML
    private TableColumn<Projects, String> addressColumn;
    @FXML
    private TableColumn<Projects, String> jobTypeColumn;
    @FXML
    private TableColumn<Projects, java.time.LocalDate> dateStartedColumn;
    @FXML
    private TableColumn<Projects, java.time.LocalDate> dateFinishedColumn;
    @FXML
    private TableColumn<Projects, Void> editProjectButton;
    @FXML
    private TableColumn<Projects, Void> deleteProjectButton;

    private void loadProjects() {
        ObservableList<Projects> projects = FXCollections.observableArrayList(projectService.getAllProjects());
    }

    public void refreshTable() {
        projectsTable.getItems().setAll(projectService.getAllProjects());
    }

    @FXML
    public void initialize() {

        editProjectButton.setCellFactory(param -> new TableCell<>() {
            private final Button editButton = new Button("Edit");
            {
                editButton.setOnAction(event -> {
                    Projects project = getTableView().getItems().get(getIndex());
                    switchToProjectDetailView(project);
                });
                editButton.setPrefWidth(65);
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);

                if (empty) {
                    setGraphic(null);
                } else {
                    setGraphic(editButton);
                }
            }
        });

        deleteProjectButton.setCellFactory(param -> new TableCell<>() {
            private final Button deleteButton = new Button("Delete");
            {
                deleteButton.setOnAction(e -> {
                    Projects project = getTableView().getItems().get(getIndex());
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
                            "Are you sure you want to delete " + project.getAddress() + "?");
                    Optional<ButtonType> result = alert.showAndWait();
                    if (result.isPresent() && result.get() == ButtonType.OK) {
                        projectService.deleteProject(project.getId());
                        refreshTable();
                    } else {
                        Alert alert1 = new Alert(Alert.AlertType.INFORMATION,
                                "Be carefull next time please, I almost deleted it!");
                        alert1.showAndWait();
                    }
                    deleteButton.setPrefWidth(65);
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                if (empty) {
                    setGraphic(null);
                } else {
                    setGraphic(deleteButton);
                }
            }
        });

        projectsTable.getItems().setAll(projectService.getAllProjects());

        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        addressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));
        jobTypeColumn.setCellValueFactory(new PropertyValueFactory<>("jobType"));
        dateStartedColumn.setCellValueFactory(new PropertyValueFactory<>("dateStarted"));
        dateFinishedColumn.setCellValueFactory(new PropertyValueFactory<>("dateFinished"));

        loadProjects();
    }

    public void switchToProjectDetailView(Projects project) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/ProjectDetailView.fxml"));
            loader.setControllerFactory(MainApp.springContext::getBean);

            root = loader.load();

            ProjectDetailController projectDetailController = loader.getController();
            projectDetailController.setupProjectDetail(project);

            stage = (Stage) projectsTable.getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

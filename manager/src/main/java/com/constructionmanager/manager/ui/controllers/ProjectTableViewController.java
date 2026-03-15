package com.constructionmanager.manager.ui.controllers;

import com.constructionmanager.manager.model.Projects;
import com.constructionmanager.manager.service.ProjectService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.springframework.boot.SpringApplication;
import com.constructionmanager.manager.ui.MainApp;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class ProjectTableViewController {

    private final ProjectService projectService;
    private Parent root;
    private Scene scene;
    private Stage stage;
    private String id;

    public ProjectTableViewController(ProjectService projectService, ProjectDetailController projectDetailController) {
        this.projectService = projectService;
    }

    @FXML private TableView<Projects> projectsTable;
    @FXML private TableColumn<Projects, Integer> idColumn;
    @FXML private TableColumn<Projects, String> nameColumn;
    @FXML private TableColumn<Projects, String> addressColumn;
    @FXML private TableColumn<Projects, String> jobTypeColumn;
    @FXML private TableColumn<Projects, java.time.LocalDate> dateStartedColumn;
    @FXML private TableColumn<Projects, java.time.LocalDate> dateFinishedColumn;

    private void loadProjects() {
        ObservableList<Projects> projects = FXCollections.observableArrayList(projectService.getAllProjects());
    }

    public void refreshTable() {
        projectsTable.getItems().setAll(projectService.getAllProjects());
    }

    @FXML
    public void initialize() {
        nameColumn.setEditable(true);
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        nameColumn.setCellFactory(col -> {
            TableCell<Projects, String> cell = new TableCell<>() {
                private final Hyperlink link = new Hyperlink();

                {
                    link.setOnAction(e -> {
                        Projects project = getTableView().getItems().get(getIndex());
                        try {
                            switchToProjectDetailView(project);
                        } catch (IOException exception) {
                            exception.printStackTrace();
                        }
                    });
                }
            };
            return cell;
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

    public void switchToProjectDetailView(Projects project) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/ProjectDetailView.fxml"));

        ProjectDetailController projectDetailController = loader.getController();
        projectDetailController.initialize(project);

        scene = new Scene(loader.load());
        stage = (Stage) projectsTable.getScene().getWindow();
        stage.setScene(scene);
        stage.show();

//        Projects project = projectService.getProjectById(Integer.parseInt(idColumn.getText()));


//        System.out.println(project.getName() + "--------------------------------");

//        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/ProjectDetailView.fxml"));
//        root = loader.load();

//        ProjectDetailController projectDetailController = loader.getController();
//        projectDetailController.initialize(project);

//        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//        scene = new Scene(root);
//        stage.setScene(scene);
//        stage.show();
    }

}

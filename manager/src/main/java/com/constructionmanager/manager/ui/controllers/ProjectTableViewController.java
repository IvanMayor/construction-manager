package com.constructionmanager.manager.ui.controllers;

import com.constructionmanager.manager.model.Projects;
import com.constructionmanager.manager.service.ProjectService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.springframework.boot.SpringApplication;
import com.constructionmanager.manager.ui.MainApp;
import org.springframework.stereotype.Component;

@Component
public class ProjectTableViewController {

    private final ProjectService projectService;

    public ProjectTableViewController(ProjectService projectService) {
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
        projectsTable.getItems().setAll(projectService.getAllProjects());

        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        addressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));
        jobTypeColumn.setCellValueFactory(new PropertyValueFactory<>("jobType"));
        dateStartedColumn.setCellValueFactory(new PropertyValueFactory<>("dateStarted"));
        dateFinishedColumn.setCellValueFactory(new PropertyValueFactory<>("dateFinished"));

        loadProjects();
    }



}

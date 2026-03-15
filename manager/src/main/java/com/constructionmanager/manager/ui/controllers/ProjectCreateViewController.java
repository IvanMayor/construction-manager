package com.constructionmanager.manager.ui.controllers;

import com.constructionmanager.manager.model.Projects;
import com.constructionmanager.manager.service.ProjectService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class ProjectCreateViewController {
    private final ProjectService projectService;
    private final ProjectTableViewController projectTableViewController;

    @FXML
    private TextField projectName;

    @FXML
    private TextField projectAddress;

    @FXML
    private ComboBox<Projects.JobType> projectType;

    @FXML
    private DatePicker projectStartDate;

    @FXML
    private DatePicker projectFinishDate;

    public ProjectCreateViewController(ProjectService projectService, ProjectTableViewController projectTableViewController) {
        this.projectService = projectService;
        this.projectTableViewController = projectTableViewController;
    }

    @FXML
    public void initialize() {
        projectType.getItems().addAll(Projects.JobType.values());
    }

    @FXML
    public void createProjectView(ActionEvent event) {
        String name = projectName.getText();
        String address = projectAddress.getText();
        Projects.JobType jobType = projectType.getValue();
        LocalDate dateStarted = projectStartDate.getValue();
        LocalDate dateFinished = projectFinishDate.getValue();

        projectService.createProject(
                name != null && !name.isBlank() ? name : null,
                address != null && !address.isBlank() ? address : null,
                jobType != null ? jobType : null,
                dateStarted != null ? dateStarted : null,
                dateFinished != null ? dateFinished : null,
                null);

        projectTableViewController.refreshTable();
    }

}

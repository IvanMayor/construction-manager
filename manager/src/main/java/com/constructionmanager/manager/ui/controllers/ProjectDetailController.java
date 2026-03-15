package com.constructionmanager.manager.ui.controllers;

import com.constructionmanager.manager.model.Projects;
import com.constructionmanager.manager.service.ProjectService;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import org.springframework.stereotype.Component;

@Component
public class ProjectDetailController {

    private final ProjectService projectService;

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

    public ProjectDetailController(ProjectService projectService) {
        this.projectService = projectService;
    }

    public void initialize(Projects project) {
        setupProjectDetail(project);
    }

    @FXML
    private void setupProjectDetail(Projects project) {
        projectName.setText(project.getName());
        projectAddress.setText(project.getAddress());
        projectType.setValue(project.getJobType());
        projectStartDate.setValue(project.getDateStarted());
        projectFinishDate.setValue(project.getDateFinished());
    }



}

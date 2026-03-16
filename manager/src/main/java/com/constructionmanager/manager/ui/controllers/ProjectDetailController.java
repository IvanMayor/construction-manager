package com.constructionmanager.manager.ui.controllers;

import com.constructionmanager.manager.model.Projects;
import com.constructionmanager.manager.service.ProjectService;
import com.constructionmanager.manager.ui.MainApp;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Optional;

@Component
public class ProjectDetailController {
    private Projects project;
    private Stage stage;
    private Parent root;
    private Scene scene;

    private final ProjectService projectService;

    @FXML private Label projectId;
    @FXML private TextField projectName;
    @FXML private TextField projectAddress;
    @FXML private ComboBox<Projects.JobType> projectType;
    @FXML private DatePicker projectStartDate;
    @FXML private DatePicker projectFinishDate;

    public ProjectDetailController(ProjectService projectService) {
        this.projectService = projectService;
    }

    public void setupProjectDetail(Projects project) {

        projectType.getItems().addAll(Projects.JobType.values());


        projectId.setText(String.valueOf(project.getId()));
        projectName.setText(project.getName());
        projectAddress.setText(project.getAddress());
        projectType.setValue(project.getJobType());
        projectStartDate.setValue(project.getDateStarted());
        projectFinishDate.setValue(project.getDateFinished());
    }

    public void backToAllProjects(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/ProjectsView.fxml"));
        loader.setControllerFactory(MainApp.springContext::getBean);

        root = loader.load();

        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void updateProject(ActionEvent event) {
        Projects project = new Projects(
                projectName.getText(),
                projectAddress.getText(),
                projectType.getValue(),
                projectStartDate.getValue(),
                projectFinishDate.getValue(),
                null);

        projectService.updateProject(Integer.parseInt(projectId.getText()), project);

        Alert alert = new Alert(Alert.AlertType.INFORMATION, "Project was successfully updated!!");
        alert.showAndWait();
    }



}

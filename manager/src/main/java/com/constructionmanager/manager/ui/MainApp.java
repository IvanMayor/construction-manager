package com.constructionmanager.manager.ui;

import com.constructionmanager.manager.ManagerApplication;
import com.constructionmanager.manager.model.Projects;
import com.constructionmanager.manager.service.ProjectService;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.ArrayList;
import java.util.List;

public class MainApp extends Application {

//    private static ConfigurableApplicationContext springContext;
//
//    @Override
//    public void init() {
//        springContext = SpringApplication.run(ManagerApplication.class);
//    }
//
//    private Parent createContent() {
//        return new StackPane(new Text("This is welcome screen"));
//    }
//
//    private Parent displayProjects() {
//        ProjectService projectService = springContext.getBean(ProjectService.class);
//
//        VBox projects = new VBox();
//
//        List<Projects> projectsList = projectService.getAllProjects();
//
//        for (Projects project : projectsList) {
//
//            String projectRepresentation = "Id-" + project.getId() + " " + project.getName() + " " + project.getAddress() + ".";
//
//            projects.getChildren().add(new StackPane(new Text(projectRepresentation)));
//        }
//        return projects;
//    }

    private void go() {
        Stage stage = new Stage();
        Group group = new Group();
        Scene scene = new Scene(group, 1280, 720, Color.DARKGRAY);

        stage.setScene(scene);
        stage.show();

    }

    @Override
    public void start(Stage stage) {
        go();
    }

    public static void main(String[] args) {
        launch(args);
    }

}

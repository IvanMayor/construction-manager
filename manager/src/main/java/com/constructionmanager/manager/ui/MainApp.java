package com.constructionmanager.manager.ui;

import com.constructionmanager.manager.ManagerApplication;
import com.constructionmanager.manager.model.Projects;
import com.constructionmanager.manager.service.ProjectService;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.ArrayList;
import java.util.List;

public class MainApp extends Application {

    private static ConfigurableApplicationContext springContext;

    @Override
    public void init() {
        springContext = SpringApplication.run(ManagerApplication.class);
    }

    private Parent createContent() {
        return new StackPane(new Text("This is welcome screen"));
    }

    private Parent displayProjects() {
        ProjectService projectService = springContext.getBean(ProjectService.class);

        VBox projects = new VBox();

        List<Projects> projectsList = projectService.getAllProjects();

        for (Projects project : projectsList) {

            String projectRepresentation = "Id-" + project.getId() + " " + project.getName() + " " + project.getAddress() + ".";

            projects.getChildren().add(new StackPane(new Text(projectRepresentation)));
        }
        return projects;
    }

    @Override
    public void start(Stage stage) {
        VBox root = new VBox(10);
        Scene scene = new Scene(root, 1920, 1080);
        stage.setTitle("Construction manager");
        stage.setScene(scene);
        stage.show();

        root.getChildren().add(createContent());

        root.getChildren().add(displayProjects());


//        Button button = new Button("Click me");
//
//        button.setOnAction(e -> {
//            System.out.println("Button clicked!");
//        });
//        root.getChildren().add(button);
    }

    public static void main(String[] args) {
        launch(args);
    }

}

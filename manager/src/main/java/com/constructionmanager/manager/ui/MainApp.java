package com.constructionmanager.manager.ui;

import com.constructionmanager.manager.ManagerApplication;
import com.constructionmanager.manager.model.Projects;
import com.constructionmanager.manager.service.ProjectService;
import javafx.application.Application;
import javafx.scene.Group;
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

    @Override
    public void start(Stage stage) {

        Group root = new Group();
        Scene scene = new Scene(root, 1920, 1080, Color.DARKGRAY);

        Text text = new Text();
        text.setText("This is welcome message");
        text.setX(50);
        text.setY(50);
        text.setFont(Font.font("Verdana", 50));
        text.setFill(Color.WHITE);

        Line line = new Line();
        line.setStartX(200);
        line.setStartY(200);
        line.setEndX(500);
        line.setEndY(200);
        line.setStrokeWidth(5);
        line.setStroke(Color.YELLOWGREEN);

        Rectangle rectangle = new Rectangle();
        rectangle.setX(100);
        rectangle.setY(100);
        rectangle.setWidth(100);
        rectangle.setHeight(100);
        rectangle.setFill(Color.BLUE);
        rectangle.setStrokeWidth(5);
        rectangle.setStroke(Color.WHITE);

        Polygon triangle = new Polygon();
        triangle.getPoints().setAll(
                200.0, 200.0,
                300.0, 300.0,
                200.0, 300.0);
        triangle.setFill(Color.YELLOW);

        Circle circle = new Circle();
        circle.setCenterX(350);
        circle.setCenterY(350);
        circle.setRadius(50);
        circle.setFill(Color.ORANGE);

        Image projectIcon = new Image(getClass().getResource("/icons/Projects-icon.png").toExternalForm());
        ImageView imageView = new ImageView(projectIcon);
        imageView.setX(400);
        imageView.setY(400);



        Image logo = new Image(getClass().getResource("/icons/Maramorosh-Digital-Logo-removebg.png").toExternalForm());
        stage.getIcons().add(logo);

        root.getChildren().add(text);
        root.getChildren().add(line);
        root.getChildren().add(rectangle);
        root.getChildren().add(triangle);
        root.getChildren().add(circle);
        root.getChildren().add(imageView);

        stage.setTitle("Construction Manage");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}

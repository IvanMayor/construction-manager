package com.constructionmanager.manager.ui;

import com.constructionmanager.manager.ManagerApplication;
import com.constructionmanager.manager.model.Projects;
import com.constructionmanager.manager.service.ProjectService;
import com.constructionmanager.manager.ui.layout.MainLayout;
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

    public static ConfigurableApplicationContext springContext;

    @Override
    public void init() {
        springContext = SpringApplication.run(ManagerApplication.class);
    }

    @Override
    public void start(Stage stage) {

        MainLayout root = new MainLayout();
        Scene scene = new Scene(root, 1280, 720);
        scene.setFill(Color.DARKGRAY);

        Image icon = new Image(getClass().getResource("/icons/Maramorosh-Digital-Logo-removebg.png").toExternalForm());
        stage.getIcons().add(icon);

        stage.setTitle("Construction Management System");
        stage.setScene(scene);

        stage.show();

    }

    @Override
    public void stop() {
        springContext.close();
    }

    public static void main(String[] args) {
        launch(args);
    }

}

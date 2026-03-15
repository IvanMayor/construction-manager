package com.constructionmanager.manager.ui;

import com.constructionmanager.manager.ManagerApplication;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import java.io.IOException;

public class MainApp extends Application {

    public static ConfigurableApplicationContext springContext;

    @Override
    public void init() {
        springContext = SpringApplication.run(ManagerApplication.class);
    }

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/ProjectsView.fxml"));

            loader.setControllerFactory(springContext::getBean);

            Parent root = loader.load();

            Scene scene = new Scene(root);
            scene.setFill(Color.DARKGRAY);

            Image icon = new Image(getClass().getResource("/icons/Maramorosh-Digital-Logo-removebg.png").toExternalForm());
            stage.getIcons().add(icon);

            stage.setTitle("Construction Management System");
            stage.setScene(scene);

            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void stop() {
        springContext.close();
    }

    public static void main(String[] args) {
        launch(args);
    }

}

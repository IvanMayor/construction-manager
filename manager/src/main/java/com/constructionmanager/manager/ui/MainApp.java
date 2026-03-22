package com.constructionmanager.manager.ui;

import com.constructionmanager.manager.ManagerApplication;
import com.constructionmanager.manager.model.Navigator;
import com.constructionmanager.manager.model.Route;
import com.constructionmanager.manager.model.SessionContext;
import com.constructionmanager.manager.model.StageManager;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.io.IOException;

public class MainApp extends Application {

    public static ConfigurableApplicationContext springContext;

    private Navigator navigator;

    @Override
    public void init() {
        springContext = SpringApplication.run(ManagerApplication.class);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Font.loadFont(getClass().getResourceAsStream("/fonts/Inter-Regular.ttf"), 14);
        Font.loadFont(getClass().getResourceAsStream("/fonts/Inter-Bold.ttf"), 14);
        Font.loadFont(getClass().getResourceAsStream("/fonts/Inter-SemiBold.ttf"), 14);

        StageManager stageManager = springContext.getBean(StageManager.class);
        stageManager.setPrimaryStage(stage);

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/user/LoginUserView.fxml"));
        loader.setControllerFactory(springContext::getBean);

        Parent root = loader.load();
        Scene scene = new Scene(root);

        stage.setTitle("Construction Manager System");
        stage.setScene(scene);
        stage.setMinWidth(1280);
        stage.setMinHeight(720);
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

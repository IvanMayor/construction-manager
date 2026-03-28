package com.constructionmanager.manager.ui.controllers;

import com.constructionmanager.manager.model.User;
import com.constructionmanager.manager.service.AuthService;
import com.constructionmanager.manager.service.UserService;
import com.constructionmanager.manager.ui.MainApp;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.springframework.stereotype.Component;

import java.io.IOException;


@Component
public class UserLoginView {
    private Parent root;
    private Scene scene;
    private Stage stage;


    private UserService userService;
    private AuthService authService;

    @FXML private TextField userLogin;
    @FXML private PasswordField userPassword;

    public UserLoginView(
            UserService userService,
            AuthService authService) {
        this.userService = userService;
        this.authService = authService;
    }

    @FXML
    public void performLoginButton(ActionEvent event) {
        String login = userLogin.getText();
        String password = userPassword.getText();
        if (userService.verifyUserLogin(login)) {
            User user = userService.login(login, password);
            // TODO: Implement token auth
            authService.login(user, "123");
            try {
                redirectToProjectsView();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void redirectToProjectsView() throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/ProjectsView.fxml"));
        loader.setControllerFactory(MainApp.springContext::getBean);
        root = loader.load();
        stage = (Stage) userLogin.getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void performRegistrationButton(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/user/RegisterUserView.fxml"));
        loader.setControllerFactory(MainApp.springContext::getBean);
        root = loader.load();
        stage = (Stage) userLogin.getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}

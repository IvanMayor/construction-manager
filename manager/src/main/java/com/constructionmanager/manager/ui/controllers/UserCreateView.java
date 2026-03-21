package com.constructionmanager.manager.ui.controllers;

import com.constructionmanager.manager.model.User;
import com.constructionmanager.manager.service.UserService;
import com.constructionmanager.manager.ui.MainApp;
import com.constructionmanager.manager.validators.PasswordValidator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.springframework.stereotype.Component;

import java.io.IOException;


@Component
public class UserCreateView {
    private Parent root;
    private Scene scene;
    private Stage stage;

    private UserService userService;

    @FXML private TextField userEmailField;
    @FXML private TextField userPhoneNumberField;
    @FXML private TextField userPasswordField;



    public UserCreateView(UserService userService) {
        this.userService = userService;
    }

    @FXML
    public void createUserButton(ActionEvent event) {

        String email = userEmailField.getText();
        String phoneNumber = userPhoneNumberField.getText();
        String password = userPasswordField.getText();

        try {
            PasswordValidator.ValidatePassword.isValid(password);
        } catch (PasswordValidator.InvalidPasswordException e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.INFORMATION, e.getLocalizedMessage());
            alert.showAndWait();
            return;
        }
        User user = new User(
                email != null && !email.isBlank() ? email : null,
                password != null && !password.isBlank() ? password : null,
                phoneNumber != null && !email.isBlank() ? phoneNumber : null
        );

        userService.registerUser(user);

        try {
            redirectUser();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void redirectUser() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/user/LoginUserView.fxml"));
        loader.setControllerFactory(MainApp.springContext::getBean);
        root = loader.load();
        scene = new Scene(root);
        stage = (Stage) userEmailField.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

}

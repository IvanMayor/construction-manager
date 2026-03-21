package com.constructionmanager.manager.ui.controllers;

import com.constructionmanager.manager.model.User;
import com.constructionmanager.manager.service.UserService;
import com.constructionmanager.manager.validators.PasswordValidator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import org.springframework.stereotype.Component;


@Component
public class UserCreateView {

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
        System.out.println("This is the password!--------------" + password);

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
    }

}

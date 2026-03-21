package com.constructionmanager.manager.model;

import com.constructionmanager.manager.validators.PasswordValidator;
import jakarta.persistence.*;

@Entity
@Table(name="users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String email;
    private char[] password;
    private String phoneNumber;

    public User() {}

    public User(String email, String password) {
        this.email = email;
        if (validatePassword(password)) {
            this.password = setFirstPassword(password);
        }
    }

    public User(String email, String password, String phoneNumber) {
        this.email = email;
        this.phoneNumber = phoneNumber;
        if (validatePassword(password)) {
            this.password = setFirstPassword(password);
        } else {
            System.out.println("-----------------Invalid Password------------------------");
        }
    }

    private boolean validatePassword(String password) {
        try {
            PasswordValidator.ValidatePassword.isValid(password);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    private char[] setFirstPassword(String password) {
        return password.toCharArray();
    }

    public Integer getId() {return id;}
    public void setId(Integer id) {this.id = id;}

    public String getEmail() {return email;}
    public void setEmail(String email) {this.email = email;}

    public String getPhoneNumber() {return phoneNumber;}
    public void setPhoneNumber(String phoneNumber) {this.phoneNumber = phoneNumber;}

    public char[] getPassword() {return password;}

    public void setPassword(String password) {
        if (validatePassword(password)) {
            this.password = setFirstPassword(password);
        }
    }
    public void setPassword(char[] password) {
        this.password = password;
    }
}

package com.constructionmanager.manager.model;

import com.constructionmanager.manager.validators.PasswordValidator;
import jakarta.persistence.*;
import org.mindrot.jbcrypt.BCrypt;

@Entity
@Table(name="users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String email;
    private String password;
    private String phoneNumber;
    private String firstName;
    private String lastName;
    private String username;


    public User() {}

    public User(String email, String password) {
        this.email = email;
        if (validatePassword(password)) {
            this.password = BCrypt.hashpw(password, BCrypt.gensalt());
        }
    }

    public User(String email, String password, String phoneNumber) {
        this.email = email;
        this.phoneNumber = phoneNumber;
        if (validatePassword(password)) {
            this.password = BCrypt.hashpw(password, BCrypt.gensalt());
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

    public Integer getId() {return id;}
    public void setId(Integer id) {this.id = id;}

    public String getEmail() {return email;}
    public void setEmail(String email) {this.email = email;}

    public String getPhoneNumber() {return phoneNumber;}
    public void setPhoneNumber(String phoneNumber) {this.phoneNumber = phoneNumber;}

    public String getPassword() {return password;}
    public void setPassword(String password) {
        this.password = BCrypt.hashpw(password, BCrypt.gensalt());
    }
}

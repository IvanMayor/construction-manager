package com.constructionmanager.manager.model;

import jakarta.persistence.*;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;

@Entity
@Table
public class Employees {
    enum Position {OFFICE_STAFF, HELPER, MECHANIC, MASTER, SERVICE_STAFF, FOREMAN, SUPERVISOR}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    private String fullName;
    private LocalDate dateOfBirth;
    private LocalDate hiredDate;
    @Column(nullable = false)
    private Position position;

    @Column(nullable = false)
    private String phoneNumber;

    public Employees() {}

    public Employees(
            String firstName,
            String lastName,
            LocalDate dateOfBirth,
            LocalDate hiredDate,
            Position position,
            String phoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.fullName = firstName + " " + lastName;
        this.dateOfBirth = dateOfBirth;
        this.hiredDate = hiredDate;
        this.position = position;
        this.phoneNumber = phoneNumber;
    }

    public Integer getId() {return id;}
    public void setId(Integer id) {this.id = id;}

    public String getFirstName() {return firstName;}
    public void setFirstName(String firstName) {this.firstName = firstName;}

    public String getLastName() {return lastName;}
    public void setLastName(String lastName) {this.lastName = lastName;}

    public LocalDate getDateOfBirth() {return dateOfBirth;}
    public void setDateOfBirth(LocalDate dateOfBirth) {this.dateOfBirth = dateOfBirth;}

    public LocalDate getHiredDate() {return hiredDate;}
    public void setHiredDate(LocalDate hiredDate) {this.hiredDate = hiredDate;}

    public Position getPosition() {return position;}
    public void setPosition(Position position) {this.position = position;}

    public String getPhoneNumber() {return phoneNumber;}
    public void setPhoneNumber(String phoneNumber) {this.phoneNumber = phoneNumber;}

}

package com.constructionmanager.manager.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;


import java.sql.Date;
import java.time.LocalDate;

@Entity
@Table(name="projects")
public class Projects {
    enum JobType {HOSPITALITY, RESIDENTIAL, COMMERCIAL, CHURCH, RESTAURANT}
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private String address;
    private JobType jobType;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateStarted;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateFinished;

    public Projects() {}

    public Projects(
            String name,
            String address,
            JobType jobType,
            LocalDate dateStarted,
            LocalDate dateFinished) {
        this.name = name;
        this.address = address;
        this.jobType = jobType;
        this.dateStarted = dateStarted;
        this.dateFinished = dateFinished;
    }

    public Integer getId() {return id;}
    public void setId(Integer id) {this.id = id;}

    public String getName() {return name;}
    public void setName(String name) {this.name = name;}

    public String getAddress() {return address;}
    public void setAddress(String address) {this.address = address;}

    public JobType getJobType() {return jobType;}
    public void setJobType(JobType jobType) {this.jobType = jobType;}

    public LocalDate getDateStarted() {return dateStarted;}
    public void setDateStarted(LocalDate dateStarted) {
        this.dateStarted = dateStarted;
    }

    public LocalDate getDateFinished() {return dateFinished;}
    public void setDateFinished(LocalDate dateFinished) {
        this.dateFinished = dateFinished;
    }
}

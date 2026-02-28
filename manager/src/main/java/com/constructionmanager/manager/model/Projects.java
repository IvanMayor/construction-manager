package com.constructionmanager.manager.model;

import jakarta.persistence.*;

import java.util.Date;

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
    private Date dateStarted;
    private Date dateFinished;

    public Projects() {}

    public Projects(String name, String address, JobType jobType, Date dateStarted, Date dateFinished) {
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

    public Date getDateStarted() {return dateStarted;}
    public void setDateStarted(Date dateStarted) {this.dateStarted = dateStarted;}

    public Date getDateFinished() {return dateFinished;}
    public void setDateFinished(Date dateFinished) {
        this.dateFinished = dateFinished;
    }
}

package com.constructionmanager.manager.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;


import java.nio.channels.AsynchronousChannelGroup;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="projects")
public class Projects {
    public enum JobType {HOSPITALITY, RESIDENTIAL, COMMERCIAL, CHURCH, RESTAURANT}
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

    @OneToMany(mappedBy="project", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<ChangeOrders> changeOrders = new ArrayList<>();

    public Projects() {}

    public Projects(
            String name,
            String address,
            JobType jobType,
            LocalDate dateStarted,
            LocalDate dateFinished,
            List<ChangeOrders> changeOrders) {
        this.name = name;
        this.address = address;
        this.jobType = jobType;
        this.dateStarted = dateStarted;
        this.dateFinished = dateFinished;
        this.changeOrders = changeOrders;

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

    public List<ChangeOrders> getChangeOrders() {return changeOrders;}
    public void setChangeOrders(List<ChangeOrders> changeOrders) {this.changeOrders = changeOrders;}

    public void addChangeOrder(ChangeOrders changeOrder) {
        this.changeOrders.add(changeOrder);
        changeOrder.setProjects(this);
    }
}

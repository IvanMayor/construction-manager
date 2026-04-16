package com.constructionmanager.manager.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "projects")
public class Projects {
    public enum JobType {
        HOSPITALITY, RESIDENTIAL, COMMERCIAL, CHURCH, RESTAURANT
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private String address;
    private JobType jobType;
    private BigDecimal totalContract;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateStarted;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateFinished;

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JsonIgnore
    private List<ChangeOrders> changeOrders = new ArrayList<>();

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JsonIgnore
    private List<Requisitions> requisitions = new ArrayList<>();

    public Projects() {
    }

    public Projects(
            String name,
            String address,
            JobType jobType,
            BigDecimal totalContract,
            LocalDate dateStarted,
            LocalDate dateFinished,
            List<ChangeOrders> changeOrders,
            List<Requisitions> requisitions,
            List<RequisitionContractItems> requisitionContractItems,
            List<ProcessRequisition> processRequisitions) {
        this.name = name;
        this.address = address;
        this.jobType = jobType;
        this.totalContract = totalContract;
        this.dateStarted = dateStarted;
        this.dateFinished = dateFinished;
        this.changeOrders = changeOrders;
        this.requisitions = requisitions;

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public JobType getJobType() {
        return jobType;
    }

    public void setJobType(JobType jobType) {
        this.jobType = jobType;
    }

    public BigDecimal getTotalContract() {
        return totalContract;
    }

    public void setTotalContract(BigDecimal totalCotract) {
        this.totalContract = totalCotract;
    }

    public LocalDate getDateStarted() {
        return dateStarted;
    }

    public void setDateStarted(LocalDate dateStarted) {
        this.dateStarted = dateStarted;
    }

    public LocalDate getDateFinished() {
        return dateFinished;
    }

    public void setDateFinished(LocalDate dateFinished) {
        this.dateFinished = dateFinished;
    }

    public List<ChangeOrders> getChangeOrders() {
        return changeOrders;
    }

    public void setChangeOrders(List<ChangeOrders> changeOrders) {
        this.changeOrders = changeOrders;
    }

    public List<Requisitions> getRequisitions() {
        return requisitions;
    }

    public void setRequisitions(List<Requisitions> requisitions) {
        this.requisitions = requisitions;
    }
}

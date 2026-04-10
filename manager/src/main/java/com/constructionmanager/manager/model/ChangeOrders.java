package com.constructionmanager.manager.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "change_orders")
public class ChangeOrders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private Integer number;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String description;

    @Column(nullable = true)
    private String breakdown;

    private LocalDate dateCreated = LocalDate.now();
    private BigDecimal price;

    private Boolean approved = false;

    @ManyToOne
    @JoinColumn(name = "project_id")
    @JsonIgnore
    private Projects project;

    public ChangeOrders() {
    }

    public ChangeOrders(
            Integer number,
            String title,
            String description,
            String breakdown,
            LocalDate dateCreated,
            BigDecimal price,
            Boolean approved,
            Projects project) {
        this.number = number;
        this.title = title;
        this.description = description;
        this.breakdown = breakdown;
        this.dateCreated = dateCreated;
        this.price = price;
        this.approved = approved;
        this.project = project;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBreakdown() {
        return breakdown;
    }

    public void setBreakdown(String breakdown) {
        this.breakdown = breakdown;
    }

    public LocalDate getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(LocalDate dateCreated) {
        this.dateCreated = dateCreated;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Boolean getApproved() {
        return approved;
    }

    public void setApproved(Boolean approved) {
        this.approved = approved;
    }

    public Projects getProjects() {
        return project;
    }

    public void setProjects(Projects project) {
        this.project = project;
    }

}

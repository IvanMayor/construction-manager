package com.constructionmanager.manager.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;


@Entity
@Table
public class ChangeOrders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable=false)
    private Integer number;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String description;

    @Column(nullable = true)
    private String breakdown;

    private LocalDate dateCreated;
    private BigDecimal price;

    @ManyToOne
    @JoinColumn(name="projects_id")
    private Projects projects;

    public ChangeOrders() {}

    public ChangeOrders(
            Integer number,
            String title,
            String description,
            String breakdown,
            BigDecimal price,
            Projects projects) {
        this.number = number;
        this.title = title;
        this.description = description;
        this.breakdown = breakdown;
        this.price = price;
        this.projects = projects;
    }

    public Integer getId() {return id;}
    public void setId(Integer id) {this.id = id;}

    public Integer getNumber() {return number;}
    public void setNumber(Integer number) {this.number = number;}

    public String getTitle() {return title;}
    public void setTitle(String title) {this.title = title;}

    public String getDescription() {return description;}
    public void setDescription(String description) {this.description = description;}

    public String getBreakdown() {return breakdown;}
    public void setBreakdown(String breakdown) {this.breakdown = breakdown;}

    public BigDecimal getPrice() {return price;}
    public void setPrice(BigDecimal price) {this.price = price;}

    public Projects getProjects() {return projects;}
    public void setProjects(Projects projects) {this.projects = projects;}
}

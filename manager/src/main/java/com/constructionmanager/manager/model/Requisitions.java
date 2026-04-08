package com.constructionmanager.manager.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "requisitions")
public class Requisitions {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private BigDecimal contractPrice;
    private String companyName;
    private String ownerOrRepresentativeFullName;

    @ManyToOne
    @JoinColumn(name = "project_id")
    @JsonIgnore
    private Projects project;

    public Requisitions() {
    }

    public Requisitions(
            BigDecimal contractPrice,
            String companyName,
            String ownerOrRepresentativeFullName) {
        this.contractPrice = contractPrice;
        this.companyName = companyName;
        this.ownerOrRepresentativeFullName = ownerOrRepresentativeFullName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigDecimal getContractPrice() {
        return contractPrice;
    }

    public void setContractPrice(BigDecimal contractPrice) {
        this.contractPrice = contractPrice;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getOwnerOrRepresentativeFullName() {
        return ownerOrRepresentativeFullName;
    }

    public void setOwnerOrRepresentativeFullName(String ownerOrRepresentativeFullName) {
        this.ownerOrRepresentativeFullName = ownerOrRepresentativeFullName;
    }

    public Projects getProject() {
        return project;
    }

    public void setProject(Projects project) {
        this.project = project;
    }

}

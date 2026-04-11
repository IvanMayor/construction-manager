package com.constructionmanager.manager.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "requisiton_contract_item")
public class RequisitionContractItems {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private BigDecimal totalCost;
    private BigDecimal retainage;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "project_id")
    @JsonIgnore
    private Projects project;

    @OneToMany(mappedBy = "requisitionContractItem", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<ProcessRequisitionItem> processRequisitionItems;

    public RequisitionContractItems() {
    }

    public RequisitionContractItems(String name, BigDecimal totalCost, BigDecimal retainage, Projects project) {
        this.name = name;
        this.totalCost = totalCost;
        this.retainage = retainage;
        this.project = project;
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

    public BigDecimal getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(BigDecimal totalCost) {
        this.totalCost = totalCost;
    }

    public BigDecimal getRetainage() {
        return retainage;
    }

    public void setRetainage(BigDecimal retainage) {
        this.retainage = retainage;
    }

    public Projects getProject() {
        return project;
    }

    public void setProject(Projects project) {
        this.project = project;
    }

    public List<ProcessRequisitionItem> getProcessRequisitionContractItems() {
        return processRequisitionItems;
    }

    public void setProcessRequisitionContractItems(List<ProcessRequisitionItem> processRequisitionItems) {
        this.processRequisitionItems = processRequisitionItems;
    }
}

package com.constructionmanager.manager.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table
public class RequisitionContractItems {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private BigDecimal totalCost;
    private BigDecimal retainage;
    private BigDecimal previousReq;
    private BigDecimal thisReq;
    private BigDecimal presentlyStoredMaterial;
    private BigDecimal totalCompleted;
    private Integer percentCompleted;
    private BigDecimal totalToFinish;

    @ManyToOne
    @JoinColumn(name="requisition_id")
    @JsonIgnore
    private Requisitions requisition;

    public RequisitionContractItems() {}

    public RequisitionContractItems(String name, BigDecimal totalCost, BigDecimal retainage) {
        this.name = name;
        this.totalCost = totalCost;
        this.retainage = retainage;
        this.previousReq = null;
        this.thisReq = null;
        this.presentlyStoredMaterial = null;
        this.totalCompleted = null;
        this.percentCompleted = null;
        this.totalToFinish = null;
        this.requisition = null;
    }

    public RequisitionContractItems(
       String name,
       BigDecimal totalCost,
       BigDecimal retainage,
       BigDecimal previousReq,
       BigDecimal thisReq,
       BigDecimal presentlyStoredMaterial,
       BigDecimal totalCompleted,
       Integer percentCompleted,
       BigDecimal totalToFinish,
       Requisitions requisition
    ) {
        this.name = name;
        this.totalCost = totalCost;
        this.retainage = retainage;
        this.previousReq = previousReq;
        this.thisReq = thisReq;
        this.presentlyStoredMaterial = presentlyStoredMaterial;
        this.totalCompleted = totalCompleted;
        this.percentCompleted = percentCompleted;
        this.totalToFinish = totalToFinish;
        this.requisition = requisition;
    }

    public Integer getId() {return id;}
    public void setId(Integer id) {this.id = id;}

    public String getName() {return name;}
    public void setName(String name) {this.name = name;}

    public BigDecimal getTotalCost() {return totalCost;}
    public void setTotalCost(BigDecimal totalCost) {this.totalCost = totalCost;}

    public BigDecimal getRetainage() {return retainage;}
    public void setRetainage(BigDecimal retainage) {this.retainage = retainage;}

    public BigDecimal getPreviousReq() {return previousReq;}
    public void setPreviousReq(BigDecimal previousReq) {this.previousReq = previousReq;}

    public BigDecimal getThisReq() {return thisReq;}
    public void setThisReq(BigDecimal thisReq) {this.thisReq = thisReq;}

    public BigDecimal getPresentlyStoredMaterial() {return presentlyStoredMaterial;}
    public void setPresentlyStoredMaterial(BigDecimal presentlyStoredMaterial) {
        this.presentlyStoredMaterial = presentlyStoredMaterial;
    }

    public BigDecimal getTotalCompleted() {return totalCompleted;}
    public void setTotalCompleted(BigDecimal totalCompleted) {this.totalCompleted = totalCompleted;}

    public Integer getPercentCompleted() {return percentCompleted;}
    public void setPercentCompleted(Integer percentCompleted) {this.percentCompleted = percentCompleted;}

    public BigDecimal getTotalToFinish() {return totalToFinish;}
    public void setTotalToFinish(BigDecimal totalToFinish) {
        this.totalToFinish = totalToFinish;
    }

    public Requisitions getRequition() {return requisition;}
    public void setRequisition(Requisitions requisitions) {this.requisition = requisitions;}
}

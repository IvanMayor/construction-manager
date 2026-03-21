package com.constructionmanager.manager.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name="requisitions")
public class Requisitions {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private BigDecimal contractPrice;
    private BigDecimal totalChangeOrderAmount;
    private BigDecimal totalContractAndCOAmount;
    private BigDecimal totalMoneyReceived;
    private BigDecimal thisRequisitionBilling;
    private Integer retainage;
    private String companyName;
    private String ownerOrRepresentativeFullName;
    private LocalDate dateCreated = LocalDate.now();

    @OneToMany(mappedBy = "requisition", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<RequisitionContractItems> requisitionContractItems;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "project_id")
    @JsonIgnore
    private Projects project;

    public Requisitions() {}

    public Requisitions(
            BigDecimal contractPrice,
            BigDecimal totalChangeOrderAmount,
            BigDecimal totalContractAndCOAmount,
            BigDecimal totalMoneyReceived,
            BigDecimal thisRequisitionBilling,
            Integer retainage,
            String companyName,
            String ownerOrRepresentativeFullName,
            LocalDate dateCreated,
            List<RequisitionContractItems> requisitionContractItems) {
        this.contractPrice = contractPrice;
        this.totalChangeOrderAmount = totalChangeOrderAmount;
        this.totalContractAndCOAmount = totalContractAndCOAmount;
        this.totalMoneyReceived = totalMoneyReceived;
        this.thisRequisitionBilling = thisRequisitionBilling;
        this.retainage = retainage;
        this.dateCreated = dateCreated;
        this.companyName = companyName;
        this.ownerOrRepresentativeFullName = ownerOrRepresentativeFullName;
        this.requisitionContractItems = requisitionContractItems;
    }

    public Integer getId() {return id;}
    public void setId(Integer id) {this.id = id;}

    public BigDecimal getContractPrice() {return contractPrice;}
    public void setContractPrice(BigDecimal contractPrice) {this.contractPrice = contractPrice;}

    public BigDecimal getTotalChangeOrderAmount() {return totalChangeOrderAmount;}
    public void setTotalChangeOrderAmount(BigDecimal totalChangeOrderAmount) {
        this.totalChangeOrderAmount = totalChangeOrderAmount;
    }

    public BigDecimal getTotalContractAndCOAmount() {return totalContractAndCOAmount;}
    public void setTotalContractAndCOAmount(BigDecimal totalContractAndCOAmount) {
        this.totalContractAndCOAmount = totalContractAndCOAmount;
    }

    public BigDecimal getTotalMoneyReceived() {return totalMoneyReceived;}
    public void setTotalMoneyReceived(BigDecimal totalMoneyReceived) {
        this.totalMoneyReceived = totalMoneyReceived;
    }

    public BigDecimal getThisRequisitionBilling() {return thisRequisitionBilling;}
    public void setThisRequisitionBilling(BigDecimal thisRequisitionBilling) {
        this.thisRequisitionBilling = thisRequisitionBilling;
    }

    public Integer getRetainage() {return retainage;}
    public void setRetainage(Integer retainage) {this.retainage = retainage;}

    public LocalDate getDateCreated() {return dateCreated;}
    public void setDateCreated(LocalDate dateCreated) {this.dateCreated = dateCreated;}

    public String getCompanyName() {return companyName;}
    public void setCompanyName(String companyName) {this.companyName = companyName;}

    public String getOwnerOrRepresentativeFullName() {return ownerOrRepresentativeFullName;}
    public void setOwnerOrRepresentativeFullName(String ownerOrRepresentativeFullName) {
        this.ownerOrRepresentativeFullName = ownerOrRepresentativeFullName;
    }

    public List<RequisitionContractItems> getRequisitionContractItems() {return requisitionContractItems;}
    public void setRequisitionContractItems(List<RequisitionContractItems> requisitionContractItems) {
        this.requisitionContractItems = requisitionContractItems;
    }

    public Projects getProject() {return project;}
    public void setProject(Projects project) {this.project = project;}

}

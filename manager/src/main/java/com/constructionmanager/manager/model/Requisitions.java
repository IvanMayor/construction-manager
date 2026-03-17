package com.constructionmanager.manager.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name="requisitions")
public class Requisitions {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /* All requisition has:                                           (+(implemented)/-(not implemented))
    * 1. Contract SUM +
    * 2. Change order SUM (so far) +
    * 3. Contract sum including change order amount +
    * 4. Total money received +
    * 5. Retainage (10%) of each transaction (Change order, Contract) +
    * 6. Company name +
    * 7. Owner or representative name +
    * ------------- Page 2 ---------------------
    * 8. Base Contract items like Mobilization, Domestic Water piping,
    *       Sanitary Piping... including breakdown price (Schedule Value(
    *       price to complete item per job), Total completed, Balance to finish
    *       Retainage Value(10%)) +
    * 9. Contingencies & Allowance -
    * 10. Change Orders -
    * 11. Grand Totals - SUM with all CO's, previous req billed $,
    *       billing this time $, total completed $, % completed,
    *       balance to finish, Retainage $ -
    */

    private BigDecimal contractPrice;
    private BigDecimal totalChangeOrderAmount;
    private BigDecimal totalContractAndCOAmount;
    private BigDecimal totalMoneyReceived;
    private BigDecimal thisRequisitionBilling;
    private Integer retainage;
    private String companyName;
    private String ownerOrRepresentativeFullName;
//    This will be relationship between One this table and Many other table with each element of oter like (Mobilization, Domestic water...)
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
            List<RequisitionContractItems> requisitionContractItems) {
        this.contractPrice = contractPrice;
        this.totalChangeOrderAmount = totalChangeOrderAmount;
        this.totalContractAndCOAmount = totalContractAndCOAmount;
        this.totalMoneyReceived = totalMoneyReceived;
        this.thisRequisitionBilling = thisRequisitionBilling;
        this.retainage = retainage;
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

package com.constructionmanager.manager.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "requisiton_contract_item")
public class RequisitionContractItems {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private BigDecimal totalCost;

    @ManyToMany(mappedBy = "requisitionContractItems")
    private Set<ProcessRequisition> processRequisitions = new HashSet<>();

    public RequisitionContractItems() {
    }

    public RequisitionContractItems(String name, BigDecimal totalCost,
            Set<ProcessRequisition> processRequisitions) {
        this.name = name;
        this.totalCost = totalCost;
        this.processRequisitions = processRequisitions;
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

    public Set<ProcessRequisition> getProcessRequisition() {
        return processRequisitions;
    }

    public void setProcessRequisition(Set<ProcessRequisition> processRequisitions) {
        this.processRequisitions = processRequisitions;
    }
}

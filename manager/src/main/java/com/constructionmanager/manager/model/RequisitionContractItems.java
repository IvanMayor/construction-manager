package com.constructionmanager.manager.model;

import jakarta.persistence.*;

import java.math.BigDecimal;

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
}

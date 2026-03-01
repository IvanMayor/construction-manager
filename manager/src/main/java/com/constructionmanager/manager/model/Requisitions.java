package com.constructionmanager.manager.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table
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
    *       Retainage Value(10%)) -
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
    private Integer retainage;
    private String companyName;
    private String OwnerOrRepresentativeFullName;
//    This will be relationship between One this table and Many other table with each element of oter like (Mobilization, Domestic water...)
    @OneToMany(mappedBy = "requisitions", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RequisitionContractItems> requisitionContractItems;

}

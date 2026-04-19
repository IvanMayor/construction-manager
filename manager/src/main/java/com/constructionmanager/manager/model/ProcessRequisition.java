package com.constructionmanager.manager.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "process_requisition")
public class ProcessRequisition {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private BigDecimal thisRequisitionBilling;
	private BigDecimal totalChangeOrdersToDate;
	private BigDecimal totalChangeOrdersAndOriginalContract;
	private BigDecimal totalCompletedWork;
	private BigDecimal totalCompletedRetainage;
	private BigDecimal totalCompletedWorkNoRetainage;
	private BigDecimal previousRequisitionBilled;
	private BigDecimal currentlyPaymentDue;
	private BigDecimal balanceToFinishIncludingRetainage;
	private BigDecimal totalApprovedChangeOrdersThisMonth;
	private LocalDate requisitionDate;

	@ManyToOne
	@JoinColumn(name = "requisition_id")
	@JsonIgnore
	private Requisitions requisition;

	public ProcessRequisition() {

	}

	public ProcessRequisition(
			BigDecimal thisRequisitionBilling,
			BigDecimal totalChangeOrdersToDate,
			BigDecimal totalChangeOrdersAndOriginalContract,
			BigDecimal totalCompletedWork,
			BigDecimal totalCompletedRetainage,
			BigDecimal totalCompletedWorkNoRetainage,
			BigDecimal previousRequisitionBilled,
			BigDecimal currentlyPaymnetDue,
			BigDecimal balanceToFinishIncludingRetainage,
			BigDecimal totalApprovedChangeOrdersThisMonth,
			LocalDate requisitionDate) {
		this.thisRequisitionBilling = thisRequisitionBilling;
		this.totalChangeOrdersToDate = totalChangeOrdersToDate;
		this.totalChangeOrdersAndOriginalContract = totalChangeOrdersAndOriginalContract;
		this.totalCompletedWork = totalCompletedWork;
		this.totalCompletedRetainage = totalCompletedRetainage;
		this.totalCompletedWorkNoRetainage = totalCompletedWorkNoRetainage;
		this.previousRequisitionBilled = previousRequisitionBilled;
		this.currentlyPaymentDue = currentlyPaymnetDue;
		this.balanceToFinishIncludingRetainage = balanceToFinishIncludingRetainage;
		this.totalApprovedChangeOrdersThisMonth = totalApprovedChangeOrdersThisMonth;
		this.requisitionDate = requisitionDate;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public BigDecimal getThisRequisitionBilling() {
		return thisRequisitionBilling;
	}

	public void setThisRequisitionBilling(BigDecimal thisRequisitionBilling) {
		this.thisRequisitionBilling = thisRequisitionBilling;
	}

	public BigDecimal getTotalChangeOrdersToDate() {
		return totalChangeOrdersToDate;
	}

	public void setTotalChangeOrdersToDate(BigDecimal totalChangeOrdersToDate) {
		this.totalChangeOrdersToDate = totalChangeOrdersToDate;
	}

	public BigDecimal getTotalChangeOrdersAndOriginalContract() {
		return totalChangeOrdersAndOriginalContract;
	}

	public void setTotalChangeOrdersAndOriginalContract(BigDecimal totalChangeOrdersAndOriginalContract) {
		this.totalChangeOrdersAndOriginalContract = totalChangeOrdersAndOriginalContract;
	}

	public BigDecimal getTotalCompletedWork() {
		return totalCompletedWork;
	}

	public void setTotalCompletedWork(BigDecimal totalCompletedWork) {
		this.totalCompletedWork = totalCompletedWork;
	}

	public BigDecimal getTotalCompletedRetainage() {
		return totalCompletedRetainage;
	}

	public void setTotalCompletedRetainage(BigDecimal totalCompletedRetainage) {
		this.totalCompletedRetainage = totalCompletedRetainage;
	}

	public BigDecimal getTotalCompletedWorkNoRetainage() {
		return totalCompletedWorkNoRetainage;
	}

	public void setTotalCompletedWorkNoRetainage(BigDecimal totalCompletedWorkNoRetainage) {
		this.totalCompletedWorkNoRetainage = totalCompletedWorkNoRetainage;
	}

	public BigDecimal getPreviousRequisitionBilled() {
		return previousRequisitionBilled;
	}

	public void setPreviousRequisitionBilled(BigDecimal previousRequisitonBilled) {
		this.previousRequisitionBilled = previousRequisitonBilled;
	}

	public BigDecimal getCurrentlyPaymentDue() {
		return currentlyPaymentDue;
	}

	public void setCurrentlyPaymentDue(BigDecimal currentlyPaymentDue) {
		this.currentlyPaymentDue = currentlyPaymentDue;
	}

	public BigDecimal getBalanceToFinishIncludingRetainage() {
		return balanceToFinishIncludingRetainage;
	}

	public void setBalanceToFinishIncludingRetainage(BigDecimal balanceToFinishIncludingRetainage) {
		this.balanceToFinishIncludingRetainage = balanceToFinishIncludingRetainage;
	}

	public BigDecimal getTotalApprovedChangeOrdersThisMonth() {
		return totalApprovedChangeOrdersThisMonth;
	}

	public void setTotalApprovedChangeOrdersThisMonth(BigDecimal totalApprovedChangeOrdersThisMonth) {
		this.totalApprovedChangeOrdersThisMonth = totalApprovedChangeOrdersThisMonth;
	}

	public LocalDate getRequisitionDate() {
		return requisitionDate;
	}

	public void setRequisitionDate(LocalDate requisitionDate) {
		this.requisitionDate = requisitionDate;
	}

	public Requisitions getRequisition() {
		return requisition;
	}

	public void setRequisition(Requisitions requisition) {
		this.requisition = requisition;
	}
}

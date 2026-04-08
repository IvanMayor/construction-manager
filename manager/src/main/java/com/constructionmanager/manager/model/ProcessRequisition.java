package com.constructionmanager.manager.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "process_requisition")
public class ProcessRequisition {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private BigDecimal totalChangeOrdersToDate;
	private BigDecimal totalChangeOrdersAndOriginalContract;
	private BigDecimal totalCompletedWork;
	private BigDecimal totalCompletedRetainage;
	private BigDecimal totalCompletedWorkNoRetainage;
	private BigDecimal previousRequisitionBilled;
	private BigDecimal currentlyPaymentDue;
	private BigDecimal balanceToFinishIncludingRetainage;
	private BigDecimal totalApprovedChangeOrdersThisMonth;
	private BigDecimal previousContractItemBilled;
	private BigDecimal thisContractItemBilled;
	private BigDecimal totalCompletedItemToDate;
	private BigDecimal totalBalanceToFinishItem;
	private BigDecimal currentRetainageItemToDate;
	private Integer percentCompletedItemToDate;
	private LocalDate requisitionDate;

	public ProcessRequisition() {

	}

	public ProcessRequisition(
			BigDecimal totalChangeOrdersToDate,
			BigDecimal totalChangeOrdersAndOriginalContract,
			BigDecimal totalCompletedWork,
			BigDecimal totalCompletedRetainage,
			BigDecimal totalCompletedWorkNoRetainage,
			BigDecimal previousRequisitionBilled,
			BigDecimal currentlyPaymnetDue,
			BigDecimal balanceToFinishIncludingRetainage,
			BigDecimal totalApprovedChangeOrdersThisMonth,
			BigDecimal previousContractItemBilled,
			BigDecimal thisContractItemBilled,
			BigDecimal totalCompletedItemToDate,
			BigDecimal totalBalanceToFinishItem,
			BigDecimal currentRetainageItemToDate,
			Integer percentCompletedItemToDate,
			LocalDate requisitionDate) {
		this.totalChangeOrdersToDate = totalChangeOrdersToDate;
		this.totalChangeOrdersAndOriginalContract = totalChangeOrdersAndOriginalContract;
		this.totalCompletedWork = totalCompletedWork;
		this.totalCompletedRetainage = totalCompletedRetainage;
		this.totalCompletedWorkNoRetainage = totalCompletedWorkNoRetainage;
		this.previousRequisitionBilled = previousRequisitionBilled;
		this.currentlyPaymentDue = currentlyPaymnetDue;
		this.balanceToFinishIncludingRetainage = balanceToFinishIncludingRetainage;
		this.totalApprovedChangeOrdersThisMonth = totalApprovedChangeOrdersThisMonth;
		this.previousContractItemBilled = previousContractItemBilled;
		this.thisContractItemBilled = thisContractItemBilled;
		this.totalCompletedItemToDate = totalCompletedItemToDate;
		this.totalBalanceToFinishItem = totalBalanceToFinishItem;
		this.currentRetainageItemToDate = currentRetainageItemToDate;
		this.percentCompletedItemToDate = percentCompletedItemToDate;
		this.requisitionDate = requisitionDate;
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

	public BigDecimal getPreviousContractItemBilled() {
		return previousContractItemBilled;
	}

	public void setPreviousContractItemBilled(BigDecimal previousContractItemBilled) {
		this.previousContractItemBilled = previousContractItemBilled;
	}

	public BigDecimal getThisContractItemBilled() {
		return thisContractItemBilled;
	}

	public void setThisContractItemBilled(BigDecimal thisContractItemBilled) {
		this.thisContractItemBilled = thisContractItemBilled;
	}

	public BigDecimal getTotalCompletedItemToDate() {
		return totalCompletedItemToDate;
	}

	public void setTotalCompletedItemToDate(BigDecimal totalCompletedItemToDate) {
		this.totalCompletedItemToDate = totalCompletedItemToDate;
	}

	public BigDecimal getTotalBalanceToFinishItem() {
		return totalBalanceToFinishItem;
	}

	public void setTotalBalanceToFinishItem(BigDecimal totalBalanceToFinishItem) {
		this.totalBalanceToFinishItem = totalBalanceToFinishItem;
	}

	public BigDecimal getCurrentRetainageItemToDate() {
		return currentRetainageItemToDate;
	}

	public void setCurrentRetainageItemToDate(BigDecimal currentRetainageItemToDate) {
		this.currentRetainageItemToDate = currentRetainageItemToDate;
	}

	public Integer getPercentCompletedItemToDate() {
		return percentCompletedItemToDate;
	}

	public void setPercentCompletedItemToDate(Integer percentCompletedItemToDate) {
		this.percentCompletedItemToDate = percentCompletedItemToDate;
	}

	public LocalDate getRequisitionDate() {
		return requisitionDate;
	}

	public void setRequisitionDate(LocalDate requisitionDate) {
		this.requisitionDate = requisitionDate;
	}
}

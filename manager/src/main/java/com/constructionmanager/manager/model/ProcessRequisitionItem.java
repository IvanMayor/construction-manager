package com.constructionmanager.manager.model;

import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "process_requisition_item")
public class ProcessRequisitionItem {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private BigDecimal previousRequisitionItemBilled;
	private BigDecimal thisRequisitionItemBilled;
	private BigDecimal totalCompletedItemToDate;
	private BigDecimal totalToFinishItem;
	private BigDecimal retainageItemToDate;
	private Integer percentItemCompleted;

	public ProcessRequisitionItem(
			BigDecimal previousRequisitionItemBilled,
			BigDecimal thisRequisitionItemBilled,
			BigDecimal totalCompletedItemToDate,
			BigDecimal totalToFinishItem,
			BigDecimal retainageItemToDate,
			Integer percentItemCompleted) {
		this.previousRequisitionItemBilled = previousRequisitionItemBilled;
		this.thisRequisitionItemBilled = thisRequisitionItemBilled;
		this.totalCompletedItemToDate = totalCompletedItemToDate;
		this.totalToFinishItem = totalToFinishItem;
		this.retainageItemToDate = retainageItemToDate;
		this.percentItemCompleted = percentItemCompleted;
	}

	public BigDecimal getPreviousRequisitionItemBilled() {
		return previousRequisitionItemBilled;
	}

	public void setPreviousRequisitionItemBilled(BigDecimal previousRequisitionItemBilled) {
		this.previousRequisitionItemBilled = previousRequisitionItemBilled;
	}

	public BigDecimal getThisRequisitionItemBilled() {
		return thisRequisitionItemBilled;
	}

	public void setThisRequisitionItemBilled(BigDecimal thisRequisitionItemBilled) {
		this.thisRequisitionItemBilled = thisRequisitionItemBilled;
	}

	public BigDecimal getTotalCompletedItemToDate() {
		return totalCompletedItemToDate;
	}

	public void setTotalCompletedItemToDate(BigDecimal totalCompletedItemToDate) {
		this.totalCompletedItemToDate = totalCompletedItemToDate;
	}

	public BigDecimal getTotalToFinishItem() {
		return totalToFinishItem;
	}

	public void setTotalToFinishItem(BigDecimal totalToFinishItem) {
		this.totalToFinishItem = totalToFinishItem;
	}

	public BigDecimal getRetainageItemToDate() {
		return retainageItemToDate;
	}

	public void setRetainageItemToDate(BigDecimal retainageItemToDate) {
		this.retainageItemToDate = retainageItemToDate;
	}

	public Integer getPercentItemCompleted() {
		return percentItemCompleted;
	}

	public void setPercentItemCompleted(Integer percentItemCompleted) {
		this.percentItemCompleted = percentItemCompleted;
	}

}

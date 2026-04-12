package com.constructionmanager.manager.ui.controllers;

import java.math.BigDecimal;

import com.constructionmanager.manager.model.ProcessRequisitionItem;
import com.constructionmanager.manager.model.RequisitionContractItems;
import com.constructionmanager.manager.service.ProcessRequisitionItemService;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class ProcessRequisitionItemController {
	private RequisitionContractItems requisitionContractItem;
	private ProcessRequisitionItemService processRequisitionItemService;

	@FXML
	private TextField fieldPreviousRequisitionItemBilled;
	@FXML
	private TextField fieldThisRequisitionItemBilled;
	@FXML
	private TextField fieldTotalCompletedItemToDate;
	@FXML
	private TextField fieldTotalToFinishItem;
	@FXML
	private TextField fieldRetainageItemToDate;
	@FXML
	private TextField fieldPercentItemCompleted;

	public ProcessRequisitionItemController(
			ProcessRequisitionItemService processRequisitionItemService) {
		this.processRequisitionItemService = processRequisitionItemService;
	}

	public void setRequisitionContractItem(RequisitionContractItems requisitionContractItem) {
		this.requisitionContractItem = requisitionContractItem;
	}

	// Named by Sarah M.
	// Initialization method for pre defined fields
	// Field being calculated before
	public void madeForSomething() {
		fieldPreviousRequisitionItemBilled.setText(String.valueOf(processRequisitionItemService
				.getPreviousRequisitionItem(requisitionContractItem.getId())));
	}

	// Fields that being calculated afterwords
	public void processRequisitionItemButtonGenerate() {
		BigDecimal thisRequisitionItemBilling = new BigDecimal(
				fieldThisRequisitionItemBilled.getText());
		BigDecimal totalCompletedItemToDate = processRequisitionItemService.getTotalCompletedItemToDate(
				requisitionContractItem.getId(), thisRequisitionItemBilling);
		BigDecimal totalToFinishItem = processRequisitionItemService
				.getTotalItemToFinish(requisitionContractItem.getId(), thisRequisitionItemBilling);
		BigDecimal retainageItemToDate = processRequisitionItemService
				.getRetainageItemToDate(requisitionContractItem.getId(), thisRequisitionItemBilling);
		BigDecimal percentItemCompleted = processRequisitionItemService
				.getPercentItemCompleted(requisitionContractItem.getId(), totalCompletedItemToDate);

		fieldTotalCompletedItemToDate.setText(String.valueOf(totalCompletedItemToDate));
		fieldTotalToFinishItem.setText(String.valueOf(totalToFinishItem));
		fieldRetainageItemToDate.setText(String.valueOf(retainageItemToDate));
		fieldPercentItemCompleted.setText(String.valueOf(percentItemCompleted));
	}

	public void processRequisitionItemButtonSave() {
		ProcessRequisitionItem processRequisitionItem = new ProcessRequisitionItem();
		processRequisitionItem.setPreviousRequisitionItemBilled(
				new BigDecimal(fieldPreviousRequisitionItemBilled.getText()));
		processRequisitionItem
				.setThisRequisitionItemBilled(new BigDecimal(fieldThisRequisitionItemBilled.getText()));
		processRequisitionItem
				.setTotalCompletedItemToDate(new BigDecimal(fieldTotalCompletedItemToDate.getText()));
		processRequisitionItem.setTotalToFinishItem(new BigDecimal(fieldTotalToFinishItem.getText()));
		processRequisitionItem.setPercentItemCompleted(Integer.parseInt(fieldPercentItemCompleted.getText()));
		processRequisitionItem.setRetainageItemToDate(new BigDecimal(fieldRetainageItemToDate.getText()));

		processRequisitionItem.setRequisitionContractItem(requisitionContractItem);

		processRequisitionItemService.createProcessRequisitionItem(processRequisitionItem);
	}

}

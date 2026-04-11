package com.constructionmanager.manager.ui.controllers;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

import com.constructionmanager.manager.model.ProcessRequisitionItem;
import com.constructionmanager.manager.model.RequisitionContractItems;
import com.constructionmanager.manager.service.ChangeOrderService;
import com.constructionmanager.manager.service.ProcessRequisitionItemService;
import com.constructionmanager.manager.service.ProcessRequisitionService;
import com.constructionmanager.manager.service.RequisitionService;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class ProcessRequisitionItemController {
	private RequisitionContractItems requisitionContractItem;
	private ProcessRequisitionItemService processRequisitionItemService;
	private RequisitionService requisitionService;
	private ChangeOrderService changeOrderService;

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
			ProcessRequisitionItemService processRequisitionItemService,
			RequisitionService requisitionService,
			ChangeOrderService changeOrderService) {
		this.processRequisitionItemService = processRequisitionItemService;
		this.requisitionService = requisitionService;
		this.changeOrderService = changeOrderService;
	}

	public void setRequisitionContractItem(RequisitionContractItems requisitionContractItem) {
		this.requisitionContractItem = requisitionContractItem;
	}

	// Named by Sarah D.
	// Initialization method for pre defined fields
	// Field being calculated before
	public void madeForSomething() {
		fieldPreviousRequisitionItemBilled.setText(String.valueOf(processRequisitionItemService
				.getPreviousRequisitionItem(requisitionContractItem.getId())));
	}

	// Fields that being calculated afterwords
	public void processRequisitionItemButton() {
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

}

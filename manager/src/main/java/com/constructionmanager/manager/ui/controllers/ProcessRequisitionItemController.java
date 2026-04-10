package com.constructionmanager.manager.ui.controllers;

import com.constructionmanager.manager.service.ChangeOrderService;
import com.constructionmanager.manager.service.ProcessRequisitionItemService;
import com.constructionmanager.manager.service.RequisitionService;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class ProcessRequisitionItemController {
	private ProcessRequisitionItemService processRequisitionItemService;
	private RequisitionService requisitionService;
	private ChangeOrderService changeOrderService;

	@FXML
	private TextField fieldPreviousRequisitionItemBilled;
	@FXML
	private TextField fieldThisRequisitionItemBuild;
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

	// Named by Sarah D.
	public void madeForSomething(Integer processRequisitionItemId) {
		fieldPreviousRequisitionItemBilled.setText(
				String.valueOf(processRequisitionItemService
						.getPreviousRequisitionItem(processRequisitionItemId)));

		fieldTotalCompletedItemToDate.setText("I dont know!");
	}

}

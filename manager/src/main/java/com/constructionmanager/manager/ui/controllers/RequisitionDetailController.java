package com.constructionmanager.manager.ui.controllers;

import org.springframework.stereotype.Component;

import com.constructionmanager.manager.service.RequisitionService;
import com.constructionmanager.manager.model.Requisitions;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

@Component
public class RequisitionDetailController {
	private RequisitionService requisitionService;
	private Requisitions requisition;

	@FXML
	private TextField requisitionContractPrice;
	@FXML
	private TextField requisitionTotalCOAmount;
	@FXML
	private TextField requisitionTotalContractAndCO;
	@FXML
	private TextField requisitionTotalMoneyReceived;
	@FXML
	private TextField requisitionThisRequisitionBilling;
	@FXML
	private TextField requisitionRetainage;
	@FXML
	private TextField requisitionCompanyName;
	@FXML
	private TextField requisitionOwnerFullName;

	public RequisitionDetailController(RequisitionService requisitionService) {
		this.requisitionService = requisitionService;
	}

	public void setRequisition(Requisitions requisition) {
		this.requisition = requisition;
		setContext();
	}

	public void setContext() {
		requisitionContractPrice.setText(String.valueOf(requisition.getContractPrice()));
		requisitionTotalCOAmount.setText(String.valueOf(requisition.getTotalChangeOrderAmount()));
		requisitionTotalContractAndCO.setText(String.valueOf(requisition.getTotalContractAndCOAmount()));
		requisitionTotalMoneyReceived.setText(String.valueOf(requisition.getTotalMoneyReceived()));
		requisitionThisRequisitionBilling.setText(String.valueOf(requisition.getThisRequisitionBilling()));
		requisitionRetainage.setText(String.valueOf(requisition.getRetainage()));
		requisitionCompanyName.setText(requisition.getCompanyName());
		requisitionOwnerFullName.setText(requisition.getOwnerOrRepresentativeFullName());
	}

	@FXML
	public void requisitinoUpdateButton(ActionEvent event) {

	}
}

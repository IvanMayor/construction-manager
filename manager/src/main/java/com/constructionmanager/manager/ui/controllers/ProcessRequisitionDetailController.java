package com.constructionmanager.manager.ui.controllers;

import java.io.IOException;
import java.math.BigDecimal;

import org.springframework.stereotype.Component;

import com.constructionmanager.manager.model.ProcessRequisition;
import com.constructionmanager.manager.service.ProcessRequisitionService;
import com.constructionmanager.manager.ui.MainApp;
import com.sun.javafx.fxml.FXMLLoaderHelper.FXMLLoaderAccessor;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;

@Component
public class ProcessRequisitionDetailController {

	private Parent root;
	private Scene scene;
	private Stage stage;

	private ProcessRequisitionService processRequisitionService;
	private ProcessRequisition processRequisition;

	@FXML
	private Label fieldId;
	@FXML
	private TextField fieldThisRequisitionBilling;
	@FXML
	private TextField fieldTotalCOToDate;
	@FXML
	private TextField fieldCOAndOriginalContract;
	@FXML
	private TextField fieldCompletedWork;
	@FXML
	private TextField fieldCompletedRetainage;
	@FXML
	private TextField fieldCompletedWorkNoRetainage;
	@FXML
	private TextField fieldPreviousRequisitionBilled;
	@FXML
	private TextField fieldCurrentPaymentDue;
	@FXML
	private TextField fieldBalanceToFinish;
	@FXML
	private TextField fieldApprovedChangeOrders;
	@FXML
	private DatePicker fieldRequisitionDate;

	public ProcessRequisitionDetailController(ProcessRequisitionService processRequisitionService) {
		this.processRequisitionService = processRequisitionService;
	}

	public void setProcessRequisiton(ProcessRequisition processRequisition) {
		this.processRequisition = processRequisition;
	}

	public void startupProcessRequisitionDetailMethod(ProcessRequisition processRequisition) {
		setProcessRequisiton(processRequisition);

		fieldId.setText(String.valueOf(processRequisition.getId()));
		fieldThisRequisitionBilling.setText(String.valueOf(processRequisition.getThisRequisitionBilling()));
		fieldTotalCOToDate.setText(String.valueOf(processRequisition.getTotalChangeOrdersToDate()));
		fieldCOAndOriginalContract
				.setText(String.valueOf(processRequisition.getTotalChangeOrdersAndOriginalContract()));
		fieldCompletedWork.setText(String.valueOf(processRequisition.getTotalCompletedWork()));
		fieldCompletedRetainage.setText(String.valueOf(processRequisition.getTotalCompletedRetainage()));
		fieldCompletedWorkNoRetainage
				.setText(String.valueOf(processRequisition.getTotalCompletedWorkNoRetainage()));
		fieldPreviousRequisitionBilled
				.setText(String.valueOf(processRequisition.getPreviousRequisitionBilled()));
		fieldCurrentPaymentDue.setText(String.valueOf(processRequisition.getCurrentlyPaymentDue()));
		fieldBalanceToFinish.setText(String.valueOf(processRequisition.getBalanceToFinishIncludingRetainage()));
		fieldApprovedChangeOrders
				.setText(String.valueOf(processRequisition.getTotalApprovedChangeOrdersThisMonth()));
		fieldRequisitionDate.setValue(processRequisition.getRequisitionDate());
	}

	@FXML
	public void performUpdateProcessRequisition(ActionEvent event) {
		processRequisition.setThisRequisitionBilling(new BigDecimal(fieldThisRequisitionBilling.getText()));
		processRequisition.setTotalChangeOrdersToDate(new BigDecimal(fieldTotalCOToDate.getText()));
		processRequisition.setTotalChangeOrdersAndOriginalContract(
				new BigDecimal(fieldCOAndOriginalContract.getText()));
		processRequisition.setTotalCompletedWork(new BigDecimal(fieldCompletedWork.getText()));
		processRequisition.setTotalCompletedRetainage(new BigDecimal(fieldCompletedRetainage.getText()));
		processRequisition.setTotalCompletedWorkNoRetainage(
				new BigDecimal(fieldCompletedWorkNoRetainage.getText()));
		processRequisition
				.setPreviousRequisitionBilled(new BigDecimal(fieldPreviousRequisitionBilled.getText()));
		processRequisition.setCurrentlyPaymentDue(new BigDecimal(fieldCurrentPaymentDue.getText()));
		processRequisition.setBalanceToFinishIncludingRetainage(new BigDecimal(fieldBalanceToFinish.getText()));
		processRequisition.setTotalApprovedChangeOrdersThisMonth(
				new BigDecimal(fieldApprovedChangeOrders.getText()));
		processRequisition.setRequisitionDate(fieldRequisitionDate.getValue());

		processRequisitionService.updateProcessRequisition(processRequisition.getId(), processRequisition);
	}

	@FXML
	public void returnToProjectDetailView(ActionEvent event) {
	}
}

package com.constructionmanager.manager.ui.controllers;

import java.io.IOException;
import java.math.BigDecimal;

import org.springframework.stereotype.Component;

import com.constructionmanager.manager.service.RequisitionService;
import com.constructionmanager.manager.ui.MainApp;
import com.constructionmanager.manager.model.Requisitions;
import com.constructionmanager.manager.model.Projects;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.TextField;
import javafx.scene.Scene;
import javafx.stage.Stage;

@Component
public class RequisitionDetailController {
	private Parent root;
	private Scene scene;
	private Stage stage;

	private RequisitionService requisitionService;
	private Requisitions requisition;
	private Projects project;

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

	public void setRequisitionAndProject(Requisitions requisition, Projects project) {
		this.requisition = requisition;
		this.project = project;
		setupContext();
	}

	public void setupContext() {
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
	public void requisitionUpdateButton(ActionEvent event) {
		if (!requisitionContractPrice.getText().isBlank() && !requisitionContractPrice.getText().isEmpty()) {
			requisition.setContractPrice(new BigDecimal(requisitionContractPrice.getText()));
		}
		if (!requisitionTotalCOAmount.getText().isBlank() && !requisitionTotalCOAmount.getText().isEmpty()) {
			requisition.setTotalChangeOrderAmount(new BigDecimal(requisitionTotalCOAmount.getText()));
		}
		if (!requisitionTotalContractAndCO.getText().isBlank()
				&& !requisitionTotalContractAndCO.getText().isEmpty()) {
			requisition.setTotalContractAndCOAmount(
					new BigDecimal(requisitionTotalContractAndCO.getText()));
		}
		if (!requisitionTotalMoneyReceived.getText().isBlank()
				&& !requisitionTotalMoneyReceived.getText().isEmpty()) {
			requisition.setTotalMoneyReceived(new BigDecimal(requisitionTotalMoneyReceived.getText()));
		}
		if (!requisitionThisRequisitionBilling.getText().isBlank()
				&& !requisitionThisRequisitionBilling.getText().isEmpty()) {
			requisition.setThisRequisitionBilling(
					new BigDecimal(requisitionThisRequisitionBilling.getText()));
		}
		if (!requisitionRetainage.getText().isBlank() && !requisitionRetainage.getText().isEmpty()) {
			requisition.setRetainage(Integer.parseInt(requisitionRetainage.getText()));
		}
		if (!requisitionCompanyName.getText().isBlank() && !requisitionCompanyName.getText().isEmpty()) {
			requisition.setCompanyName(requisitionCompanyName.getText());
		}
		if (!requisitionOwnerFullName.getText().isBlank() && !requisitionOwnerFullName.getText().isEmpty()) {
			requisition.setOwnerOrRepresentativeFullName(requisitionOwnerFullName.getText());
		}

		requisitionService.updateRequisition(project.getId(), requisition.getId(), requisition);
		switchToProjectDetailController();
	}

	public void switchToProjectDetailController() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/ProjectDetailView.fxml"));
			loader.setControllerFactory(MainApp.springContext::getBean);

			root = loader.load();
			ProjectDetailController projectDetailController = loader.getController();
			projectDetailController.setupProjectDetail(project);

			stage = (Stage) ((Node) requisitionCompanyName).getScene().getWindow();
			scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}

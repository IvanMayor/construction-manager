package com.constructionmanager.manager.ui.controllers;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;

import org.springframework.stereotype.Component;

import com.constructionmanager.manager.model.ProcessRequisition;
import com.constructionmanager.manager.model.Projects;
import com.constructionmanager.manager.model.Requisitions;
import com.constructionmanager.manager.service.ProcessRequisitionService;
import com.constructionmanager.manager.service.ProjectService;
import com.constructionmanager.manager.ui.MainApp;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

@Component
public class ProcessRequisitionController {

	private Parent root;
	private Scene scene;
	private Stage stage;

	private Requisitions requisition;
	private Projects project;
	private ProjectService projectService;
	private ProcessRequisitionService processRequisitionService;

	// To set by user
	@FXML
	private TextField fieldCurrentRequisitionBilling;
	// To Calculate by App
	@FXML
	private TextField fieldTotalChangeOrdersToDate;
	// To Calculate by App
	@FXML
	private TextField fieldTotalChangeOrdersAndOriginalContract;
	// To Calculate by App
	@FXML
	private TextField fieldTotalCompletedWork;
	// To Calculate by App
	@FXML
	private TextField fieldTotalCompletedRetainage;
	// To Calculate by App
	@FXML
	private TextField fieldTotalCompletedWorkNoRetainage;
	// To Calculate by App
	@FXML
	private TextField fieldPreviousRequisitionBilled;
	// To Calcualte by App
	@FXML
	private TextField fieldCurrentlyPaymentDue;
	// To Calculate by App
	@FXML
	private TextField fieldBalanceToFinishIncludingRetainage;
	// To Calculate by App
	@FXML
	private TextField fieldTotalApprovedChangeOrdersThisMonth;
	// To Calculate by App
	@FXML
	private DatePicker fieldRequisitionDate;

	public ProcessRequisitionController(ProjectService projectService,
			ProcessRequisitionService processRequisitionService) {
		this.projectService = projectService;
		this.processRequisitionService = processRequisitionService;
	}

	public void setProject(Projects project) {
		this.project = project;
	}

	public void setRequisition(Requisitions requisition) {
		this.requisition = requisition;
	}

	@FXML
	public void createProcessRequisition(ActionEvent event) {
		BigDecimal currentRequisitionBilling = new BigDecimal(fieldCurrentRequisitionBilling.getText());
		BigDecimal totalChangeOrdersToDate = new BigDecimal(fieldTotalChangeOrdersToDate.getText());
		BigDecimal totalChangeOrdersAndOriginalContract = new BigDecimal(
				fieldTotalChangeOrdersAndOriginalContract.getText());
		BigDecimal totalCompletedWork = new BigDecimal(fieldTotalCompletedWork.getText());
		BigDecimal totalCompletedRetainage = new BigDecimal(fieldTotalCompletedRetainage.getText());
		BigDecimal totalCompletedWorkNoRetainage = new BigDecimal(fieldTotalCompletedWorkNoRetainage.getText());
		BigDecimal previousRequisitionBilled = new BigDecimal(fieldPreviousRequisitionBilled.getText());
		BigDecimal currentlyPaymnetDue = new BigDecimal(fieldCurrentlyPaymentDue.getText());
		BigDecimal balanceToFinishIncludingRetainage = new BigDecimal(
				fieldBalanceToFinishIncludingRetainage.getText());
		BigDecimal totalApprovedChangeOrdersThisMonth = new BigDecimal(
				fieldTotalApprovedChangeOrdersThisMonth.getText());
		LocalDate requisitionDate = fieldRequisitionDate.getValue();

		ProcessRequisition processRequisition = new ProcessRequisition(
				currentRequisitionBilling,
				totalChangeOrdersToDate,
				totalChangeOrdersAndOriginalContract,
				totalCompletedWork,
				totalCompletedRetainage,
				totalCompletedWorkNoRetainage,
				previousRequisitionBilled,
				currentlyPaymnetDue,
				balanceToFinishIncludingRetainage,
				totalApprovedChangeOrdersThisMonth,
				requisitionDate);

		processRequisitionService.createProcessRequisition(project.getId(), processRequisition);
		switchBackToProjectDetail();
	}

	@FXML
	public void goBackToProjectDetail(ActionEvent event) {
		switchBackToProjectDetail();
	}

	public void switchBackToProjectDetail() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/ProjectDetailView.fxml"));
			loader.setControllerFactory(MainApp.springContext::getBean);
			root = loader.load();

			ProjectDetailController projectDetailController = loader.getController();
			projectDetailController.setupProjectDetail(project);

			stage = (Stage) ((Node) fieldCurrentRequisitionBilling).getScene().getWindow();
			scene = new Scene(root);
			stage.setScene(scene);
			stage.show();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}

package com.constructionmanager.manager.ui.controllers;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.springframework.stereotype.Component;

import com.constructionmanager.manager.model.ProcessRequisition;
import com.constructionmanager.manager.model.Projects;
import com.constructionmanager.manager.model.Requisitions;
import com.constructionmanager.manager.service.ProcessRequisitionService;
import com.constructionmanager.manager.service.ProjectService;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

@Component
public class ProcessRequisitionController {

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

		processRequisitionService.createProcessRequisition(processRequisition);

	}

}

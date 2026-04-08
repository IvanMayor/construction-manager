package com.constructionmanager.manager.ui.controllers;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.springframework.stereotype.Component;

import com.constructionmanager.manager.model.ProcessRequisition;
import com.constructionmanager.manager.model.Projects;
import com.constructionmanager.manager.model.Requisitions;
import com.constructionmanager.manager.service.ProjectService;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

@Component
public class ProcessRequisitionController {

	private Requisitions requisition;
	private Projects project;
	private ProjectService projectService;

	@FXML
	private TextField totalChangeOrdersToDate;
	@FXML
	private TextField totalChangeOrdersAndOriginalContract;
	@FXML
	private TextField totalCompletedWork;
	@FXML
	private TextField totalCompletedRetainage;
	@FXML
	private TextField totalCompletedWorkNoRetainage;
	@FXML
	private TextField previousRequisitionBilled;
	@FXML
	private TextField currentlyPaymentDue;
	@FXML
	private TextField balanceToFinishIncludingRetainage;
	@FXML
	private TextField totalApprovedChangeOrdersThisMonth;
	@FXML
	private TextField previousContractItemBilled;
	@FXML
	private TextField thisContractItemBilled;
	@FXML
	private TextField totalCompletedItemToDate;
	@FXML
	private TextField totalBalanceToFinishItem;
	@FXML
	private TextField currentRetainageItemToDate;
	@FXML
	private TextField percentCompletedItemToDate;
	@FXML
	private TextField requisitionDate;

	@FXML
	private TextField currentRequisitionBilling;

	public ProcessRequisitionController(ProjectService projectService) {
		this.projectService = projectService;
	}

	public void setProject(Projects project) {
		this.project = project;
	}

	public void setRequisition(Requisitions requisition) {
		this.requisition = requisition;
	}

	public void createProcessRequisition() {
		ProcessRequisition processRequisition = new ProcessRequisition();
		processRequisition
				.setTotalChangeOrdersToDate(projectService.getTotalChangeOrderAmount(project.getId()));
		processRequisition.setTotalChangeOrdersAndOriginalContract(projectService
				.getTotalChangeOrderAmount(project.getId()).add(requisition.getContractPrice()));
		processRequisition.setTotalCompletedWork(new BigDecimal(totalCompletedWork.getText()));
		processRequisition.setTotalCompletedRetainage(processRequisition.getTotalCompletedWork()
				.divide(new BigDecimal(100)).multiply(new BigDecimal(5)));
		processRequisition.setTotalCompletedWorkNoRetainage(processRequisition.getTotalCompletedWork()
				.subtract(processRequisition.getTotalCompletedRetainage()));
		processRequisition.setCurrentlyPaymentDue(new BigDecimal(currentlyPaymentDue.getText()));
		processRequisition.setBalanceToFinishIncludingRetainage(
				requisition.getContractPrice().subtract(processRequisition.getTotalCompletedWork()));
		processRequisition.setTotalApprovedChangeOrdersThisMonth(
				new BigDecimal(totalApprovedChangeOrdersThisMonth.getText()));
		processRequisition.setPreviousContractItemBilled(new BigDecimal(previousContractItemBilled.getText()));
		processRequisition.setThisContractItemBilled(new BigDecimal(thisContractItemBilled.getText()));
		processRequisition.setTotalCompletedItemToDate(new BigDecimal(totalCompletedItemToDate.getText()));
		processRequisition.setTotalBalanceToFinishItem(new BigDecimal(totalBalanceToFinishItem.getText()));
		processRequisition.setCurrentRetainageItemToDate(new BigDecimal(currentRetainageItemToDate.getText()));
		processRequisition
				.setPercentCompletedItemToDate(Integer.parseInt(percentCompletedItemToDate.getText()));
		processRequisition.setRequisitionDate(LocalDate.now());
	}

}

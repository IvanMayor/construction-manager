package com.constructionmanager.manager.ui.controllers;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;

import javafx.scene.control.TableView;

import org.springframework.stereotype.Component;

import com.constructionmanager.manager.model.ProcessRequisition;
import com.constructionmanager.manager.model.Projects;
import com.constructionmanager.manager.model.RequisitionContractItems;
import com.constructionmanager.manager.model.Requisitions;
import com.constructionmanager.manager.service.ProcessRequisitionService;
import com.constructionmanager.manager.service.ProjectService;
import com.constructionmanager.manager.ui.MainApp;

import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
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

	// TODO: To set by user
	@FXML
	private TextField fieldCurrentRequisitionBilling;
	// TODO: To Calculate by App
	@FXML
	private TextField fieldTotalChangeOrdersToDate;
	// TODO: To Calculate by App
	@FXML
	private TextField fieldTotalChangeOrdersAndOriginalContract;
	// TODO: To Calculate by App
	@FXML
	private TextField fieldTotalCompletedWork;
	// TODO: To Calculate by App
	@FXML
	private TextField fieldTotalCompletedRetainage;
	// TODO: To Calculate by App
	@FXML
	private TextField fieldTotalCompletedWorkNoRetainage;
	// TODO: To Calculate by App
	@FXML
	private TextField fieldPreviousRequisitionBilled;
	// TODO: To Calcualte by App
	@FXML
	private TextField fieldCurrentlyPaymentDue;
	// TODO: To Calculate by App
	@FXML
	private TextField fieldBalanceToFinishIncludingRetainage;
	// TODO: To Calculate by App
	@FXML
	private TextField fieldTotalApprovedChangeOrdersThisMonth;
	// TODO: To Calculate by App
	@FXML
	private DatePicker fieldRequisitionDate;

	@FXML
	private TableView<RequisitionContractItems> requisitionContractItemTable;
	@FXML
	private TableColumn<RequisitionContractItems, Integer> columnIdRCI;
	@FXML
	private TableColumn<RequisitionContractItems, String> columnNameRCI;
	@FXML
	private TableColumn<RequisitionContractItems, BigDecimal> columnTotalCostRCI;
	@FXML
	private TableColumn<RequisitionContractItems, BigDecimal> columnRetainageRCI;

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

	public void setUpRequisitionContractItemTable() {
		requisitionContractItemTable
				.setItems(FXCollections.observableArrayList(requisition.getRequisitionContractItems()));
		columnIdRCI.setCellValueFactory(new PropertyValueFactory<>("id"));
		columnNameRCI.setCellValueFactory(new PropertyValueFactory<>("name"));
		columnTotalCostRCI.setCellValueFactory(new PropertyValueFactory<>("totalCost"));
		columnRetainageRCI.setCellValueFactory(new PropertyValueFactory<>("retainage"));
	}

	public void startupProcessRequisitionMethod(Projects project, Requisitions requisition) {
		setRequisition(requisition);
		setProject(project);
		setUpRequisitionContractItemTable();
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

		requisitionContractItemTable
				.setItems(FXCollections.observableArrayList(requisition.getRequisitionContractItems()));
		columnIdRCI.setCellValueFactory(new PropertyValueFactory<>("id"));
		columnNameRCI.setCellValueFactory(new PropertyValueFactory<>("name"));
		columnTotalCostRCI.setCellValueFactory(new PropertyValueFactory<>("totalCost"));
		columnRetainageRCI.setCellValueFactory(new PropertyValueFactory<>("retainage"));
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

	@FXML
	public void createRequisitionContractItemButton(ActionEvent event) {
		try {
			FXMLLoader loader = new FXMLLoader(
					getClass().getResource("/fxml/RequisitionContractItemCreateView.fxml"));
			loader.setControllerFactory(MainApp.springContext::getBean);

			root = loader.load();

			RequisitionContractItemCreateController requisitionContractItemCreateController = loader
					.getController();
			requisitionContractItemCreateController.startUpRequisitionCreateController(requisition);

			stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}

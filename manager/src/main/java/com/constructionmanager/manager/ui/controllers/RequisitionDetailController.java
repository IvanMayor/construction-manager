package com.constructionmanager.manager.ui.controllers;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;

import org.springframework.stereotype.Component;

import com.constructionmanager.manager.service.RequisitionService;
import com.constructionmanager.manager.ui.MainApp;
import com.constructionmanager.manager.model.Requisitions;
import com.constructionmanager.manager.model.ProcessRequisition;
import com.constructionmanager.manager.model.Projects;
import com.constructionmanager.manager.model.RequisitionContractItems;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TableCell;
import javafx.scene.control.Button;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.Scene;
import javafx.stage.Stage;

@Component
public class RequisitionDetailController {
	private Parent root;
	private Scene scene;
	private Stage stage;

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

	@FXML
	private TableView<ProcessRequisition> processRequisitionTable;
	@FXML
	private TableColumn<ProcessRequisition, Integer> columnPRId;
	@FXML
	private TableColumn<ProcessRequisition, BigDecimal> columnPRThisRequisition;
	@FXML
	private TableColumn<ProcessRequisition, BigDecimal> columnPRTotalCompletedWork;
	@FXML
	private TableColumn<ProcessRequisition, BigDecimal> columnPRPaymentDue;
	@FXML
	private TableColumn<ProcessRequisition, BigDecimal> columnPRBalanceToFinish;
	@FXML
	private TableColumn<ProcessRequisition, LocalDate> columnPRRequisitionDate;
	@FXML
	private TableColumn<ProcessRequisition, Void> columnPRDetailButton;

	@FXML
	private TableView<RequisitionContractItems> requisitionContractItemTable;
	@FXML
	private TableColumn<RequisitionContractItems, Integer> columnRCIId;
	@FXML
	private TableColumn<RequisitionContractItems, String> columnRCITitle;
	@FXML
	private TableColumn<RequisitionContractItems, BigDecimal> columnRCITotalCost;
	@FXML
	private TableColumn<RequisitionContractItems, BigDecimal> columnRCIRetainage;
	@FXML
	private TableColumn<RequisitionContractItems, Void> columnRCIDetailButton;

	public RequisitionDetailController(RequisitionService requisitionService) {
		this.requisitionService = requisitionService;
	}

	public void initialize() {
		columnPRDetailButton.setCellFactory(param -> new TableCell<>() {
			private final Button detailProcessRequisitionButton = new Button("Detail");
			{
				detailProcessRequisitionButton.setOnAction(e -> {
					ProcessRequisition processRequisition = getTableView().getItems()
							.get(getIndex());
					switchToProcessRequisitionDetailController(processRequisition);
				});
				detailProcessRequisitionButton.setPrefWidth(65);
			}

			@Override
			protected void updateItem(Void item, boolean empty) {
				super.updateItem(item, empty);
				if (empty) {
					setGraphic(null);
				} else {
					setGraphic(detailProcessRequisitionButton);
				}
			}
		});

	}

	public void setupRequisitionContactItemTable(Requisitions requisition) {
		requisitionContractItemTable
				.setItems(FXCollections.observableArrayList(
						requisitionService.getRequisitionContractItems(requisition.getId())));
		columnRCIId.setCellValueFactory(new PropertyValueFactory<>("id"));
		columnRCITitle.setCellValueFactory(new PropertyValueFactory<>("name"));
		columnRCITotalCost.setCellValueFactory(new PropertyValueFactory<>("totalCost"));
		columnRCIRetainage.setCellValueFactory(new PropertyValueFactory<>("retainage"));
	}

	public void setupProcessRequisitionTable(Requisitions requisition) {
		processRequisitionTable
				.setItems(FXCollections.observableArrayList(
						requisitionService.getProcessRequisitions(requisition.getId())));
		columnPRId.setCellValueFactory(new PropertyValueFactory<>("id"));
		columnPRThisRequisition.setCellValueFactory(new PropertyValueFactory<>("thisRequisitionBilling"));
		columnPRTotalCompletedWork.setCellValueFactory(new PropertyValueFactory<>("totalCompletedWork"));
		columnPRPaymentDue.setCellValueFactory(new PropertyValueFactory<>("currentlyPaymentDue"));
		columnPRBalanceToFinish
				.setCellValueFactory(new PropertyValueFactory<>("balanceToFinishIncludingRetainage"));
		columnPRRequisitionDate.setCellValueFactory(new PropertyValueFactory<>("requisitionDate"));

	}

	public void startupRequisitionControllerMethod(Requisitions requisition) {
		this.requisition = requisition;
		setupContext();
		setupRequisitionContactItemTable(requisition);
		setupProcessRequisitionTable(requisition);
	}

	public void setupContext() {
		requisitionContractPrice.setText(String.valueOf(requisition.getContractPrice()));
		requisitionCompanyName.setText(requisition.getCompanyName());
		requisitionOwnerFullName.setText(requisition.getOwnerOrRepresentativeFullName());
	}

	@FXML
	public void requisitionUpdateButton(ActionEvent event) {
		if (!requisitionContractPrice.getText().isBlank() && !requisitionContractPrice.getText().isEmpty()) {
			requisition.setContractPrice(new BigDecimal(requisitionContractPrice.getText()));
		}
		if (!requisitionCompanyName.getText().isBlank() && !requisitionCompanyName.getText().isEmpty()) {
			requisition.setCompanyName(requisitionCompanyName.getText());
		}
		if (!requisitionOwnerFullName.getText().isBlank() && !requisitionOwnerFullName.getText().isEmpty()) {
			requisition.setOwnerOrRepresentativeFullName(requisitionOwnerFullName.getText());
		}

		requisitionService.updateRequisition(requisition.getProject().getId(), requisition.getId(),
				requisition);
		switchToProjectDetailController();
	}

	public void switchToProjectDetailController() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/ProjectDetailView.fxml"));
			loader.setControllerFactory(MainApp.springContext::getBean);

			root = loader.load();
			ProjectDetailController projectDetailController = loader.getController();
			projectDetailController.setupProjectDetail(requisition.getProject());

			stage = (Stage) ((Node) requisitionCompanyName).getScene().getWindow();
			scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML
	public void switchToProcessRequisitionCreateController(ActionEvent event) {
		try {
			FXMLLoader loader = new FXMLLoader(
					getClass().getResource("/fxml/ProcessRequisitionCreateView.fxml"));
			loader.setControllerFactory(MainApp.springContext::getBean);
			root = loader.load();
			ProcessRequisitionController processRequisitionController = loader.getController();
			processRequisitionController.startupProcessRequisitionMethod(requisition);
			stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
		} catch (IOException exception) {
			exception.printStackTrace();
		}
	}

	@FXML
	public void switchToRequisitionContractItemCreateController(ActionEvent event) {
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

	public void switchToProcessRequisitionDetailController(ProcessRequisition processRequisition) {
		try {
			FXMLLoader loader = new FXMLLoader(
					getClass().getResource("/fxml/ProcessRequisitionDetailView.fxml"));
			loader.setControllerFactory(MainApp.springContext::getBean);
			root = loader.load();

			ProcessRequisitionDetailController processRequisitionDetailController = loader.getController();
			processRequisitionDetailController.startupProcessRequisitionDetailMethod(processRequisition);

			stage = (Stage) ((Node) requisitionCompanyName).getScene().getWindow();
			scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

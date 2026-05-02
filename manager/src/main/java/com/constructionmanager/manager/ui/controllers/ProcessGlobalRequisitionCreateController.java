package com.constructionmanager.manager.ui.controllers;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.constructionmanager.manager.model.ProcessRequisition;
import com.constructionmanager.manager.model.ProcessRequisitionItem;
import com.constructionmanager.manager.model.RequisitionContractItems;
import com.constructionmanager.manager.model.Requisitions;
import com.constructionmanager.manager.service.ProcessGlobalRequisitionService;
import com.constructionmanager.manager.service.ProcessRequisitionItemService;
import com.constructionmanager.manager.service.ProcessRequisitionService;
import com.constructionmanager.manager.service.RequisitionContractItemService;
import com.constructionmanager.manager.ui.MainApp;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

@Component
public class ProcessGlobalRequisitionCreateController {
	private ProcessGlobalRequisitionService processGlobalRequisitionService;
	private ProcessRequisitionItemService processRequisitionItemService;
	private ProcessRequisitionService processRequisitionService;
	private RequisitionContractItemService requisitionContractItemService;
	private List<ProcessRequisitionItem> processRequisitionItems = new ArrayList<>();
	private List<RequisitionContractItems> requisitionContractItems;
	private Requisitions requisition;
	private ProcessRequisition processRequisition = new ProcessRequisition();

	private Parent root;
	private Scene scene;
	private Stage stage;

	public ProcessGlobalRequisitionCreateController(ProcessGlobalRequisitionService processGlobalRequisitionService,
			ProcessRequisitionItemService processRequisitionItemService,
			ProcessRequisitionService processRequisitionService,
			RequisitionContractItemService requisitionContractItemService) {
		this.processGlobalRequisitionService = processGlobalRequisitionService;
		this.processRequisitionItemService = processRequisitionItemService;
		this.requisitionContractItemService = requisitionContractItemService;
		this.processRequisitionService = processRequisitionService;
	}

	@FXML
	private Label labelNameOfRCI;
	@FXML
	private TextField fieldPercentComplete;

	@FXML
	private TableView<ProcessRequisitionItem> tableProcessRequisitionItem;
	@FXML
	private TableColumn<ProcessRequisitionItem, Integer> columnId;
	@FXML
	private TableColumn<ProcessRequisitionItem, BigDecimal> columnPrevReq;
	@FXML
	private TableColumn<ProcessRequisitionItem, BigDecimal> columnThisReq;
	@FXML
	private TableColumn<ProcessRequisitionItem, BigDecimal> columnTotalComplete;
	@FXML
	private TableColumn<ProcessRequisitionItem, BigDecimal> columnRetainage;
	@FXML
	private TableColumn<ProcessRequisitionItem, Integer> columnPercentComplete;
	@FXML
	private TableColumn<ProcessRequisitionItem, LocalDate> columnDate;

	@FXML
	private TextField fieldPreviousReqBill;
	@FXML
	private TextField fieldThisReqBill;
	@FXML
	private TextField fieldChangeOrderToDate;
	@FXML
	private TextField fieldCOAndOriginalContract;
	@FXML
	private TextField fieldCompletedWork;
	@FXML
	private TextField fieldCompletedRetainage;
	@FXML
	private TextField fieldCompWorkNoRetainage;
	@FXML
	private TextField fieldPaymentDue;
	@FXML
	private TextField fieldBalanceToFinish;
	@FXML
	private TextField fieldTotalApprovedCO;
	@FXML
	private DatePicker fieldRequisitionDate;

	private void setListRequisitionContractItems(List<RequisitionContractItems> requisitionContractItems) {
		this.requisitionContractItems = requisitionContractItems;
	}

	private void setRequisition(Requisitions requisition) {
		this.requisition = requisition;
	}

	public void startupProcessGlobalRequisitionMethod(List<RequisitionContractItems> requisitionContractItems,
			Requisitions requisition) {
		setListRequisitionContractItems(requisitionContractItems);
		for (RequisitionContractItems requisitionContractItem : requisitionContractItems) {
			ProcessRequisitionItem processRequisitionItem = new ProcessRequisitionItem();
			processRequisitionItem.setRequisitionContractItem(requisitionContractItem);
			processRequisitionItems.add(processRequisitionItem);
		}
		setupTableProcessRequisitionItems(processRequisitionItems);
		setRequisition(requisition);
	}

	public void setupTableProcessRequisitionItems(List<ProcessRequisitionItem> processRequisitionItems) {
		tableProcessRequisitionItem.setItems(FXCollections.observableArrayList(processRequisitionItems));
		columnId.setCellValueFactory(new PropertyValueFactory<>("id"));
		columnPrevReq.setCellValueFactory(new PropertyValueFactory<>("previousRequisitionItemBilled"));
		columnThisReq.setCellValueFactory(new PropertyValueFactory<>("thisRequisitionItemBilled"));
		columnTotalComplete.setCellValueFactory(new PropertyValueFactory<>("totalCompletedItemToDate"));
		columnRetainage.setCellValueFactory(new PropertyValueFactory<>("retainageItemToDate"));
		columnPercentComplete.setCellValueFactory(new PropertyValueFactory<>("percentItemCompleted"));
		columnDate.setCellValueFactory(new PropertyValueFactory<>("dateCreated"));
	}

	@FXML
	public void rawSelectedAction(MouseEvent event) {
		ProcessRequisitionItem requisitionContractItem = tableProcessRequisitionItem.getSelectionModel()
				.getSelectedItem();
		labelNameOfRCI.setText(requisitionContractItem.getName());
	}

	@FXML
	public void applyFieldsAction(ActionEvent event) {
		ProcessRequisitionItem processRequisitionItem = tableProcessRequisitionItem.getSelectionModel()
				.getSelectedItem();

		processRequisitionItem.setPercentItemCompleted(Integer.parseInt(fieldPercentComplete.getText()));

		processRequisitionItem = processRequisitionItemService
				.setAllFieldsByPercentCompleted(processRequisitionItem.getRequisitionContractItem(),
						processRequisitionItem);
		Integer index = processRequisitionItems
				.indexOf(tableProcessRequisitionItem.getSelectionModel().getSelectedItem());
		processRequisitionItems.set(index, processRequisitionItem);
		tableProcessRequisitionItem.refresh();
		setupProcessRequisitionItemFields(processRequisitionItem);
	}

	public void setupProcessRequisitionItemFields(ProcessRequisitionItem processRequisitionItem) {
		setupTableProcessRequisitionItems(processRequisitionItems);
	}

	public void setupProcessRequisitionFields() {
		processRequisition = processRequisitionService
				.generatePGRFromProcessRequisitionItems(processRequisitionItems);

		fieldPreviousReqBill.setText(String.valueOf(processRequisition.getPreviousRequisitionBilled()));
		fieldThisReqBill.setText(String.valueOf(processRequisition.getThisRequisitionBilling()));
		fieldChangeOrderToDate.setText(String.valueOf(processRequisition.getTotalChangeOrdersToDate()));
		fieldCOAndOriginalContract
				.setText(String.valueOf(processRequisition.getTotalChangeOrdersAndOriginalContract()));
		fieldCompletedWork.setText(String.valueOf(processRequisition.getTotalCompletedWork()));
		fieldCompletedRetainage.setText(String.valueOf(processRequisition.getTotalCompletedRetainage()));
		fieldCompWorkNoRetainage
				.setText(String.valueOf(processRequisition.getTotalCompletedWorkNoRetainage()));
		fieldPaymentDue.setText(String.valueOf(processRequisition.getCurrentlyPaymentDue()));
		fieldBalanceToFinish.setText(String.valueOf(processRequisition.getBalanceToFinishIncludingRetainage()));
		fieldTotalApprovedCO
				.setText(String.valueOf(processRequisition.getTotalApprovedChangeOrdersThisMonth()));
		fieldRequisitionDate.setValue(processRequisition.getRequisitionDate());
	}

	@FXML
	public void createAllProcessRequisitionItems(ActionEvent event) {
		for (ProcessRequisitionItem processRequisitionItem : processRequisitionItems) {
			processRequisitionItemService.createProcessRequisitionItem(
					processRequisitionItem.getRequisitionContractItem().getId(),
					processRequisitionItem);
		}
		setupProcessRequisitionFields();
	}

	@FXML
	public void createProcessGlobalRequisition(ActionEvent event) {
		processRequisitionService.createProcessRequisition(requisition.getId(), processRequisition);
		processGlobalRequisitionService.createProcessGlobalRequisition(processRequisition.getId(),
				processRequisitionItems);
		returnToRequisitionDetailController(event);
	}

	@FXML
	public void returnToRequisitionDetailController(ActionEvent event) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/RequisitionDetailView.fxml"));
			loader.setControllerFactory(MainApp.springContext::getBean);
			root = loader.load();

			RequisitionDetailController requisitionDetailController = loader.getController();
			requisitionDetailController.startupRequisitionControllerMethod(requisition);

			scene = new Scene(root);
			stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			stage.setScene(scene);
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}

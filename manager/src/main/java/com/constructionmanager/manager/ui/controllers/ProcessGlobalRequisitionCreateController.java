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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

@Component
public class ProcessGlobalRequisitionCreateController {
	private ProcessRequisitionItemService processRequisitionItemService;
	private RequisitionContractItemService requisitionContractItemService;
	private List<ProcessRequisitionItem> processRequisitionItems = new ArrayList<>();
	private List<RequisitionContractItems> requisitionContractItems;
	private Requisitions requisition;
	private ProcessRequisition processRequisition = new ProcessRequisition();

	private Parent root;
	private Scene scene;
	private Stage stage;

	public ProcessGlobalRequisitionCreateController(ProcessRequisitionItemService processRequisitionItemService,
			ProcessRequisitionService processRequisitionService,
			RequisitionContractItemService requisitionContractItemService) {
		this.processRequisitionItemService = processRequisitionItemService;
		this.requisitionContractItemService = requisitionContractItemService;
		this.processRequisitionItemService = processRequisitionItemService;
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

	}

	@FXML
	public void createAllProcessRequisitionItems(ActionEvent event) {
		for (ProcessRequisitionItem processRequisitionItem : processRequisitionItems) {
			processRequisitionItemService.createProcessRequisitionItem(
					processRequisitionItem.getRequisitionContractItem().getId(),
					processRequisitionItem);
		}
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

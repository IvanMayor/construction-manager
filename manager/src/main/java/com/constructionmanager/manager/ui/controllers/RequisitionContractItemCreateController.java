package com.constructionmanager.manager.ui.controllers;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.math.BigDecimal;

import org.springframework.stereotype.Component;

import com.constructionmanager.manager.model.RequisitionContractItems;
import com.constructionmanager.manager.model.Requisitions;
import com.constructionmanager.manager.service.RequisitionContractItemService;
import com.constructionmanager.manager.service.RequisitionService;
import com.constructionmanager.manager.ui.MainApp;

@Component
public class RequisitionContractItemCreateController {
	private Parent root;
	private Scene scene;
	private Stage stage;

	private Requisitions requisition;

	private RequisitionContractItemService requisitionContractItemService;
	private RequisitionService requisitionService;

	@FXML
	private Label informationAboutProject;
	@FXML
	private TextField requisitionTextFieldName;
	@FXML
	private TextField requisitionTextFieldTotalCost;
	@FXML
	private TextField fieldRequisitionCIRetainage;
	@FXML
	private TableView<RequisitionContractItems> requisitionContractItemTable;
	@FXML
	private TableColumn<RequisitionContractItems, Integer> requisitionCIId;
	@FXML
	private TableColumn<RequisitionContractItems, String> requisitionCIName;
	@FXML
	private TableColumn<RequisitionContractItems, BigDecimal> requisitionCITotalCost;
	@FXML
	private TableColumn<RequisitionContractItems, BigDecimal> requisitionCIRetainage;

	public RequisitionContractItemCreateController(RequisitionContractItemService requisitionContractItemService,
			RequisitionService requisitionService) {
		this.requisitionContractItemService = requisitionContractItemService;
		this.requisitionService = requisitionService;
	}

	public void setRequisition(Requisitions requisition) {
		this.requisition = requisition;
	}

	public void setUpRequisitionCreateItemTable(Requisitions requisition) {
		requisitionContractItemTable.setItems(FXCollections
				.observableArrayList(
						requisitionService.getRequisitionContractItems(requisition.getId())));

		requisitionCIId.setCellValueFactory(new PropertyValueFactory<>("id"));
		requisitionCIName.setCellValueFactory(new PropertyValueFactory<>("name"));
		requisitionCITotalCost.setCellValueFactory(new PropertyValueFactory<>("totalCost"));
		requisitionCIRetainage.setCellValueFactory(new PropertyValueFactory<>("retainage"));
	}

	public void startUpRequisitionCreateController(Requisitions requisition) {
		setRequisition(requisition);
		displaySomeInformationAboutTheRequisition();
		setUpRequisitionCreateItemTable(requisition);
	}

	@FXML
	public void createRequisitionContractItem(ActionEvent event) {

		RequisitionContractItems requisitionContractItem = new RequisitionContractItems();
		requisitionContractItem.setName(requisitionTextFieldName.getText());
		requisitionContractItem.setTotalCost(new BigDecimal(requisitionTextFieldTotalCost.getText()));
		requisitionContractItem.setRetainage(new BigDecimal(fieldRequisitionCIRetainage.getText()));
		requisitionContractItemService.createRequisitionContractItem(requisition.getId(),
				requisitionContractItem);

		Alert alert = new Alert(AlertType.INFORMATION, "Requisition Contract Item created!!!");
		alert.setTitle("Info");
		alert.setHeaderText("Operation Completed!");
		alert.setContentText("Requisition contract item created!!!");

		setUpRequisitionCreateItemTable(requisition);
		alert.showAndWait();
		switchBackToRequisitionDetailController();
	}

	@FXML
	public void returnToRequisitionDetailController(ActionEvent event) {
		switchBackToRequisitionDetailController();
	}

	public void switchBackToRequisitionDetailController() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/RequisitionDetailView.fxml"));
			loader.setControllerFactory(MainApp.springContext::getBean);
			root = loader.load();
			RequisitionDetailController requisitionDetailController = loader.getController();
			requisitionDetailController.startupRequisitionControllerMethod(requisition);
			stage = (Stage) ((Node) requisitionTextFieldName).getScene().getWindow();
			scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void displaySomeInformationAboutTheRequisition() {
		String companyName = requisition.getCompanyName();
		if (companyName != null) {
			informationAboutProject.setText("Please create Requisition Contract Items for " + companyName);
		}
	}
}

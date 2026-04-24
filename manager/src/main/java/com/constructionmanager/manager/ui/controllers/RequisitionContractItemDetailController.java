package com.constructionmanager.manager.ui.controllers;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;

import org.springframework.stereotype.Component;

import com.constructionmanager.manager.model.ProcessRequisitionItem;
import com.constructionmanager.manager.model.RequisitionContractItems;
import com.constructionmanager.manager.service.RequisitionContractItemService;
import com.constructionmanager.manager.ui.MainApp;

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
import javafx.stage.Stage;

@Component
public class RequisitionContractItemDetailController {

	private Parent root;
	private Scene scene;
	private Stage stage;

	private RequisitionContractItems requisitionContractItem;
	private RequisitionContractItemService requisitionContractItemService;

	@FXML
	private TableView<ProcessRequisitionItem> tableProcessRequisitionItem;
	@FXML
	private TableColumn<ProcessRequisitionItem, Integer> columnPRIId;
	@FXML
	private TableColumn<ProcessRequisitionItem, BigDecimal> columnPRIPrevBill;
	@FXML
	private TableColumn<ProcessRequisitionItem, BigDecimal> columnPRIThisBill;
	@FXML
	private TableColumn<ProcessRequisitionItem, BigDecimal> columnPRITotalComplete;
	@FXML
	private TableColumn<ProcessRequisitionItem, BigDecimal> columnPRITotalToFinish;
	@FXML
	private TableColumn<ProcessRequisitionItem, BigDecimal> columnPRIRetainage;
	@FXML
	private TableColumn<ProcessRequisitionItem, Integer> columnPRIPercentComplete;
	@FXML
	private TableColumn<ProcessRequisitionItem, LocalDate> columnPRIDateCreated;

	@FXML
	private TextField fieldName;
	@FXML
	private TextField fieldTotalCost;
	@FXML
	private TextField fieldRetainage;
	@FXML
	private Label fieldId;

	public RequisitionContractItemDetailController(RequisitionContractItemService requisitionContractItemService) {
		this.requisitionContractItemService = requisitionContractItemService;
	}

	public void setRequisitionContractItem(RequisitionContractItems requisitionContractItem) {
		this.requisitionContractItem = requisitionContractItem;
	}

	public void setupProcessRequisitionTable(RequisitionContractItems requisitionContractItem) {
		tableProcessRequisitionItem.setItems(FXCollections
				.observableList(requisitionContractItem.getProcessRequisitionContractItems()));
		columnPRIId.setCellValueFactory(new PropertyValueFactory<>("id"));
		columnPRIPrevBill.setCellValueFactory(new PropertyValueFactory<>("previousRequisitionItemBilled"));
		columnPRIThisBill.setCellValueFactory(new PropertyValueFactory<>("thisRequisitionItemBilled"));
		columnPRITotalComplete.setCellValueFactory(new PropertyValueFactory<>("totalCompletedItemToDate"));
		columnPRITotalToFinish.setCellValueFactory(new PropertyValueFactory<>("totalToFinishItem"));
		columnPRIRetainage.setCellValueFactory(new PropertyValueFactory<>("retainageItemToDate"));
		columnPRIPercentComplete.setCellValueFactory(new PropertyValueFactory<>("percentItemCompleted"));
		columnPRIDateCreated.setCellValueFactory(new PropertyValueFactory<>("dateCreated"));
	}

	public void startupRCIDetailControllerMethod(RequisitionContractItems requisitionContractItem) {
		setRequisitionContractItem(requisitionContractItem);
		fieldId.setText(String.valueOf(requisitionContractItem.getId()));
		fieldName.setText(requisitionContractItem.getName());
		fieldTotalCost.setText(String.valueOf(requisitionContractItem.getTotalCost()));
		fieldRetainage.setText(String.valueOf(requisitionContractItem.getRetainage()));
		setupProcessRequisitionTable(requisitionContractItem);
	}

	@FXML
	public void updateRCIButton(ActionEvent event) {
		String rciName = fieldName.getText();
		BigDecimal rciTotalCost = new BigDecimal(fieldTotalCost.getText());
		BigDecimal rciRetainage = new BigDecimal(fieldRetainage.getText());

		RequisitionContractItems newRequisitionContractItem = new RequisitionContractItems();

		newRequisitionContractItem.setName(rciName);
		newRequisitionContractItem.setTotalCost(rciTotalCost);
		newRequisitionContractItem.setRetainage(rciRetainage);

		requisitionContractItemService.updateRequisitionContractItem(
				requisitionContractItem.getRequisition().getId(),
				requisitionContractItem.getId(), newRequisitionContractItem);
	}

	@FXML
	public void returnToRequisitionDetailController(ActionEvent event) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/RequisitionDetailView.fxml"));
			loader.setControllerFactory(MainApp.springContext::getBean);
			root = loader.load();
			RequisitionDetailController requisitionDetailController = loader.getController();
			requisitionDetailController
					.startupRequisitionControllerMethod(requisitionContractItem.getRequisition());

			stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML
	public void deleteThisItemButton(ActionEvent event) {
		requisitionContractItemService.deleteRequisitionContractItem(requisitionContractItem.getId());
		returnToRequisitionDetailController(event);
	}

	@FXML
	public void processReuqisitionItemButton(ActionEvent event) {
		try {
			FXMLLoader loader = new FXMLLoader(
					getClass().getResource("/fxml/ProcessRequisitionItemCreateView.fxml"));
			loader.setControllerFactory(MainApp.springContext::getBean);
			root = loader.load();

			ProcessRequisitionItemController processRequisitionItemController = loader.getController();
			processRequisitionItemController.setRequisitionContractItem(requisitionContractItem);

			stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

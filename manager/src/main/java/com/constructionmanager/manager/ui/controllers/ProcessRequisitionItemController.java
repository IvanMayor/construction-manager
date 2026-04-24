package com.constructionmanager.manager.ui.controllers;

import java.io.IOException;
import java.math.BigDecimal;

import org.springframework.stereotype.Component;

import com.constructionmanager.manager.model.ProcessRequisitionItem;
import com.constructionmanager.manager.model.RequisitionContractItems;
import com.constructionmanager.manager.service.ProcessRequisitionItemService;
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
public class ProcessRequisitionItemController {

	private Parent root;
	private Scene scene;
	private Stage stage;

	private RequisitionContractItems requisitionContractItem;
	private ProcessRequisitionItemService processRequisitionItemService;

	@FXML
	private TextField fieldPreviousRequisitionItemBilled;
	@FXML
	private TextField fieldThisRequisitionItemBilled;
	@FXML
	private TextField fieldTotalCompletedItemToDate;
	@FXML
	private TextField fieldTotalToFinishItem;
	@FXML
	private TextField fieldRetainageItemToDate;
	@FXML
	private TextField fieldPercentItemCompleted;
	@FXML
	private DatePicker fieldDateCreated;

	public ProcessRequisitionItemController(
			ProcessRequisitionItemService processRequisitionItemService) {
		this.processRequisitionItemService = processRequisitionItemService;
	}

	public void setRequisitionContractItem(RequisitionContractItems requisitionContractItem) {
		this.requisitionContractItem = requisitionContractItem;
	}

	public void startupRequisitionContractItemDetailController(RequisitionContractItems requisitionContractItem) {
		setRequisitionContractItem(requisitionContractItem);
	}

	// Named by Sarah M.
	// Initialization method for pre defined fields
	// Field being calculated before
	public void madeForSomething() {
		System.out.println("----------");
	}

	// TODO: Create ProcessRequisitionItem from percent work done!!!
	@FXML
	public void createProcessItemButton(ActionEvent event) {
		ProcessRequisitionItem processRequisitionItem = new ProcessRequisitionItem();

		processRequisitionItem.setPreviousRequisitionItemBilled(
				new BigDecimal(fieldPreviousRequisitionItemBilled.getText()));
		processRequisitionItem
				.setThisRequisitionItemBilled(new BigDecimal(fieldThisRequisitionItemBilled.getText()));
		processRequisitionItem
				.setTotalCompletedItemToDate(new BigDecimal(fieldTotalCompletedItemToDate.getText()));
		processRequisitionItem.setTotalToFinishItem(new BigDecimal(fieldTotalToFinishItem.getText()));
		processRequisitionItem.setRetainageItemToDate(new BigDecimal(fieldRetainageItemToDate.getText()));
		processRequisitionItem.setPercentItemCompleted(Integer.parseInt(fieldPercentItemCompleted.getText()));
		processRequisitionItem.setDateCreated(fieldDateCreated.getValue());

		processRequisitionItemService.createProcessRequisitionItem(requisitionContractItem.getId(),
				processRequisitionItem);

		returnToRequisitionContractItemDetailController(event);
	}

	@FXML
	public void returnToRequisitionContractItemDetailController(ActionEvent event) {
		try {
			FXMLLoader loader = new FXMLLoader(
					getClass().getResource("/fxml/RequisitionContractItemDetailView.fxml"));
			loader.setControllerFactory(MainApp.springContext::getBean);

			root = loader.load();

			RequisitionContractItemDetailController requisitionContractItemDetailController = loader
					.getController();
			requisitionContractItemDetailController
					.startupRCIDetailControllerMethod(requisitionContractItem);

			scene = new Scene(root);
			stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			stage.setScene(scene);
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}

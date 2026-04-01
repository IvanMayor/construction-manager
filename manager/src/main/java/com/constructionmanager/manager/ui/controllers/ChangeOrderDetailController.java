package com.constructionmanager.manager.ui.controllers;

import java.io.IOException;
import java.math.BigDecimal;

import org.springframework.stereotype.Component;

import com.constructionmanager.manager.model.ChangeOrders;
import com.constructionmanager.manager.model.Projects;
import com.constructionmanager.manager.service.ChangeOrderService;
import com.constructionmanager.manager.ui.MainApp;
import com.sun.javafx.fxml.FXMLLoaderHelper.FXMLLoaderAccessor;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

@Component
public class ChangeOrderDetailController {
	private Parent root;
	private Scene scene;
	private Stage stage;

	private ChangeOrderService changeOrderService;
	private ChangeOrders changeOrder;
	private Projects project;

	@FXML
	private TextField changeOrderNumber;
	@FXML
	private TextField changeOrderTitle;
	@FXML
	private TextField changeOrderDescription;
	@FXML
	private TextField changeOrderBreakdown;
	@FXML
	private TextField changeOrderPrice;

	public ChangeOrderDetailController(ChangeOrderService changeOrderService) {
		this.changeOrderService = changeOrderService;
	}

	public void setChangeOrder(ChangeOrders changeOrder) {
		this.changeOrder = changeOrder;
	}

	public void setProject(Projects project) {
		this.project = project;
	}

	public void setupContext() {
		changeOrderNumber.setText(String.valueOf(changeOrder.getNumber()));
		changeOrderTitle.setText(changeOrder.getTitle());
		changeOrderDescription.setText(changeOrder.getDescription());
		changeOrderBreakdown.setText(changeOrder.getBreakdown());
		changeOrderPrice.setText(String.valueOf(changeOrder.getPrice()));
	}

	public void updateChangeOrderButton(ActionEvent event) {
		if (!changeOrderNumber.getText().isBlank() && !changeOrderNumber.getText().isEmpty()) {
			changeOrder.setNumber(Integer.parseInt(changeOrderNumber.getText()));
		}
		if (!changeOrderTitle.getText().isBlank() && !changeOrderTitle.getText().isEmpty()) {
			changeOrder.setTitle(changeOrderTitle.getText());
		}
		if (!changeOrderDescription.getText().isBlank() && !changeOrderDescription.getText().isEmpty()) {
			changeOrder.setDescription(changeOrderDescription.getText());
		}
		if (!changeOrderBreakdown.getText().isBlank() && !changeOrderBreakdown.getText().isEmpty()) {
			changeOrder.setBreakdown(changeOrderBreakdown.getText());
		}
		if (!changeOrderPrice.getText().isBlank() && !changeOrderPrice.getText().isEmpty()) {
			changeOrder.setPrice(new BigDecimal(changeOrderPrice.getText()));
		}
		changeOrderService.updateChangeOrder(project.getId(), changeOrder.getId(), changeOrder);

		try {
			FXMLLoader loader = new FXMLLoader(
					getClass().getResource("/fxml/ProjectDetailView.fxml"));
			loader.setControllerFactory(MainApp.springContext::getBean);

			root = loader.load();

			ProjectDetailController projectDetailController = loader.getController();
			projectDetailController.setupProjectDetail(project);
			stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setScene(scene);
			stage.show();

		} catch (IOException exception) {
			exception.printStackTrace();
		}

	}

}

package com.constructionmanager.manager.ui.controllers;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.stereotype.Component;
import javafx.scene.Node;
import com.constructionmanager.manager.model.Projects;
import com.constructionmanager.manager.model.Requisitions;
import com.constructionmanager.manager.service.RequisitionService;
import com.constructionmanager.manager.ui.MainApp;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

@Component
public class RequisitionCreateController {

	private Parent root;
	private Scene scene;
	private Stage stage;

	private RequisitionService requisitionService;
	private Projects project;

	@FXML
	private TextField contractPriceField;
	@FXML
	private TextField companyNameField;
	@FXML
	private TextField ownerFullNameField;

	public RequisitionCreateController(RequisitionService requisitionService) {
		this.requisitionService = requisitionService;
	}

	public void setProject(Projects project) {
		this.project = project;
	}

	@FXML
	public void createRequisition(ActionEvent event) throws IOException {
		Requisitions requisition = new Requisitions();
		requisition.setContractPrice(new BigDecimal(contractPriceField.getText()));
		requisition.setCompanyName(companyNameField.getText());
		requisition.setOwnerOrRepresentativeFullName(ownerFullNameField.getText());

		requisitionService.createRequisition(project.getId(), requisition);

		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/ProjectDetailView.fxml"));
		loader.setControllerFactory(MainApp.springContext::getBean);
		root = loader.load();

		ProjectDetailController projectDetailController = loader.getController();
		projectDetailController.setupProjectDetail(project);

		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

	@FXML
	public void switchBackToProjectDetail(ActionEvent event) {
		try {

			FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/ProjectDetailView.fxml"));
			loader.setControllerFactory(MainApp.springContext::getBean);
			root = loader.load();
			ProjectDetailController projectDetailController = loader.getController();
			projectDetailController.setupProjectDetail(project);
			stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setScene(scene);
			stage.show();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}

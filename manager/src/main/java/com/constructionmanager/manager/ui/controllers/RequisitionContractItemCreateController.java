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

import com.constructionmanager.manager.model.Projects;
import com.constructionmanager.manager.model.RequisitionContractItems;
import com.constructionmanager.manager.service.ProjectService;
import com.constructionmanager.manager.service.RequisitionContractItemService;
import com.constructionmanager.manager.ui.MainApp;

@Component
public class RequisitionContractItemCreateController {
	private Parent root;
	private Scene scene;
	private Stage stage;

	private Projects project;

	private RequisitionContractItemService requisitionContractItemService;
	private ProjectService projectService;

	@FXML
	private Label informationAboutProject;
	@FXML
	private TextField requisitionTextFieldName;
	@FXML
	private TextField requisitionTextFieldTotalCost;
	@FXML
	private TableView<RequisitionContractItems> requisitionContractItemTable;
	@FXML
	private TableColumn<RequisitionContractItems, Integer> requisitionCIId;
	@FXML
	private TableColumn<RequisitionContractItems, String> requisitionCIName;
	@FXML
	private TableColumn<RequisitionContractItems, BigDecimal> requisitionCITotalCost;

	public RequisitionContractItemCreateController(RequisitionContractItemService requisitionContractItemService,
			ProjectService projectService) {
		this.requisitionContractItemService = requisitionContractItemService;
		this.projectService = projectService;
	}

	public void setProject(Projects project) {
		this.project = project;
	}

	public void setUpRequisitionCreateItemTable(Projects project) {
		requisitionContractItemTable.setItems(FXCollections
				.observableArrayList(projectService.getRequisitionContractItems(project.getId())));

		requisitionCIId.setCellValueFactory(new PropertyValueFactory<>("id"));
		requisitionCIName.setCellValueFactory(new PropertyValueFactory<>("name"));
		requisitionCITotalCost.setCellValueFactory(new PropertyValueFactory<>("totalCost"));
	}

	public void startUpRequisitionCreateController(Projects project) {
		setProject(project);
		displaySomeInformationAboutTheProject();
		setUpRequisitionCreateItemTable(project);
	}

	@FXML
	public void createRequisitionContractItem(ActionEvent event) {

		RequisitionContractItems requisitionContractItem = new RequisitionContractItems();
		requisitionContractItem.setName(requisitionTextFieldName.getText());
		requisitionContractItem.setTotalCost(new BigDecimal(requisitionTextFieldTotalCost.getText()));
		requisitionContractItemService.createRequisitionContractItem(project.getId(), requisitionContractItem);

		Alert alert = new Alert(AlertType.INFORMATION, "Requisition Contract Item created!!!");
		alert.setTitle("Info");
		alert.setHeaderText("Operation Completed!");
		alert.setContentText("Requisition contract item created!!!");

		setUpRequisitionCreateItemTable(project);
		alert.showAndWait();
	}

	@FXML
	public void returnToDetailProjectController(ActionEvent event) {
		switchBackToProjectDetailController();
	}

	public void switchBackToProjectDetailController() {

		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/ProjectDetailView.fxml"));
			loader.setControllerFactory(MainApp.springContext::getBean);

			root = loader.load();

			ProjectDetailController projectDetailController = loader.getController();
			projectDetailController.setupProjectDetail(project);

			stage = (Stage) ((Node) requisitionTextFieldName).getScene().getWindow();
			scene = new Scene(root);
			stage.setScene(scene);
			stage.show();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void displaySomeInformationAboutTheProject() {

		String projectName = project.getName();
		String projectAddress = project.getAddress();
		if (projectName != null) {
			informationAboutProject.setText("Please create Requisition Contract Items for " + projectName);
		}
		if (projectAddress != null && projectName == null) {
			informationAboutProject.setText(
					"Please create Requisition Contract Items for project at: " + projectAddress);
		}
	}
}

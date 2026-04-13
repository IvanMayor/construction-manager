package com.constructionmanager.manager.ui.controllers;

import com.constructionmanager.manager.model.ChangeOrders;
import com.constructionmanager.manager.model.ProcessRequisition;
import com.constructionmanager.manager.model.Projects;
import com.constructionmanager.manager.model.Requisitions;
import com.constructionmanager.manager.service.ChangeOrderService;
import com.constructionmanager.manager.service.ProcessRequisitionService;
import com.constructionmanager.manager.service.ProjectService;
import com.constructionmanager.manager.service.RequisitionService;
import com.constructionmanager.manager.ui.MainApp;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

@Component
public class ProjectDetailController {
	private Projects project;
	private Stage stage;
	private Parent root;
	private Scene scene;

	private final ProjectService projectService;
	private final RequisitionService requisitionService;
	private final ChangeOrderService changeOrderService;
	private final ProcessRequisitionService processRequisitionService;

	@FXML
	private Label projectId;
	@FXML
	private TextField projectName;
	@FXML
	private TextField projectAddress;
	@FXML
	private ComboBox<Projects.JobType> projectType;
	@FXML
	private DatePicker projectStartDate;
	@FXML
	private DatePicker projectFinishDate;
	@FXML
	private Button createChangeOrderButton;

	@FXML
	private TableView<ChangeOrders> changeOrderTable;
	@FXML
	private TableColumn<ChangeOrders, Integer> changeOrderId;
	@FXML
	private TableColumn<ChangeOrders, Integer> changeOrderNumber;
	@FXML
	private TableColumn<ChangeOrders, String> changeOrderTitle;
	@FXML
	private TableColumn<ChangeOrders, BigDecimal> changeOrderCost;
	@FXML
	private TableColumn<ChangeOrders, Boolean> changeOrderApproved;
	@FXML
	private TableColumn<ChangeOrders, Void> changeOrderDetail;
	@FXML
	private TableColumn<ChangeOrders, Void> changeOrderDelete;

	@FXML
	private TableView<ProcessRequisition> processRequisitionTable;
	@FXML
	private TableColumn<ProcessRequisition, Integer> fieldProcessRequisitionId;
	@FXML
	private TableColumn<ProcessRequisition, Integer> fieldCurrentRequisitionBilled;
	@FXML
	private TableColumn<ProcessRequisition, BigDecimal> fieldCurrentPaymentDue;
	@FXML
	private TableColumn<ProcessRequisition, Void> buttonProcessRequisitionDetail;
	@FXML
	private TableColumn<ProcessRequisition, Void> buttonProcessRequisitionDelete;

	public ProjectDetailController(ProjectService projectService, RequisitionService requisitionSevice,
			ChangeOrderService changeOrderService, ProcessRequisitionService processRequisitionService) {
		this.projectService = projectService;
		this.requisitionService = requisitionSevice;
		this.changeOrderService = changeOrderService;
		this.processRequisitionService = processRequisitionService;
	}

	public void initialize() {
		changeOrderDelete.setCellFactory(param -> new TableCell<>() {
			private final Button deleteChangeOrderButton = new Button("Delete");
			{
				deleteChangeOrderButton.setOnAction(e -> {
					ChangeOrders changeOrder = getTableView().getItems().get(getIndex());
					Alert alert = new Alert(AlertType.CONFIRMATION);
					alert.setTitle("Confirm Delete");
					alert.setHeaderText("Delete Item");
					alert.setContentText("Are you sure you want to delete Change order: "
							+ changeOrder.getTitle());
					Optional<ButtonType> result = alert.showAndWait();
					if (result.isPresent() && result.get() == ButtonType.OK) {
						changeOrderService.deleteChangeOrder(changeOrder.getId());
						setUpChangeOrderTable(project);
					}
					deleteChangeOrderButton.setPrefWidth(65);
				});
			}

			@Override
			protected void updateItem(Void item, boolean empty) {
				if (empty) {
					setGraphic(null);
				} else {
					setGraphic(deleteChangeOrderButton);
				}
			}
		});

		changeOrderDetail.setCellFactory(param -> new TableCell<>() {
			private final Button editChangeOrderButton = new Button();
			{
				editChangeOrderButton.setOnAction(e -> {
					ChangeOrders changeOrder = getTableView().getItems().get(getIndex());
					redirectToEditChangeOrder(changeOrder);
				});
				editChangeOrderButton.setPrefWidth(65);
				editChangeOrderButton.setText("Edit");
			}

			@Override
			protected void updateItem(Void item, boolean empty) {
				super.updateItem(item, empty);
				if (empty) {
					setGraphic(null);
				} else {
					setGraphic(editChangeOrderButton);
				}
			}
		});
	}

	public void setUpChangeOrderTable(Projects project) {
		changeOrderTable
				.setItems(FXCollections.observableArrayList(
						projectService.getProjectChangeOrders(project.getId())));

		changeOrderId.setCellValueFactory(new PropertyValueFactory<>("id"));
		changeOrderNumber.setCellValueFactory(new PropertyValueFactory<>("number"));
		changeOrderTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
		changeOrderCost.setCellValueFactory(new PropertyValueFactory<>("price"));
		changeOrderApproved.setCellValueFactory(new PropertyValueFactory<>("approved"));
	}

	public void setUpRequisitionTable(Projects project) {
		processRequisitionTable.setItems(FXCollections
				.observableArrayList(projectService.getProcessRequisitions(project.getId())));

		fieldProcessRequisitionId.setCellValueFactory(new PropertyValueFactory<>("id"));
		fieldCurrentRequisitionBilled.setCellValueFactory(new PropertyValueFactory<>("thisRequisitionBilling"));
		fieldCurrentPaymentDue.setCellValueFactory(new PropertyValueFactory<>("currentlyPaymentDue"));

	}

	public void setUpProjectEditFields() {
		projectType.getItems().addAll(Projects.JobType.values());
		projectId.setText(String.valueOf(project.getId()));
		projectName.setText(project.getName());
		projectAddress.setText(project.getAddress());
		projectType.setValue(project.getJobType());
		projectStartDate.setValue(project.getDateStarted());
		projectFinishDate.setValue(project.getDateFinished());
	}

	@FXML
	public void createChangeOrderButton(ActionEvent event) throws IOException {

		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/ChangeOrderCreateView.fxml"));
		loader.setControllerFactory(MainApp.springContext::getBean);
		root = loader.load();

		ChangeOrderCreateViewController changeOrderCreateViewController = loader.getController();
		changeOrderCreateViewController.setProject(project);

		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

	@FXML
	public void createRequisitionButton(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/RequisitionCreateView.fxml"));
		loader.setControllerFactory(MainApp.springContext::getBean);
		root = loader.load();

		RequisitionCreateController requisitionCreateController = loader.getController();
		requisitionCreateController.setProject(project);

		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

	public void setupProjectDetail(Projects project) {
		this.project = project;
		setUpProjectEditFields();
		setUpChangeOrderTable(project);
		setUpRequisitionTable(project);

	}

	public void backToAllProjects(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/ProjectsView.fxml"));
		loader.setControllerFactory(MainApp.springContext::getBean);

		root = loader.load();

		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

	public void updateProject(ActionEvent event) {
		Projects project = new Projects();
		project.setName(projectName.getText());
		project.setAddress(projectAddress.getText());
		project.setJobType(projectType.getValue());
		project.setDateStarted(projectStartDate.getValue());
		project.setDateFinished(projectFinishDate.getValue());

		projectService.updateProject(Integer.parseInt(projectId.getText()), project);

		Alert alert = new Alert(Alert.AlertType.INFORMATION, "Project was successfully updated!!");
		alert.showAndWait();
	}

	public void redirectToEditRequisition(Requisitions requisition) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/RequisitionDetailView.fxml"));
			loader.setControllerFactory(MainApp.springContext::getBean);

			root = loader.load();

			RequisitionDetailController requisitionDetailController = loader.getController();
			requisitionDetailController.setRequisitionAndProject(requisition, project);
			requisitionDetailController.setupContext();

			scene = new Scene(root);
			stage = (Stage) ((Node) changeOrderTable).getScene().getWindow();
			stage.setScene(scene);
			stage.show();
		} catch (IOException exception) {
			exception.printStackTrace();
		}

	}

	@FXML
	public void redirectToEditChangeOrder(ChangeOrders changeOrder) {

		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/ChangeOrderDetailView.fxml"));
			loader.setControllerFactory(MainApp.springContext::getBean);

			root = loader.load();

			ChangeOrderDetailController changeOrderDetailController = loader.getController();
			changeOrderDetailController.setChangeOrder(changeOrder);
			changeOrderDetailController.setProject(project);
			changeOrderDetailController.setupContext();

			scene = new Scene(root);
			stage = (Stage) ((Node) changeOrderTable).getScene().getWindow();
			stage.setScene(scene);
			stage.show();
		} catch (IOException exception) {
			exception.printStackTrace();
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
			requisitionContractItemCreateController.startUpRequisitionCreateController(project);

			stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@FXML
	public void switchToProcessRequisitionView(ActionEvent event) {

		try {
			FXMLLoader loader = new FXMLLoader(
					getClass().getResource("/fxml/ProcessRequisitionCreateView.fxml"));
			loader.setControllerFactory(MainApp.springContext::getBean);

			root = loader.load();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}

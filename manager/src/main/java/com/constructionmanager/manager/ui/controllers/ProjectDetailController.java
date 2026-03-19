package com.constructionmanager.manager.ui.controllers;

import com.constructionmanager.manager.model.ChangeOrders;
import com.constructionmanager.manager.model.Projects;
import com.constructionmanager.manager.model.Requisitions;
import com.constructionmanager.manager.service.ProjectService;
import com.constructionmanager.manager.ui.MainApp;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
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

    @FXML private Label projectId;
    @FXML private TextField projectName;
    @FXML private TextField projectAddress;
    @FXML private ComboBox<Projects.JobType> projectType;
    @FXML private DatePicker projectStartDate;
    @FXML private DatePicker projectFinishDate;
    @FXML private Button createChangeOrderButton;

    @FXML private TableView<ChangeOrders> changeOrderTable;
    @FXML private TableColumn<ChangeOrders, Integer> changeOrderId;
    @FXML private TableColumn<ChangeOrders, Integer> changeOrderNumber;
    @FXML private TableColumn<ChangeOrders, String> changeOrderTitle;
    @FXML private TableColumn<ChangeOrders, BigDecimal> changeOrderCost;
    @FXML private TableColumn<ChangeOrders, LocalDate> changeOrderDate;
    @FXML private TableColumn<ChangeOrders, Void> changeOrderDetail;

    @FXML private TableView<Requisitions> requisitionTable;
    @FXML private TableColumn<Requisitions, Integer> requisitionId;
    @FXML private TableColumn<Requisitions, Integer> requisitionNumber;
    @FXML private TableColumn<Requisitions, BigDecimal> requisitionBilled;
    @FXML private TableColumn<Requisitions, LocalDate> requisitionDateCreated;
    @FXML private TableColumn<Requisitions, Void> requisitionDetail;

    public ProjectDetailController(ProjectService projectService) {
        this.projectService = projectService;
    }

    public void setupProjectDetail(Projects project) {
        this.project = project;

        changeOrderTable.setItems(FXCollections.observableArrayList(project.getChangeOrders()));

        changeOrderId.setCellValueFactory(new PropertyValueFactory<>("id"));
        changeOrderNumber.setCellValueFactory(new PropertyValueFactory<>("number"));
        changeOrderTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        changeOrderCost.setCellValueFactory(new PropertyValueFactory<>("price"));
        changeOrderDate.setCellValueFactory(new PropertyValueFactory<>("dateCreated"));

        requisitionTable.setItems(FXCollections.observableArrayList(project.getRequisitions()));

        requisitionId.setCellValueFactory(new PropertyValueFactory<>("id"));
        requisitionNumber.setCellValueFactory(new PropertyValueFactory<>("number"));
        requisitionBilled.setCellValueFactory(new PropertyValueFactory<>("thisRequisitionBilling"));
        requisitionDateCreated.setCellValueFactory(new PropertyValueFactory<>("dateCreated"));


        projectType.getItems().addAll(Projects.JobType.values());


        projectId.setText(String.valueOf(project.getId()));
        projectName.setText(project.getName());
        projectAddress.setText(project.getAddress());
        projectType.setValue(project.getJobType());
        projectStartDate.setValue(project.getDateStarted());
        projectFinishDate.setValue(project.getDateFinished());

        createChangeOrderButton.setOnAction(e -> {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/ChangeOrderCreateView.fxml"));
            loader.setControllerFactory(MainApp.springContext::getBean);
            try {
                root = loader.load();

                ChangeOrderCreateViewController changeOrderCreateViewController = loader.getController();
                changeOrderCreateViewController.setProject(project);

                stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();

            } catch (IOException exception) {
                exception.printStackTrace();
            }
        });
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
        Projects project = new Projects(
                projectName.getText(),
                projectAddress.getText(),
                projectType.getValue(),
                projectStartDate.getValue(),
                projectFinishDate.getValue(),
                null);

        projectService.updateProject(Integer.parseInt(projectId.getText()), project);

        Alert alert = new Alert(Alert.AlertType.INFORMATION, "Project was successfully updated!!");
        alert.showAndWait();
    }

}

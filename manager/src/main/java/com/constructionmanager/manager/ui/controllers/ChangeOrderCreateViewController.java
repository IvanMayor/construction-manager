package com.constructionmanager.manager.ui.controllers;

import com.constructionmanager.manager.model.ChangeOrders;
import com.constructionmanager.manager.model.Projects;
import com.constructionmanager.manager.service.ChangeOrderService;
import com.constructionmanager.manager.service.ProjectService;
import com.constructionmanager.manager.ui.MainApp;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;

@Component
public class ChangeOrderCreateViewController {
    private Stage stage;
    private Scene scene;
    private Parent root;

    private ChangeOrderService changeOrderService;
    private ProjectService projectService;
    private Projects project;

    @FXML private TextField changeOrderNumber;
    @FXML private TextField changeOrderTitle;
    @FXML private TextField changeOrderDescription;
    @FXML private TextField changeOrderBreakdown;
    @FXML private TextField changeOrderPrice;
    @FXML private Button backToProjectDetailViewButton;


    public ChangeOrderCreateViewController(
            ChangeOrderService changeOrderService,
            ProjectService projectService) {
        this.changeOrderService = changeOrderService;
        this.projectService = projectService;
    }

    public void setProject(Projects project) {this.project = project;}

    @FXML
    public void initializeCreateButton(ActionEvent event) {
        ChangeOrders changeOrder = new ChangeOrders(
                Integer.parseInt(changeOrderNumber.getText()),
                changeOrderTitle.getText(),
                changeOrderDescription.getText(),
                changeOrderBreakdown.getText(),
                LocalDate.now(),
                new BigDecimal(changeOrderPrice.getText()),
                project);

        changeOrderService.createChangeOrder(project.getId(), changeOrder);
        try {
            switchBackToProjectDetail();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    public void switchBackToProjectDetail() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/ProjectDetailView.fxml"));
        loader.setControllerFactory(MainApp.springContext::getBean);

        root = loader.load();
        ProjectDetailController projectDetailController =  loader.getController();
        projectDetailController.setupProjectDetail(project);

        stage = (Stage) changeOrderTitle.getScene().getWindow();
        scene = new Scene(root);

        stage.setScene(scene);
        stage.show();
    }
    public void returnActionButton(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/ProjectDetailView.fxml"));
        loader.setControllerFactory(MainApp.springContext::getBean);

        root = loader.load();

        ProjectDetailController projectDetailController = loader.getController();
        projectDetailController.setupProjectDetail(project);

        scene = new Scene(root);
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
}

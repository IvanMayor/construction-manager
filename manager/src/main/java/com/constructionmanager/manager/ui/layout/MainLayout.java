package com.constructionmanager.manager.ui.layout;

import com.constructionmanager.manager.ui.components.Sidebar;
import com.constructionmanager.manager.ui.view.DashboardView;
import com.constructionmanager.manager.ui.view.ProjectsView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class MainLayout extends BorderPane {
    public MainLayout() {
        setTop(new Sidebar());
        setCenter(new DashboardView());
    }
}

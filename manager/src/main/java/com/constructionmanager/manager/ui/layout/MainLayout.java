package com.constructionmanager.manager.ui.layout;

import com.constructionmanager.manager.ui.components.Sidebar;
import com.constructionmanager.manager.ui.view.ProjectsView;
import javafx.scene.layout.BorderPane;

public class MainLayout extends BorderPane {
    public MainLayout() {
//        setTop(new Sidebar());
        setCenter(new ProjectsView());
    }
}

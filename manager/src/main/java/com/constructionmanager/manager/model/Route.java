package com.constructionmanager.manager.model;

public enum Route {
    LOGIN("/fxml/user/LoginUserView.fxml", false),
    REGISTER("/fxml/user/RegisterUserView.fxml", false),
    PROJECTSLIST("/fxml/ProjectsView.fxml", true),
    PROJECTSCREATE("/fxml/ProjectsCreateView.fxml", true),
    PROJECTDETAIL("/fxml/ProjectDetailView.fxml", true),
    REQUISITIONSCREATE("/fxml/RequisitionCreateView.fxml", true),
    CHANGEORDERCREATE("/fxml/ChangeOrderCreateView.fxml", true);

    private final String fxmlPath;
    private final boolean protectedRoute;

    Route(String fxmlPath, boolean protectedRoute) {
        this.fxmlPath = fxmlPath;
        this.protectedRoute = protectedRoute;
    }

    public String getFxmlPath() {
        return fxmlPath;
    }

    public boolean isProtectedRoute() {
        return protectedRoute;
    }
}

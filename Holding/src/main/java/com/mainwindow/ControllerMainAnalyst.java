package com.mainwindow;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.*;


public class ControllerMainAnalyst {
    @FXML private Button exitButton;

    private MetricsTable model;
    private SceneMainAnalyst view;


    @FXML
    private void initialize() {
        setupButtonActions();
    }

    private void setupButtonActions() {
        exitButton.setOnAction(_ -> exitApp());
    }

    public void setModel(MetricsTable model){
        this.model = model;
    }

    public void setMainAnalyst(SceneMainAnalyst mainAnalyst) {
        this.view = mainAnalyst;
        setModel(view.getModel());
    }

    private void exitApp() {
        Platform.exit();
    }

}
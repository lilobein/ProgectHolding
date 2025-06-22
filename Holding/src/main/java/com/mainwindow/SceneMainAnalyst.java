package com.mainwindow;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

public class SceneMainAnalyst extends Application {

    private final MetricsTable model;


    public SceneMainAnalyst(MetricsTable model){
        this.model = model;
    }

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/login/MainAnalystViewer.fxml"));
            Parent root = loader.load();
            TableView<Metric> metricsTable = (TableView<Metric>) root.lookup("#metricsTable");
            metricsTable.setItems(model.getTableData());
            ControllerMainAnalyst controller = loader.getController();
            controller.setMainAnalyst(this);
            stage.setScene(new Scene(root));
            stage.setTitle("Панель аналитика");
            stage.show();
        } catch (Exception e) {
            System.out.println(e.getMessage());

        }
    }

    public MetricsTable getModel() {
        return model;
    }
}

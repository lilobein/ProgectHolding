package com.mainwindow;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import java.time.LocalDate;


public class SceneMainManager extends Application {
    private TableView<Metric> metricsTable;
    private final MetricsTable model;
    private ControllerMainManager controller;

    private TextField nameField;
    private TextField valueField;
    private TextField importance_constant;
    private TextField currencyField;
    private DatePicker startDate;
    private DatePicker endDate;

    public SceneMainManager(MetricsTable model){
        this.model = model;
    }

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/login/ManagerMainViewer.fxml"));
            Parent root = loader.load();
            metricsTable = (TableView<Metric>) root.lookup("#metricsTable");
            metricsTable.setItems(model.getTableData());
            controller = loader.getController();
            controller.setMainManager(this);
            stage.setScene(new Scene(root));
            stage.setTitle("Панель менеджера");
            stage.show();

        } catch (Exception e) {
            showErrorDialog("Ошибка запуска", e.getMessage());
            e.printStackTrace();
        }
    }

    private void showErrorDialog(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }

    protected Dialog<ButtonType> doAddDialog() {
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.setTitle("Добавить показатель");
        dialog.setHeaderText("Заполните данные");

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20));

        nameField = new TextField();
        valueField = new TextField();
        currencyField = new TextField();
        startDate = new DatePicker();
        endDate = new DatePicker();
        importance_constant = new TextField();

        grid.addRow(0, new Label("Название:"), nameField);
        grid.addRow(1, new Label("Значение:"), valueField);
        grid.addRow(2, new Label("Валюта (ID):"), currencyField);
        grid.addRow(3, new Label("Константа важности:"),importance_constant);
        grid.addRow(4, new Label("Начало периода:"), startDate);
        grid.addRow(5, new Label("Конец периода:"), endDate);

        dialog.getDialogPane().setContent(grid);
        return dialog;
    }

    public Dialog<ButtonType> doEditDialog(Metric metricToEdit){
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.setTitle("Редактирование показателя");
        dialog.setHeaderText("Измените нужные поля");

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20));

        nameField = new TextField(metricToEdit.getMetric_name());
        valueField = new TextField(String.valueOf(metricToEdit.getValue()));
        importance_constant = new TextField(String.valueOf(metricToEdit.getImportance_constant()));
        currencyField = new TextField(String.valueOf(metricToEdit.getCurrency_id()));
        startDate = new DatePicker(metricToEdit.getPeriod_start());
        endDate = new DatePicker(metricToEdit.getPeriod_end());

        grid.addRow(0, new Label("Название:"), nameField);
        grid.addRow(1, new Label("Значение:"), valueField);
        grid.addRow(2, new Label("Валюта (ID):"), importance_constant);
        grid.addRow(3, new Label("Константа важности:"),currencyField);
        grid.addRow(4, new Label("Начало периода:"), startDate);
        grid.addRow(5, new Label("Конец периода:"), endDate);
        dialog.getDialogPane().setContent(grid);
        return dialog;
    }



    public TableView<Metric> getMetricsTable(){return metricsTable;}
    public MetricsTable getModel(){return model;}
    public void refreshTable() {
        model.refreshData();
    }
    public String getMetricName() {
        return nameField.getText();
    }

    public double getMetricValue() {
        return Double.parseDouble(valueField.getText());
    }

    public byte getImportanceConstantValue() {
        return Byte.parseByte(importance_constant.getText());
    }

    public int getCurrencyId() {
        return Integer.parseInt(currencyField.getText());
    }

    public LocalDate getStartDateValue() {
        return startDate.getValue();
    }

    public LocalDate getEndDateValue() {
        return endDate.getValue();
    }

}



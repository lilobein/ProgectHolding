package com.mainwindow;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.sql.SQLException;



public class ControllerMainManager {
    @FXML public Button exitButton;
    @FXML private Button deleteButton;
    @FXML private Button addButton;
    @FXML private Button editButton;

    private MetricsTable model;
    private SceneMainManager view;


    @FXML
    private void initialize() {
        setupButtonActions();
    }

    public void setModel(MetricsTable model){
        this.model = model;
    }

    public void setMainManager(SceneMainManager mainManager) {
        this.view = mainManager;
        setModel(view.getModel());
    }

    private void setupButtonActions() {
        deleteButton.setOnAction(_ -> handleDelete());
        addButton.setOnAction(_ -> handleAdd());
        editButton.setOnAction(_ -> handleEdit());
        exitButton.setOnAction(_ -> exitApp());
    }

    private void handleDelete() {
        Metric selected = view.getMetricsTable().getSelectionModel().getSelectedItem();
        if (selected == null) {
            view.showError("Выберите показатель для удаления");
            return;
        }
        if (view.showConfirmation("Удалить выбранный показатель?")) {
            try {
                model.delete(selected);
                view.refreshTable();
                view.getMetricsTable().getItems().remove(selected);
            } catch (SQLException e) {
                view.showError("Ошибка удаления: " + e.getMessage());
            }
        }
    }


    private void handleAdd() {
        Dialog<ButtonType>  dialog = view.doAddDialog();

        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);
        if (dialog.showAndWait().orElse(ButtonType.CANCEL) == ButtonType.OK) {
            try {
                model.save(view.getMetricName(), view.getMetricValue(),
                        view.getImportanceConstantValue(), view.getCurrencyId(),
                        view.getStartDateValue(), view.getEndDateValue());
                view.refreshTable();
                view.showConfirmation("Показатель создан.");
            } catch (Exception e){
                view.showError("Введены некорректные данные");
                System.out.println(e.getMessage());
                view.showConfirmation("Подсказка: вес важности метрики находится от 1 до 5, максимальный ID валюты - 3, минимальный - 1");

            }
        }
    }


    @FXML
    private void handleEdit() {
        Metric metricToEdit = view.getMetricsTable().getSelectionModel().getSelectedItem();
        if (metricToEdit == null) {
            view.showError("Показатель не выбран");
            return;
        }
        try {
            Dialog<ButtonType>  dialog = view.doEditDialog(metricToEdit);
            dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);
            if (dialog.showAndWait().orElse(ButtonType.CANCEL) == ButtonType.OK) {
            try {
                model.letsEditMetric(metricToEdit, view.getMetricName(), view.getMetricValue(),
                        view.getImportanceConstantValue(), view.getCurrencyId(),
                        view.getStartDateValue(), view.getEndDateValue());
            } catch (Exception e) {
                view.showConfirmation("Проверьте правильность данных");
            }
        }
        } catch (Exception e){
            view.showError("Ошибка в данных!");
        }

    }

    private void exitApp() {
        Platform.exit();
    }


}
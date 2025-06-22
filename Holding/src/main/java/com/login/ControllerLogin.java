package com.login;

import com.mainwindow.MetricsTable;
import com.mainwindow.SceneMainAnalyst;
import com.mainwindow.SceneMainManager;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.SQLException;

public class ControllerLogin {

    @FXML private TextField loginField;
    @FXML private PasswordField passwordField;
    @FXML private Button loginButton;
    private Login login;


    @FXML
    private void initialize() {
        setupButtonStateHandler();
        setupLoginButtonHandler();

    }


    private void setupButtonStateHandler() {
        loginButton.setDisable(true);
        loginField.textProperty().addListener((obs, oldVal, newVal) ->
                updateLoginButtonState()
        );
        passwordField.textProperty().addListener((obs, oldVal, newVal) ->
                updateLoginButtonState()
        );
    }


    private void setupLoginButtonHandler() {
        loginButton.setOnAction(event -> {
            if (!loginButton.isDisabled()) {
                handleLogin();
            }
        });
    }
    private void updateLoginButtonState() {
        boolean fieldsEmpty = loginField.getText().isEmpty() ||
                passwordField.getText().isEmpty();
        loginButton.setDisable(fieldsEmpty);
    }

    @FXML
    private void handleLogin() {
        try {
            login = new Login(loginField.getText(), passwordField.getText());
            if (login.validate()) {
                Stage currentStage = (Stage) loginButton.getScene().getWindow();
                currentStage.close();
                if (login.getUser().isManager()){
                    openManagerWindow();
                } else if (login.getUser().isAnalyst()){
                    openAnalystWindow();
                }
            } else {
                showError("Неверный логин или пароль!");
            }
        } catch (SQLException e) {
            showError("Ошибка базы данных: " + e.getMessage());
        }
    }

    private void openManagerWindow() {
        try {
            Stage managerStage = new Stage();
            MetricsTable table = new MetricsTable(login.getUser());
            SceneMainManager managerScene;
            managerScene = new SceneMainManager(table);
            managerScene.start(managerStage);
        } catch (Exception e) {
            showError("Ошибка открытия панели менеджера: " + e.getMessage());
        }
    }

    private void openAnalystWindow() {
        try {
            Stage analystStage = new Stage();
            MetricsTable table = new MetricsTable(login.getUser());
            SceneMainAnalyst analystScene;
            analystScene = new SceneMainAnalyst(table);
            analystScene.start(analystStage);
        } catch (Exception e) {
            showError("Ошибка открытия панели аналитика: " + e.getMessage());
        }
    }


    private void showError(String message) {
        new Alert(Alert.AlertType.ERROR, message).showAndWait();
    }

}
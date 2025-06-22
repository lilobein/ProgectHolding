package com.login;


import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.SQLException;

public class ControllerLogin {

    @FXML public Button registerButton;
    @FXML private TextField loginField;
    @FXML private PasswordField passwordField;
    @FXML private Button loginButton;

    private Login login;
    private SceneLogin view;



    @FXML
    private void initialize() {
        setupButtonStateHandler();
        setupLoginButtonHandler();
    }


    public void setView(SceneLogin view){
        this.view = view;
    }


    private void setupButtonStateHandler() {
        loginButton.setDisable(true);
        loginField.textProperty().addListener(_ -> updateLoginButtonState());
        passwordField.textProperty().addListener(_ -> updateLoginButtonState());
    }


    private void setupLoginButtonHandler() {
        loginButton.setOnAction(_ -> {
            if (!loginButton.isDisabled()) {
                handleLogin();
            }
        });
        registerButton.setOnAction(_ -> openRegisterWindow());
    }

    private void openRegisterWindow() {
        Login.doAuthorisation();
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
                view.showError("Неверный логин или пароль!", "Попробуйте еще раз");
            }
        } catch (SQLException e) {
            view.showError("Ошибка базы данных: " , e.getMessage());
        }
    }

    private void openManagerWindow() {
        login.loginOverManager();
    }

    private void openAnalystWindow() {
        login.loginOverAnalyst();
    }



}
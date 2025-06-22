package com.authorisation;

import com.login.User;
import com.login.UserDAO;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.sql.SQLException;

public class ControllerAuthorisation {
    @FXML public Button registerButton;
    @FXML public Button exitButton;
    @FXML private TextField usernameField;
    @FXML private PasswordField passwordField;
    @FXML private TextField enterpriseIdField;
    @FXML private ComboBox<Integer> accessLevelComboBox;

    private User user;
    private SceneAuthorisation view;


    public void setView(SceneAuthorisation view){
        this.view = view;
    }

    @FXML
    private void initialize() {
        accessLevelComboBox.getItems().addAll(1, 2);
        setupButtonActions();
        this.user = new User();
    }

    private void setupButtonActions() {
        registerButton.setOnAction(_ -> getNewUser());
        exitButton.setOnAction(_ -> exitApp());
    }

    private void exitApp() {
        Stage stage = (Stage) registerButton.getScene().getWindow();
        stage.close();
    }


    public void getNewUser() throws IllegalArgumentException {
        try {
            validateInput();
            int accessLevel = accessLevelComboBox.getSelectionModel().getSelectedIndex() + 1;

            if (!User.newLogin(usernameField.getText().trim())) {
                view.showErrorDialog("Ошибка сохранения логина", "Пользователь с таким логином уже существует");
                return;
            }

            user.setUsername(usernameField.getText().trim());

            try {
                user.setEnterpriseId(Integer.parseInt(enterpriseIdField.getText().trim()));
            } catch (Exception e){
                view.showErrorDialog("Ошибка ID предприятия", "Такого предприятия не существует. Обратитесь к руководству или попробуйте еще раз.");
                return;
            }

            user.setPassword(passwordField.getText().trim());

            user.setEnterpriseId(Integer.parseInt(enterpriseIdField.getText().trim()));

            user.setAccessLevel(accessLevel);
            UserDAO.saveUser(user);

            view.userCelebration(user);

        } catch (NumberFormatException e) {
            view.showErrorDialog("Ошибка ввода", "ID предприятия должно быть числом");
        } catch (SQLException e) {
            view.showErrorDialog("Ошибка в данных", e.getMessage());
        }
    }

    private void validateInput() throws IllegalArgumentException {
        if (usernameField.getText().trim().isEmpty() ||
                passwordField.getText().trim().isEmpty() ||
                enterpriseIdField.getText().trim().isEmpty()) {
            view.showErrorDialog("Ошибка ввода", "Все поля должны быть заполнены");
        }
    }



}
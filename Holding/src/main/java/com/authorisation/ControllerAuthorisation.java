package com.authorisation;

import com.login.User;
import com.login.UserDAO;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

import java.sql.SQLException;

public class ControllerAuthorisation {
    @FXML public Button registerButton;
    @FXML public Button exitButton;
    @FXML private TextField usernameField;
    @FXML private PasswordField passwordField;
    @FXML private TextField enterpriseIdField;
    @FXML private ComboBox<String> accessLevelComboBox;

    private User user;
    private SceneAuthorisation view;


    public void setView(SceneAuthorisation view){
        this.view = view;
    }

    @FXML
    private void initialize() throws SQLException {
        accessLevelComboBox.getItems().addAll("1", "2");
        setupButtonActions();
        this.user = new User("s", "s", 1, 2);
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
            user.setUsername(usernameField.getText().trim());
            user.setPassword(passwordField.getText().trim());
            user.setEnterpriseId(Integer.parseInt(enterpriseIdField.getText().trim()));
            user.setAccessLevel(accessLevel);
            UserDAO.saveUser(user);
        } catch (SQLException e) {
            showErrorDialog("Ошибка базы данных", e.getMessage());
            throw new IllegalArgumentException("Database error", e);
        } catch (NumberFormatException e) {
            showErrorDialog("Ошибка ввода", "ID предприятия должно быть числом");
            throw new IllegalArgumentException("Invalid enterprise ID", e);
        }
    }

    private void validateInput() throws IllegalArgumentException {
        if (usernameField.getText().trim().isEmpty() ||
                passwordField.getText().trim().isEmpty() ||
                enterpriseIdField.getText().trim().isEmpty()) {
            showErrorDialog("Ошибка ввода", "Все поля должны быть заполнены");
            throw new IllegalArgumentException("All fields must be filled");
        }
    }

    private void showErrorDialog(String title, String message) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

}
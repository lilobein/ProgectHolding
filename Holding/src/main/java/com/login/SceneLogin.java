package com.login;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import java.io.IOException;

public class SceneLogin extends Application {
    private static final String TITLE = "Авторизация";

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/login/login.fxml"));
        Parent root = loader.load();
        stage.setTitle(TITLE);
        ControllerLogin controller = loader.getController();
        controller.setView(this);
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.show();
    }

    protected void showError(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }


}
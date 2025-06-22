package com.authorisation;

import com.login.User;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

public class SceneAuthorisation extends Application {
    private User user;

    public User getUser(){
        return user;
    }

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/login/authorisationWindow.fxml"));
            Parent root = loader.load();
            ControllerAuthorisation controller = loader.getController();
            controller.setView(this);
            stage.setScene(new Scene(root));
            stage.setTitle("Панель менеджера");
            stage.show();

        } catch (Exception e) {
            System.out.println("Ошибка запуска" + e.getMessage());
            e.printStackTrace();
        }
    }

    protected void showConfirmation(String message) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText(message);
        alert.showAndWait().get();
    }

    protected void userCelebration(User user){
        this.user = user;
        showConfirmation("Пользователь " + this.user.getUsername() + " успешно сохранен! Войдите с своими данными");

    }

    protected void showErrorDialog(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}

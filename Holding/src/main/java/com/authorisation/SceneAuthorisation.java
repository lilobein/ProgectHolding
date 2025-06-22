package com.authorisation;

import com.login.User;
import com.mainwindow.ControllerMainManager;
import com.mainwindow.Metric;
import com.mainwindow.SceneMainAnalyst;
import com.mainwindow.SceneMainManager;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
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
}

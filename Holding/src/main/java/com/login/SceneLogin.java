package com.login;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import java.sql.SQLException;

public class SceneLogin extends Application {
    private static final int WIDTH = 850;
    private static final int HEIGHT = 650;
    private static final String TITLE = "Авторизация";

    @Override
    public void start(Stage stage) throws IOException, SQLException {
        Parent root = FXMLLoader.load(getClass().getResource("/com/login/login.fxml"));
        stage.setTitle(TITLE);
        stage.setScene(new Scene(root, WIDTH, HEIGHT));
        stage.setResizable(false);
        stage.show();

    }


}
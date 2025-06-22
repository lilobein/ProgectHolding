package com.login;
import com.authorisation.SceneAuthorisation;
import com.data.QueryResultWrapper;
import com.mainwindow.MetricsTable;
import com.mainwindow.SceneMainAnalyst;
import com.mainwindow.SceneMainManager;
import javafx.stage.Stage;

import java.sql.SQLException;


public class Login extends UserDAO{
    private final String username;
    private final String password;
    private  User user;

    public Login(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public static void doAuthorisation() {
        Stage registStage = new Stage();
        SceneAuthorisation registScene;
        registScene = new SceneAuthorisation();
        registScene.start(registStage);
    }

    public boolean validate() throws SQLException {
        QueryResultWrapper result = findByUsername(username);
        user = (User) result.unwrap();

        if (user == null) {
            return false;
        }

        String hashedInputPassword = hashPassword(password);
        return user.getPassword().equals(hashedInputPassword);
    }

    public void loginOverManager() {
        Stage managerStage = new Stage();
        MetricsTable table = new MetricsTable(user);
        SceneMainManager managerScene;
        managerScene = new SceneMainManager(table);
        managerScene.start(managerStage);
    }

    public void loginOverAnalyst() {
        Stage analystStage = new Stage();
        MetricsTable table = new MetricsTable(user);
        SceneMainAnalyst analystScene;
        analystScene = new SceneMainAnalyst(table);
        analystScene.start(analystStage);
    }



    public User getUser(){return user;}
    public String getUsername() { return username; }
    public String getPassword() { return password; }
}
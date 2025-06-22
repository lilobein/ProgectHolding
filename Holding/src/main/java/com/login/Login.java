package com.login;
import com.data.QueryResultWrapper;

import java.sql.SQLException;

public class Login {
    private final String username;
    private final String password;
    private  User user;

    public Login(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public boolean validate() throws SQLException {
        QueryResultWrapper result = UserDAO.findByUsername(username);
        user = (User) result.unwrap();

        if (user == null || !user.getPassword().equals(password)) {
            return false;
        }
        return true;
    }

    public User getUser(){return user;}
    public String getUsername() { return username; }
    public String getPassword() { return password; }
}
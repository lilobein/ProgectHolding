package com.login;

import java.sql.SQLException;

public class User {
    private int id;
    private String username;
    private String password;
    private int enterpriseId;
    private int accessLevel;

    public static final int MANAGER = 1;
    public static final int ANALYST = 2;

    public User( String username, String password, int enterpriseId, int accessLevel) throws SQLException {
        validateAccessLevel(accessLevel);
        this.username = username;
        this.password = password;
        this.enterpriseId = enterpriseId;
        this.accessLevel = accessLevel;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public Integer getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(int i) {
        this.enterpriseId = i;
    }

    public int getAccessLevel() {
        return accessLevel;
    }

    public void setAccessLevel(int accessLevel) {
        validateAccessLevel(accessLevel);
        this.accessLevel = accessLevel;
    }

    private void validateAccessLevel(int level) {
        if (level < 1 || level > 2) {
            throw new IllegalArgumentException(
                    "Недопустимый уровень доступа. Допустимые значения: 1 (менеджер), 2 (аналитик)"
            );
        }
    }

    public void setUsername(String username){
        this.username = username;
    }

    public void setId(int id){this.id = id;}

    public void setPassword(String password){
        this.password = password;
    }



    public boolean isManager() {
        return accessLevel == MANAGER;
    }
    public boolean isAnalyst() {
        return accessLevel == ANALYST;
    }

}
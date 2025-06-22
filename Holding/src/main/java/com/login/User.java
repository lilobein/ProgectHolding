package com.login;

import java.sql.SQLException;

public class User {
    private int id;
    private String username;
    private String password;
    private final int enterpriseId;
    private int accessLevel;

    public static final int ADMIN = 1;
    public static final int MANAGER = 2;
    public static final int ANALYST = 3;

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

    public int getAccessLevel() {
        return accessLevel;
    }

    public void setAccessLevel(int accessLevel) {
        validateAccessLevel(accessLevel);
        this.accessLevel = accessLevel;
    }

    private void validateAccessLevel(int level) {
        if (level < ADMIN || level > ANALYST) {
            throw new IllegalArgumentException(
                    "Недопустимый уровень доступа. Допустимые значения: 1 (админ), 2 (менеджер), 3 (аналитик)"
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


    public boolean isAdmin() {
        return accessLevel == ADMIN;
    }
    public boolean isManager() {
        return accessLevel == MANAGER;
    }
    public boolean isAnalyst() {
        return accessLevel == ANALYST;
    }

    @Override
    public String toString() {
        String roleName = switch (accessLevel) {
            case ADMIN -> "ADMIN";
            case MANAGER -> "MANAGER";
            case ANALYST -> "ANALYST";
            default -> "UNKNOWN";
        };

        return "User{" +
                "userId=" + id +
                ", username='" + username + '\'' +
                ", enterpriseId=" + enterpriseId +
                ", accessLevel=" + roleName +
                '}';
    }
}
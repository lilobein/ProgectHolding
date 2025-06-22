module com.login {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens com.login to javafx.fxml, javafx.base;
    opens com.mainwindow to javafx.fxml, javafx.base;
    opens com.authorisation to javafx.fxml, javafx.base;
    
    exports com.login;
}
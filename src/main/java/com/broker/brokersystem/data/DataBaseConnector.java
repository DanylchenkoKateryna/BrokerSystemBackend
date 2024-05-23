package com.broker.brokersystem.data;
import java.sql.*;

public class DataBaseConnector {
    private String dataBaseUrl;
    private String dataBaseUser;
    private String dataBasePassword;
    private String driverClass;

    public DataBaseConnector(String dataBaseName) {
        this.dataBaseUrl = "jdbc:mysql://localhost/" + dataBaseName;
        this.dataBaseUser = "admin";
        this.dataBasePassword = "admin";
        this.driverClass = "com.mysql.cj.jdbc.Driver";
    }

    public boolean testDriver() {
        try {
            Class.forName(driverClass);
            return true;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(dataBaseUrl, dataBaseUser, dataBasePassword);
    }
}
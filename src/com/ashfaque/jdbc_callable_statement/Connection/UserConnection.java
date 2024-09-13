package com.ashfaque.jdbc_callable_statement.Connection;

import com.mysql.cj.jdbc.Driver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class UserConnection {
    public static Connection getUserConnection(){
        try {
            Driver driver=new Driver();
            DriverManager.registerDriver(driver);
            return DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc-e3","root","root");
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }
}

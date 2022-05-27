package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    // реализуйте настройку соеденения с БД
    public static Connection getConnection() {
        final String URL = "jdbc:mysql://localhost:3306/preproject1";
        final String USERNAME = "root";
        final String PASSWORD = "pass4mysql";

        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return connection;
    }
}

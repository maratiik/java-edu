package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/test_schema";
    private static final String ID = "root";
    private static final String PASS = "hantyumi";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, ID, PASS);
    }
}

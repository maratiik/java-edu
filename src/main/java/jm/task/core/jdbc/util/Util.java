package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    private static final String DRIVER_NAME = "com.mysql.cj.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/test_schema";
    private static final String ID = "root";
    private static final String PASS = "hantyumi";

    public static Connection getConnection() {
        try {
            Class.forName(DRIVER_NAME);
            Connection conn = DriverManager.getConnection(DB_URL, ID, PASS);
            return conn;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return null;
    }
}

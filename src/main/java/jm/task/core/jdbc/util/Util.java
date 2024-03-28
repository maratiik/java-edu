package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Util {
    private static SessionFactory sessionFactory;
    private static final String DB_URL = "jdbc:mysql://localhost:3306/test_schema";
    private static final String ID = "root";
    private static final String PASS = "hantyumi";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, ID, PASS);
    }

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            Properties prop = new Properties();
            prop.setProperty("hibernate.connection.url", DB_URL);
            prop.setProperty("dialect", "org.hibernate.dialect.MySQL8Dialect");
            prop.setProperty("hibernate.connection.username", ID);
            prop.setProperty("hibernate.connection.password", PASS);
            prop.setProperty("hibernate.connection.driver_class", "com.mysql.cj.jdbc.Driver");

            sessionFactory = new Configuration().addProperties(prop).addAnnotatedClass(User.class)
                    .buildSessionFactory();
        }
        return sessionFactory;
    }
}

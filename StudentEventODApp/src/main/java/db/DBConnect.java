package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnect {
    private static final String DB_URL = "jdbc:sqlite:data/events.db"; // Ensure path is correct

    public static Connection getConnection() {
        try {
            Class.forName("org.sqlite.JDBC"); // Optional in newer JDBC, but safe
            return DriverManager.getConnection(DB_URL);
        } catch (ClassNotFoundException | SQLException e) {
            return null;
        }
    }
}

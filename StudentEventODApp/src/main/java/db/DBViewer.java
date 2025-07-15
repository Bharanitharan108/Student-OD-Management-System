package db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;

// ✅ This import is required!
import db.DBConnect;

public class DBViewer {
    public static void main(String[] args) {
        String sql = "SELECT * FROM od_requests";

        try (Connection conn = DBConnect.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("id"));
                System.out.println("Name: " + rs.getString("name"));
                System.out.println("RegNo: " + rs.getString("reg_no"));
                System.out.println("Department: " + rs.getString("department"));
                System.out.println("Event: " + rs.getString("event"));
                System.out.println("Date: " + rs.getString("date"));
                System.out.println("-----------------------------");
            }

        } catch (SQLException e) {
            System.out.println("Error viewing data: " + e.getMessage());
        }
    }
}

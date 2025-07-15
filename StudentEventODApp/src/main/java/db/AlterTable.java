package db;

import java.sql.Connection;
import java.sql.Statement;
import db.DBConnect;  // 🔧 Import DBConnect

public class AlterTable {
    public static void main(String[] args) {
        try (Connection conn = DBConnect.getConnection()
) {
            String sql = "ALTER TABLE od_requests ADD COLUMN email TEXT";
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(sql);
            System.out.println("Email column added successfully.");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}

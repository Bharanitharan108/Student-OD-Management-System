package utils;

import db.DBConnect;
import java.sql.Connection;
import java.sql.Statement;

public class CleanTable {
    public static void main(String[] args) {
        try (Connection conn = DBConnect.getConnection();
             Statement stmt = conn.createStatement()) {

            String sql = "DELETE FROM od_requests"; // You can also use "DROP TABLE" if needed
            int rowsDeleted = stmt.executeUpdate(sql);

            System.out.println(" Table cleaned. Rows deleted: " + rowsDeleted);
        } catch (Exception e) {
            System.err.println(" Error cleaning table: " + e.getMessage());
        }
    }
}

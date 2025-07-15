package db;

import java.sql.Connection;
import java.sql.Statement;

public class CreateTable {
    public static void main(String[] args) {
        try (Connection conn = DBConnect.getConnection()) {
            String sql = "CREATE TABLE IF NOT EXISTS od_requests (" +
                         "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                         "name TEXT NOT NULL," +
                         "reg_no TEXT NOT NULL," +
                         "department TEXT NOT NULL," +
                         "event TEXT NOT NULL," +
                         "date TEXT NOT NULL," +
                         "email TEXT NOT NULL" +
                         ")";
            Statement stmt = conn.createStatement();
            stmt.execute(sql);
            System.out.println("Table 'od_requests' created or already exists.");
        } catch (Exception e) {
            System.out.println("Error creating table: " + e.getMessage());
        }
    }
}

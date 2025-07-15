package db;

import model.ODRequest;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DBService {
    public static boolean saveRequest(ODRequest request) {
        String sql = "INSERT INTO od_requests (name, reg_no, department, event, date, email) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = DBConnect.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, request.getName());
            pstmt.setString(2, request.getRegNo());
            pstmt.setString(3, request.getDepartment());
            pstmt.setString(4, request.getEvent());
            pstmt.setString(5, request.getDate());
            pstmt.setString(6, request.getEmail());

            pstmt.executeUpdate();
            System.out.println(" Data inserted successfully.");
            return true;
        } catch (SQLException e) {
            System.out.println(" Error inserting data: " + e.getMessage());
            return false;
        }
    }
}

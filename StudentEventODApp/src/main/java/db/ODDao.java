package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import model.ODRequest;

public class ODDao {

    public static boolean insertRequest(Connection conn, ODRequest req) throws SQLException {
        String sql = "INSERT INTO od_requests (name, reg_no, department, event, date, email) " +
                     "VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, req.getName());
            stmt.setString(2, req.getRegNo());
            stmt.setString(3, req.getDepartment());
            stmt.setString(4, req.getEvent());
            stmt.setString(5, req.getDate());
            stmt.setString(6, req.getEmail());

            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0;
        }
    }
}


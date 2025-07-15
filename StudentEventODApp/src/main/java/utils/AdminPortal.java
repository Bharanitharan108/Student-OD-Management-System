package utils;

import db.DBConnect;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class AdminPortal extends JFrame {
    private final JTable table;
    private final DefaultTableModel tableModel;

    public AdminPortal() {
        setTitle("Admin Portal - OD Requests");
        setSize(800, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        tableModel = new DefaultTableModel(new String[]{"ID", "Name", "Reg No", "Department", "Event", "Date", "Email"}, 0);
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);

        JButton refreshButton = new JButton("Refresh");
        refreshButton.addActionListener(e -> loadData());

        JPanel panel = new JPanel(new BorderLayout());
        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(refreshButton, BorderLayout.SOUTH);

        add(panel);
        loadData();
    }

    private void loadData() {
        tableModel.setRowCount(0); // clear previous data

        try (Connection conn = DBConnect.getConnection()) {
            String query = "SELECT * FROM od_requests";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                Object[] row = {
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("reg_no"),
                    rs.getString("department"),
                    rs.getString("event"),
                    rs.getString("date"),
                    rs.getString("email")
                };
                tableModel.addRow(row);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error loading data: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new AdminPortal().setVisible(true));
    }
}

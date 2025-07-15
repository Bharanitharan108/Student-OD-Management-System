package utils;

import db.DBService;
import model.ODRequest;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame("SRI KRISHNA ARTS AND SCIENCE COLLEGE - OD Form");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 400);

        JPanel panel = new JPanel(new GridLayout(7, 2, 10, 10));
        JLabel header = new JLabel("SRI KRISHNA ARTS AND SCIENCE COLLEGE", SwingConstants.CENTER);
        header.setFont(new Font("Arial", Font.BOLD, 16));
        frame.add(header, BorderLayout.NORTH);

        JTextField nameField = new JTextField();
        JTextField regField = new JTextField();
        JTextField deptField = new JTextField();
        JTextField eventField = new JTextField();
        JTextField dateField = new JTextField();
        JTextField emailField = new JTextField();

        panel.add(new JLabel("Name:"));
        panel.add(nameField);
        panel.add(new JLabel("Reg No:"));
        panel.add(regField);
        panel.add(new JLabel("Department:"));
        panel.add(deptField);
        panel.add(new JLabel("Event:"));
        panel.add(eventField);
        panel.add(new JLabel("Date:"));
        panel.add(dateField);
        panel.add(new JLabel("Email:"));
        panel.add(emailField);

        JButton submit = new JButton("Submit");
        panel.add(new JLabel()); // filler
        panel.add(submit);

        frame.add(panel, BorderLayout.CENTER);
        frame.setVisible(true);

        submit.addActionListener(e -> {
            ODRequest request = new ODRequest(
                nameField.getText(),
                regField.getText(),
                deptField.getText(),
                eventField.getText(),
                dateField.getText(),
                emailField.getText()
            );

            boolean saved = DBService.saveRequest(request);
            if (saved) {
                PDFGenerator.generateODForm(request, "od_request.pdf");


                JOptionPane.showMessageDialog(frame, "Request saved and PDF generated.");
            } else {
                JOptionPane.showMessageDialog(frame, "Failed to save data.");
            }
        });
    }
}

package ui;

import doa.PatientDAO;
import model.Patient;

import javax.swing.*;
import java.awt.*;

public class PatientPanel extends JFrame {
    private JTextField txtFullName, txtAge, txtPhone, txtAddress;
    private JComboBox<String> comboGender, comboBloodGroup;
    private JButton btnIssue, btnCancel, btnBack;

    public PatientPanel() {
        setTitle("Patient Request");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        // 🔹 Top panel with Back button
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        btnBack = new JButton("Back");
        styleBlueButton(btnBack);
        topPanel.add(btnBack);
        add(topPanel, BorderLayout.NORTH);

        // 🔹 Main form panel
        JPanel panel = new JPanel(new GridLayout(7, 2, 15, 15));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));

        // Fonts
        Font fieldFont = new Font("Arial", Font.PLAIN, 18);
        Font labelFont = new Font("Arial", Font.BOLD, 16);

        // Labels + Inputs
        JLabel lblFullName = new JLabel("Full Name:");
        lblFullName.setFont(labelFont);
        panel.add(lblFullName);
        txtFullName = new JTextField();
        txtFullName.setFont(fieldFont);
        panel.add(txtFullName);

        JLabel lblAge = new JLabel("Age:");
        lblAge.setFont(labelFont);
        panel.add(lblAge);
        txtAge = new JTextField();
        txtAge.setFont(fieldFont);
        panel.add(txtAge);

        JLabel lblGender = new JLabel("Gender:");
        lblGender.setFont(labelFont);
        panel.add(lblGender);
        comboGender = new JComboBox<>(new String[]{"Male", "Female", "Other"});
        comboGender.setFont(fieldFont);
        panel.add(comboGender);

        JLabel lblBlood = new JLabel("Blood Group Required:");
        lblBlood.setFont(labelFont);
        panel.add(lblBlood);
        comboBloodGroup = new JComboBox<>(new String[]{"A+", "A-", "B+", "B-", "O+", "O-", "AB+", "AB-"});
        comboBloodGroup.setFont(fieldFont);
        panel.add(comboBloodGroup);

        JLabel lblPhone = new JLabel("Phone:");
        lblPhone.setFont(labelFont);
        panel.add(lblPhone);
        txtPhone = new JTextField();
        txtPhone.setFont(fieldFont);
        panel.add(txtPhone);

        JLabel lblAddress = new JLabel("Address:");
        lblAddress.setFont(labelFont);
        panel.add(lblAddress);
        txtAddress = new JTextField();
        txtAddress.setFont(fieldFont);
        panel.add(txtAddress);

        // 🔹 Buttons Panel (Bottom)
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));

        btnIssue = new JButton("Issue");
        styleBlueButton(btnIssue);

        btnCancel = new JButton("Cancel");
        styleBlueButton(btnCancel);

        buttonPanel.add(btnIssue);
        buttonPanel.add(btnCancel);

        add(panel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        // 🔹 Button Actions
        btnIssue.addActionListener(e -> insertPatient());
        btnCancel.addActionListener(e -> dispose());
        btnBack.addActionListener(e -> dispose()); // ✅ closes and goes back
    }

    // 🔹 Common style for blue buttons
    private void styleBlueButton(JButton btn) {
        btn.setBackground(new Color(0, 123, 255));
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setFont(new Font("Arial", Font.BOLD, 16));
        btn.setPreferredSize(new Dimension(130, 40));
    }

    private void insertPatient() {
        try {
            String fullname = txtFullName.getText().trim();
            String ageStr = txtAge.getText().trim();
            String gender = comboGender.getSelectedItem().toString();
            String bloodGroup = comboBloodGroup.getSelectedItem().toString();
            String phone = txtPhone.getText().trim();
            String address = txtAddress.getText().trim();

            if (fullname.isEmpty() || ageStr.isEmpty() || phone.isEmpty() || address.isEmpty()) {
                JOptionPane.showMessageDialog(this, "⚠ Please fill all fields.");
                return;
            }

            int age = Integer.parseInt(ageStr);

            Patient p = new Patient();
            p.setFullname(fullname);
            p.setAge(age);
            p.setGender(gender);
            p.setBlood_group_requires(bloodGroup);
            p.setPhone(phone);
            p.setAddress(address);

            PatientDAO dao = new PatientDAO();
            boolean success = dao.insertPatient(p);

            if (success) {
                JOptionPane.showMessageDialog(this, "✅ Patient request saved & stock updated!");
                clearForm();
            } else {
                JOptionPane.showMessageDialog(this, "❌ Failed to save patient.");
            }

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "⚠ Age must be a number.");
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "⚠ Error: " + ex.getMessage());
        }
    }

    private void clearForm() {
        txtFullName.setText("");
        txtAge.setText("");
        txtPhone.setText("");
        txtAddress.setText("");
        comboGender.setSelectedIndex(0);
        comboBloodGroup.setSelectedIndex(0);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new PatientPanel().setVisible(true));
    }
}
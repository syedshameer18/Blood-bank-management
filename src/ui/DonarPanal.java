package ui;

import javax.swing.*;
import java.awt.*;
import doa.DonorDOA;
import model.Donor;

public class DonarPanal extends JPanel {

    private JTextField nameField, ageField, phoneField;
    private JComboBox<String> bloodGroupBox, genderBox;
    private JTextArea addressArea;
    private JButton addBtn, backBtn, cancelBtn;

    public DonarPanal() {
        setLayout(new BorderLayout(10, 10));
        setBackground(new Color(245, 249, 255));

        Font textFont = new Font("Arial", Font.PLAIN, 18); // 🔹 Bigger font for text boxes
        Font labelFont = new Font("Arial", Font.BOLD, 16); // 🔹 Slightly bigger for labels

        // 🔹 Top panel with Back button
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        topPanel.setBackground(new Color(245, 249, 255));
        backBtn = new JButton("Back");
        backBtn.setBackground(new Color(0, 123, 255));
        backBtn.setForeground(Color.WHITE);
        backBtn.addActionListener(e -> {
            SwingUtilities.getWindowAncestor(this).dispose();
            new MainMenu().setVisible(true);
        });
        topPanel.add(backBtn);
        add(topPanel, BorderLayout.NORTH);

        // 🔹 Form panel in center
        JPanel formPanel = new JPanel(new GridLayout(7, 2, 12, 12));
        formPanel.setBackground(new Color(245, 249, 255));

        // Full Name
        JLabel nameLbl = new JLabel("Full Name:");
        nameLbl.setFont(labelFont);
        formPanel.add(nameLbl);

        nameField = new JTextField();
        nameField.setFont(textFont); // 🔹 Bigger text inside
        formPanel.add(nameField);

        // Blood Group
        JLabel bgLbl = new JLabel("Blood Group:");
        bgLbl.setFont(labelFont);
        formPanel.add(bgLbl);

        bloodGroupBox = new JComboBox<>(new String[]{"A+", "A-", "B+", "B-", "O+", "O-", "AB+", "AB-"});
        bloodGroupBox.setFont(textFont); // 🔹 Bigger dropdown text
        formPanel.add(bloodGroupBox);

        // Age
        JLabel ageLbl = new JLabel("Age:");
        ageLbl.setFont(labelFont);
        formPanel.add(ageLbl);

        ageField = new JTextField();
        ageField.setFont(textFont);
        formPanel.add(ageField);

        // Gender
        JLabel genderLbl = new JLabel("Gender:");
        genderLbl.setFont(labelFont);
        formPanel.add(genderLbl);

        genderBox = new JComboBox<>(new String[]{"Male", "Female", "Other"});
        genderBox.setFont(textFont);
        formPanel.add(genderBox);

        // Phone
        JLabel phoneLbl = new JLabel("Phone:");
        phoneLbl.setFont(labelFont);
        formPanel.add(phoneLbl);

        phoneField = new JTextField();
        phoneField.setFont(textFont);
        formPanel.add(phoneField);

        // Address
        JLabel addressLbl = new JLabel("Address:");
        addressLbl.setFont(labelFont);
        formPanel.add(addressLbl);

        addressArea = new JTextArea(3, 20);
        addressArea.setFont(textFont); // 🔹 Bigger text area input
        formPanel.add(new JScrollPane(addressArea));

        add(formPanel, BorderLayout.CENTER);

        // 🔹 Bottom panel with Add + Cancel buttons
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        bottomPanel.setBackground(new Color(245, 249, 255));

        addBtn = new JButton("Add Donor");
        addBtn.setBackground(new Color(0, 123, 255));
        addBtn.setForeground(Color.WHITE);
        addBtn.setFont(new Font("Arial", Font.BOLD, 16));
        addBtn.setPreferredSize(new Dimension(160, 45));
        addBtn.addActionListener(e -> addDonor());
        bottomPanel.add(addBtn);

        cancelBtn = new JButton("Cancel");
        cancelBtn.setBackground(new Color(0, 123, 255));
        cancelBtn.setForeground(Color.WHITE);
        cancelBtn.setFont(new Font("Arial", Font.BOLD, 16));
        cancelBtn.setPreferredSize(new Dimension(160, 45));
        cancelBtn.addActionListener(e -> {
            SwingUtilities.getWindowAncestor(this).dispose();
            new MainMenu().setVisible(true);
        });
        bottomPanel.add(cancelBtn);

        add(bottomPanel, BorderLayout.SOUTH);
    }

    private void addDonor() {
        try {
            Donor donor = new Donor();
            donor.setFullname(nameField.getText());
            donor.setBlood_group(bloodGroupBox.getSelectedItem().toString());
            donor.setAge(Integer.parseInt(ageField.getText()));
            donor.setGender(genderBox.getSelectedItem().toString());
            donor.setPhone(phoneField.getText());
            donor.setAddress(addressArea.getText());

            DonorDOA donorDOA = new DonorDOA();
            boolean added = donorDOA.addDonor(donor);

            if (added) {
                JOptionPane.showMessageDialog(this, "Donor added successfully!");
                nameField.setText("");
                ageField.setText("");
                phoneField.setText("");
                addressArea.setText("");
            } else {
                JOptionPane.showMessageDialog(this, "Error adding donor!");
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter a valid number for Age.");
        }
    }
}
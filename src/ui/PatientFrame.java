package ui;

import doa.PatientDAO;
import model.Patient;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class PatientFrame extends JPanel {
    private JTable table;
    private DefaultTableModel model;
    private JTextField searchField;

    public PatientFrame() {
        setLayout(new BorderLayout());

        // 🔹 Top panel with search + buttons
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        searchField = new JTextField(20);
        JButton searchBtn = new JButton("Search");
        JButton refreshBtn = new JButton("Refresh");
        JButton deleteBtn = new JButton("Delete");
        JButton backBtn = new JButton("Back");

        topPanel.add(new JLabel("Search:"));
        topPanel.add(searchField);
        topPanel.add(searchBtn);
        topPanel.add(refreshBtn);
        topPanel.add(deleteBtn);
        topPanel.add(backBtn);

        add(topPanel, BorderLayout.NORTH);

        // 🔹 Table
        model = new DefaultTableModel(new String[]{
                "ID", "Full Name", "Age", "Gender", "Blood Required", "Phone", "Address"
        }, 0);

        table = new JTable(model);
        add(new JScrollPane(table), BorderLayout.CENTER);

        // 🔹 Load data initially
        loadPatients();

        // ✅ Search button action
        searchBtn.addActionListener(e -> {
            String keyword = searchField.getText().trim();
            PatientDAO dao = new PatientDAO();
            List<Patient> patients = dao.searchPatients(keyword);
            loadTable(patients);
        });

        // ✅ Refresh button action
        refreshBtn.addActionListener(e -> loadPatients());

        // ✅ Delete button action
        deleteBtn.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row >= 0) {
                int id = (int) model.getValueAt(row, 0);
                PatientDAO dao = new PatientDAO();
                if (dao.deletePatient(id)) {
                    JOptionPane.showMessageDialog(this, "Patient deleted successfully!");
                    loadPatients();
                } else {
                    JOptionPane.showMessageDialog(this, "Failed to delete patient!");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Please select a patient to delete.");
            }
        });

        // ✅ Back button action
        backBtn.addActionListener(e -> {
            SwingUtilities.getWindowAncestor(this).dispose();
            new MainMenu().setVisible(true);
        });
    }

    private void loadPatients() {
        PatientDAO dao = new PatientDAO();
        List<Patient> list = dao.getAllPatients();
        loadTable(list);
    }

    private void loadTable(List<Patient> patients) {
        model.setRowCount(0);
        for (Patient p : patients) {
            model.addRow(new Object[]{
                    p.getPatient_id(),
                    p.getFullname(),
                    p.getAge(),
                    p.getGender(),
                    p.getBlood_group_requires(),
                    p.getPhone(),
                    p.getAddress()
            });
        }
    }
}
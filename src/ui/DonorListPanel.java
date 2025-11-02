package ui;

import doa.DonorDOA;
import model.Donor;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class DonorListPanel extends JPanel {
    private JTable donorTable;
    private JTextField txtSearch;
    private DefaultTableModel model;
    private DonorDOA dao;

    public DonorListPanel() {
        setLayout(new BorderLayout(10, 10));

        dao = new DonorDOA();

        // 🔹 Top search panel
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        topPanel.add(new JLabel("Search:"));
        txtSearch = new JTextField(20);
        topPanel.add(txtSearch);

        JButton btnSearch = new JButton("Search");
        JButton btnRefresh = new JButton("Refresh");
        JButton btnDelete = new JButton("Delete");
        JButton btnBack = new JButton("Back");

        topPanel.add(btnSearch);
        topPanel.add(btnRefresh);
        topPanel.add(btnDelete);
        topPanel.add(btnBack);

        add(topPanel, BorderLayout.NORTH);

        // 🔹 Table
        String[] cols = {"ID", "Full Name", "Blood Group", "Age", "Gender", "Phone", "Address"};
        model = new DefaultTableModel(cols, 0);
        donorTable = new JTable(model);
        add(new JScrollPane(donorTable), BorderLayout.CENTER);

        // Load donors initially
        loadDonors();

        // 🔹 Button actions
        btnSearch.addActionListener(e -> searchDonors());
        btnRefresh.addActionListener(e -> loadDonors());
        btnDelete.addActionListener(e -> deleteDonor());
        btnBack.addActionListener(e -> {
            SwingUtilities.getWindowAncestor(this).dispose(); // ✅ closes only this frame
        });
    }

    private void loadDonors() {
        model.setRowCount(0); // clear table
        List<Donor> donors = dao.getAllDonors();
        for (Donor d : donors) {
            model.addRow(new Object[]{
                    d.getdonor_id(),
                    d.getFullname(),
                    d.getBlood_group(),
                    d.getAge(),
                    d.getGender(),
                    d.getPhone(),
                    d.getAddress()
            });
        }
    }

    private void searchDonors() {
        String keyword = txtSearch.getText().trim();
        model.setRowCount(0);
        List<Donor> donors = dao.searchDonors(keyword);
        for (Donor d : donors) {
            model.addRow(new Object[]{
                    d.getdonor_id(),
                    d.getFullname(),
                    d.getBlood_group(),
                    d.getAge(),
                    d.getGender(),
                    d.getPhone(),
                    d.getAddress()
            });
        }
    }

    private void deleteDonor() {
        int row = donorTable.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "⚠ Please select a donor to delete.");
            return;
        }

        int donorId = (int) model.getValueAt(row, 0);
        int confirm = JOptionPane.showConfirmDialog(this,
                "Are you sure you want to delete this donor?",
                "Confirm Delete",
                JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            boolean deleted = dao.deleteDonorById(donorId);
            if (deleted) {
                JOptionPane.showMessageDialog(this, "✅ Donor deleted successfully.");
                loadDonors();
            } else {
                JOptionPane.showMessageDialog(this, "❌ Failed to delete donor.");
            }
        }
    }

    // 🔹 Main method for testing standalone
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("View Donors");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(800, 500);
            frame.setLocationRelativeTo(null);
            frame.setContentPane(new DonorListPanel()); // ✅ only panel added
            frame.setVisible(true);
        });
    }
}
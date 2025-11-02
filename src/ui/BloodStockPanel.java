package ui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
import doa.StockDAO;
import model.BloodStock;

public class BloodStockPanel extends JPanel {
    private JTable stockTable;
    private DefaultTableModel tableModel;

    public BloodStockPanel() {
        setLayout(new BorderLayout());
        setBackground(new Color(245, 249, 255)); // Light blue background

        // 🔹 Table
        tableModel = new DefaultTableModel(new String[]{
                "Blood Group", "Units Available"
        }, 0);

        stockTable = new JTable(tableModel);
        add(new JScrollPane(stockTable), BorderLayout.CENTER);

        // 🔹 Top panel for Back button
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        topPanel.setBackground(new Color(245, 249, 255));

        JButton backBtn = new JButton("Back");
        backBtn.setPreferredSize(new Dimension(100, 35)); // Smaller neat size
        backBtn.setBackground(new Color(0, 123, 255));
        backBtn.setForeground(Color.WHITE);
        backBtn.addActionListener(e -> {
            SwingUtilities.getWindowAncestor(this).dispose();
            new MainMenu().setVisible(true);
        });

        topPanel.add(backBtn);
        add(topPanel, BorderLayout.NORTH);

        // 🔹 Bottom panel for Refresh button
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        bottomPanel.setBackground(new Color(245, 249, 255));

        JButton refreshBtn = new JButton("Refresh");
        refreshBtn.setBackground(new Color(0, 123, 255));
        refreshBtn.setForeground(Color.WHITE);
        refreshBtn.setFont(new Font("Arial", Font.BOLD, 14)); // Bigger text
        refreshBtn.setPreferredSize(new Dimension(140, 40));  // Bigger button
        refreshBtn.addActionListener(e -> loadStock());

        bottomPanel.add(refreshBtn);
        add(bottomPanel, BorderLayout.SOUTH);

        // 🔹 Load data initially
        loadStock();
    }

    private void loadStock() {
        StockDAO stockDAO = new StockDAO();
        List<BloodStock> stocks = stockDAO.getAllStock();

        // Clear old data
        tableModel.setRowCount(0);

        // Add rows from DB
        for (BloodStock s : stocks) {
            tableModel.addRow(new Object[]{
                    s.getBlood_group(),
                    s.getUnits_available()
            });
        }
    }
}
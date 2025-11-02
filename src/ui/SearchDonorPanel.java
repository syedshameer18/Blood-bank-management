package ui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import doa.DonorDOA;
import model.Donor;

import java.awt.*;
import java.util.List;

public class SearchDonorPanel extends JPanel {

    private JTextField txtSearch;
    private JTable table;
    private DefaultTableModel model;

    public SearchDonorPanel() {
        setLayout(new BorderLayout());

        // 🔹 Top bar (Back button + search field + button)
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

        JButton backBtn = new JButton("Back");
        backBtn.setBackground(new Color(0, 123, 255));
        backBtn.setForeground(Color.WHITE);
        backBtn.addActionListener(e -> {
            // ✅ Switch back to DonorListPanel
            JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
            frame.setContentPane(new DonorListPanel());
            frame.revalidate();
        });
        topPanel.add(backBtn);

        topPanel.add(new JLabel("Enter Keyword:"));
        txtSearch = new JTextField(15);
        topPanel.add(txtSearch);

        JButton btnSearch = new JButton("Search Donor");
        btnSearch.setBackground(new Color(0, 123, 255));
        btnSearch.setForeground(Color.WHITE);
        btnSearch.addActionListener(e -> searchDonor());
        topPanel.add(btnSearch);

        topPanel.setBackground(new Color(245, 249, 255));
        add(topPanel, BorderLayout.NORTH);

        // 🔹 Table setup
        model = new DefaultTableModel(new Object[]{"Full Name", "Blood Group", "Age", "Gender", "Phone", "Address"}, 0);
        table = new JTable(model);
        add(new JScrollPane(table), BorderLayout.CENTER);

        setBackground(new Color(245, 249, 255));
    }

    private void searchDonor() {
        String keyword = txtSearch.getText().trim();
        DonorDOA donorDOA = new DonorDOA();
        List<Donor> donors = donorDOA.searchDonors(keyword);

        model.setRowCount(0); // clear table

        if (donors.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No results found!");
        } else {
            for (Donor d : donors) {
                model.addRow(new Object[]{
                        d.getFullname(),
                        d.getBlood_group(),
                        d.getAge(),
                        d.getGender(),
                        d.getPhone(),
                        d.getAddress()
                });
            }
        }
    }
}
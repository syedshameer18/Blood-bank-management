package ui;

import javax.swing.JButton;
import javax.swing.border.CompoundBorder;
import javax.swing.border.LineBorder;
import javax.swing.BorderFactory;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;
import java.awt.*;

public class MainMenu extends JFrame {

    public MainMenu() {
        setTitle("Main Menu");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(900, 600);
        setLocationRelativeTo(null);

        JPanel grid = new JPanel(new GridLayout(2, 3, 16, 16));
        grid.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // 🔹 Menu buttons layout
        grid.add(createMenuButton("Donor"));
        grid.add(createMenuButton("Donor List"));
        grid.add(createMenuButton("Patient Request"));
        grid.add(createMenuButton("Patient List"));   // ⬅️ replaced empty cell
        grid.add(createMenuButton("Stock"));
        grid.add(createMenuButton("Logout"));

        add(grid);
    }

    // 🔹 Button creation method
    private JButton createMenuButton(String text) {
        JButton btn = new JButton(text);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 18));
        btn.setBackground(new Color(30, 144, 255));
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);

        btn.setBorder(new CompoundBorder(
            new LineBorder(new Color(25, 25, 112), 2, true),
            BorderFactory.createEmptyBorder(10, 20, 10, 20)
        ));

        btn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                btn.setBackground(new Color(65, 105, 225));
            }
            @Override
            public void mouseExited(MouseEvent e) {
                btn.setBackground(new Color(30, 144, 255));
            }
        });

        // 🔹 Actions
        btn.addActionListener(e -> {
            switch (text) {
                case "Donor":
                    new DonorFrame().setVisible(true);
                    break;
                case "Donor List":
                    new DonorListFrame().setVisible(true);
                    break;
                case "Patient Request":
                    new PatientPanel().setVisible(true);
                    break;
                case "Patient List":
                    new ViewPatientPanel().setVisible(true); // ⬅️ open patient list
                    break;
                case "Stock":
                    new BloodStockFrame().setVisible(true);
                    break;
                case "Logout":
                    JOptionPane.showMessageDialog(null, "You have logged out!");
                    dispose();
                    new LoginFrame().setVisible(true);
                    break;
            }
        });

        return btn;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainMenu().setVisible(true));
    }
}
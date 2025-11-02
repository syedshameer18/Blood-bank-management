package ui;

import javax.swing.*;

public class BloodStockFrame extends JFrame {
    public BloodStockFrame() {
        setTitle("Blood Stock Management");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Only close this window
        setLocationRelativeTo(null);

        add(new BloodStockPanel()); // Add the panel
    }
}
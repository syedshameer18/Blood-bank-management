package ui;

import javax.swing.*;
import java.awt.*;

public class SearchDonorFrame extends JFrame {

    public SearchDonorFrame() {
        setTitle("Search Donor");
        setSize(900, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        // 🔹 Add your panel into frame
        setLayout(new BorderLayout());
        add(new SearchDonorPanel(), BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new SearchDonorFrame().setVisible(true));
    }
}
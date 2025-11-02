package ui;

import javax.swing.*;

public class DonorListFrame extends JFrame {

    public DonorListFrame() {
        setTitle("View Donors");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        add(new DonorListPanel());
    }
}
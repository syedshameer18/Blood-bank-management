package ui;

import javax.swing.*;

public class DonorFrame extends JFrame {

    public DonorFrame() {
        setTitle("Add Donor");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        add(new DonarPanal()); // your existing donor form
    }
}

package ui;

import javax.swing.*;

public class ViewPatientPanel extends JFrame {

    public ViewPatientPanel() {
        setTitle("Patient List");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 600);
        setLocationRelativeTo(null);

        // Add PatientListPanel
        add(new PatientFrame());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new ViewPatientPanel().setVisible(true);
        });
    }
}
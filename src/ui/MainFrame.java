package ui;
import javax.swing.*;
public class MainFrame extends JFrame{
public MainFrame() {
	setTitle("Blood Bank Management");
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	setSize(800,600);
	setLocationRelativeTo(null);
	setContentPane(new DonarPanal());
		
}
}




package ui;
import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LoginFrame extends JFrame {
    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JButton btnLogin;
    

    public LoginFrame() {
        setTitle("Login");
        setSize(400,250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(3, 2, 5, 5));
        panel.setBackground(new Color(245,248,255));
        panel.add(new JLabel("Username:"));
        txtUsername = new JTextField();
        panel.add(txtUsername);
        
        panel.add(new JLabel("Password:"));
        txtPassword = new JPasswordField();
        panel.add(txtPassword);

        btnLogin = new JButton("Login");
        panel.add(btnLogin);
        btnLogin.setBackground(new Color(0,123,255));
        btnLogin.setForeground(Color.white);
        JButton cancelBtn = new JButton("Cancel");
        cancelBtn.setBackground(new Color(0, 123, 255));
        cancelBtn.setForeground(Color.WHITE);
        cancelBtn.addActionListener(e -> System.exit(0));

       
        panel.add(cancelBtn);
   
        add(panel);
        btnLogin.addActionListener(e -> {
            String username = txtUsername.getText();
            String password = new String(txtPassword.getPassword());

            if(username.equals("admin") && password.equals("1234")) {
                JOptionPane.showMessageDialog(this, "Login Successful!");
                 dispose();
new MainMenu().setVisible(true);

               
            } else {
                JOptionPane.showMessageDialog(this, "Invalid Username or Password!");
            }
        });
    }

    public static void main(String[] args) {
        new LoginFrame().setVisible(true);
    }
}
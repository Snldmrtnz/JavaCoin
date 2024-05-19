import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import CommonlyUsedClasses.*;
public class LoginUI extends JFrame implements ActionListener {
    JLabel userLabel, passwordLabel;
    JTextField userField;
    JPasswordField passwordField;
    JButton loginButton;

    public LoginUI() {
        setTitle("Login");
        setSize(300, 150);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        userLabel = new GJLabel("Username:", new Font("Consolas", Font.PLAIN, 15), null, Color.WHITE);
        passwordLabel = new GJLabel("Password:", new Font("Consolas", Font.PLAIN, 15), null, Color.WHITE);
        userField = new JTextField(10);
        passwordField = new JPasswordField(10);
        loginButton = new GJButton("Login", new Font("Consolas", Font.PLAIN, 15), 300, 100);

        loginButton.addActionListener(this);

        JPanel panel = new JPanel(new GridLayout(3, 2, 2, 2));
        panel.setBackground(new Color(0x2C2A32));
        panel.add(userLabel);
        panel.add(userField);
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(new JLabel(""));
        panel.add(loginButton);

        add(panel);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginButton) {

            String username = userField.getText();
            String password = new String(passwordField.getPassword());

            // Perform login check
            if (username.equals("admin") && password.equals("password")) {
                JOptionPane.showMessageDialog(this, "Login successful!");
                dispose();
                new GUI();
            } else {
                JOptionPane.showMessageDialog(this, "Invalid username or password.");
            }
        }
    }
}
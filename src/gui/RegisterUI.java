package gui;

import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JOptionPane;

import com.mysql.cj.log.Log;

import java.awt.Dimension;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class RegisterUI extends LoginUI {
    protected JLabel reEnterLabel;
    protected JPasswordField reEnterPassword;
    protected JLabel emailLabel;
    protected JTextField email;
    protected JLabel phoneLabel;
    protected JTextField phone;
    public RegisterUI() {
        // Change the title

        loginLabel.setText("Register");
        loginLabel.setBounds(225, 30, 200, 40);

        // Remove all action listeners from the "Login" button
        for (var al : loginButton.getActionListeners()) {
            loginButton.removeActionListener(al);
        }
        loginButton.setText("Register");
        loginButton.setBounds(250, 350, 100, 30);
        loginButton.addActionListener(this);

        // Disable the "Login" button in the left panel and enable "Register"
        Login.setEnabled(true);
        Login.addActionListener(e -> {
            frame.dispose();
            LoginUI login = new LoginUI();
        });
        Register.setEnabled(false);

        // Add new features
        emailLabel = new JLabel();
        emailLabel.setText("Email: ");
        emailLabel.setBounds(120, 100, 80, 30);

        email = new JTextField();
        email.setPreferredSize(new Dimension(200, 25));
        email.setBounds(200, 100, 200, 25);

        username.setBounds(200, 150, 200, 25);
        userLabel.setBounds(120, 150, 80, 30);

        phoneLabel = new JLabel();
        phoneLabel.setText("Phone: ");
        phoneLabel.setBounds(120, 200, 80, 30);

        phone = new JTextField();
        phone.setPreferredSize(new Dimension(200, 25));
        phone.setBounds(200, 200, 200, 25);

        passLabel.setBounds(120, 250, 80, 30);
        password.setBounds(200, 250, 200, 25);

        reEnterLabel = new JLabel();
        reEnterLabel.setText("Re-enter: ");
        reEnterLabel.setBounds(120, 300, 80, 30);

        reEnterPassword = new JPasswordField();
        reEnterPassword.setPreferredSize(new Dimension(200, 25));
        reEnterPassword.setBounds(200, 300, 200, 25);
        
        panelCenter.add(emailLabel);
        panelCenter.add(email);
        panelCenter.add(phoneLabel);
        panelCenter.add(phone);
        panelCenter.add(userLabel);
        panelCenter.add(username);
        panelCenter.add(passLabel);
        panelCenter.add(password);
        panelCenter.add(reEnterLabel);
        panelCenter.add(reEnterPassword);
        frame.revalidate();
        frame.repaint();
        frame.setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginButton) {
            String usernameText = username.getText();
            String passwordText = new String(password.getPassword());
            String reEnterText = new String(reEnterPassword.getPassword());

            if (usernameText.equals("") || passwordText.equals("") || reEnterText.equals("")) {
                JOptionPane.showMessageDialog(null, "Please fill in all fields", "Error", JOptionPane.ERROR_MESSAGE);
            } else if (!passwordText.equals(reEnterText)) {
                JOptionPane.showMessageDialog(null, "Passwords do not match", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                // Register the user
                System.out.println("Registering user...");
            }
        }
    }

    public static void main(String[] args) {
        new RegisterUI();
    }
}

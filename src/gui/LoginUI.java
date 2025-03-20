package gui;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JFrame;

public class LoginUI extends FrameUI implements ActionListener{
    //Top components
    protected JTextField username;
    protected JPasswordField password;
    protected JLabel userLabel;
    protected JLabel passLabel;
    protected JLabel loginLabel;
    protected JButton loginButton;
    //Left components
    protected JButton Login;
    protected JButton Register;
    protected JButton Exit;
    public LoginUI() {
        // =====CENTER PANEL=====
        panelCenter.setPreferredSize(new Dimension(100, 100));
        panelCenter.setBackground(new Color(0xFFF9E6));

        username = new JTextField();
        password = new JPasswordField();
        userLabel = new JLabel();
        passLabel = new JLabel();
        loginLabel = new JLabel();
        loginButton = new JButton();

        loginLabel.setText("Login");
        loginLabel.setFont(new Font("Arial", Font.BOLD, 30));
        loginLabel.setBounds(250, 50, 200, 40);

        userLabel.setText("Username: ");
        userLabel.setBounds(120, 150, 80, 30);

        username.setPreferredSize(new Dimension(200, 25));
        username.setBounds(200, 150, 200, 25);

        passLabel.setText("Password: ");
        passLabel.setBounds(120, 200, 80, 30);

        password.setPreferredSize(new Dimension(200, 25));
        password.setBounds(200, 200, 200, 25);

        loginButton.setText("Login");
        loginButton.setBounds(250, 250, 100, 30);
        loginButton.setFocusable(false);
        loginButton.setBackground(new Color(0xFF3D00));
        loginButton.setForeground(new Color(0xFFF9E6));
        loginButton.addActionListener(this);

        //====Left Panel====
        panelLeft.setPreferredSize(new Dimension(100, 100));
        panelLeft.setBackground(new Color(0xFFD600));
        

        Login = new JButton();
        Register = new JButton();
        Exit = new JButton();


        Login.setText("Login");
        Login.setFocusable(false);
        Login.setEnabled(false);
        Login.setBackground(new Color(0xFF3D00));
        Login.setForeground(new Color(0xFFF9E6));

        Register.setText("Register");
        Register.setFocusable(false);
        Register.setBackground(new Color(0xFF3D00));
        Register.setForeground(new Color(0xFFF9E6));
        Register.addActionListener(e -> {
            frame.dispose();
            RegisterUI ru = new RegisterUI();
        });

        Exit.setText("Exit");
        Exit.setFocusable(false);
        Exit.addActionListener(e -> System.exit(0));
        Exit.setBackground(new Color(0xFF3D00));
        Exit.setForeground(new Color(0xFFF9E6));

        // Add components to center panel
        panelCenter.add(loginLabel);
        panelCenter.add(userLabel);
        panelCenter.add(username);
        panelCenter.add(passLabel);
        panelCenter.add(password);
        panelCenter.add(loginButton);
        // Add components to left panel
        panelLeft.add(Login);
        panelLeft.add(Register);
        panelLeft.add(Exit);
        //Revalidate and repaint panels (refresh UI)
        frame.revalidate();
        frame.repaint();
        //Add panels to Frame
        frame.setVisible(true);

    }
    @Override
    public void actionPerformed(ActionEvent e) {
        String user = username.getText();
        String pass = new String(password.getPassword());
        if (user.equals("admin") && pass.equals("admin")) {
            frame.dispose();
        }else if(user.equals("customer") && pass.equals("customer")){
            frame.dispose();
            new CustomerMovie();
        }else{
            JOptionPane.showMessageDialog(null, "This username and password has not found!", "Error", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    public static void main(String[] args) {
        new LoginUI();
    }

}

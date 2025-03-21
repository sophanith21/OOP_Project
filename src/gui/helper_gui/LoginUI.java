package gui.helper_gui;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import cinema.Cinema;
import user.Customer;
import user.User;

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
    //Data
    private Customer user;
    private Cinema cinema;
    public LoginUI(Cinema cinema) {
        super(cinema);
        this.cinema = cinema;
        // =====CENTER PANEL=====
        panelCenter.setPreferredSize(new Dimension(100, 100));
        panelCenter.setBackground(new Color(0xFFFFFF));

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
        loginButton.setBackground(new Color(0x0C0950));
        loginButton.setForeground(new Color(0xFFFFFF));
        loginButton.addActionListener(this);

        //====Left Panel====
        panelLeft.setPreferredSize(new Dimension(100, 100));
        panelLeft.setBackground(new Color(0x261FB3));
        

        Login = new JButton();
        Register = new JButton();
        Exit = new JButton();


        Login.setText("Login");
        Login.setFocusable(false);
        Login.setEnabled(false);
        Login.setBackground(new Color(0xFFFFFF));
        Login.setForeground(new Color(0x0C0950));

        Register.setText("Register");
        Register.setFocusable(false);
        Register.setBackground(new Color(0xFFFFFF));
        Register.setForeground(new Color(0x0C0950));
        Register.addActionListener(e -> {
            frame.dispose();
            RegisterUI ru = new RegisterUI(cinema);
        });

        Exit.setText("Exit");
        Exit.setFocusable(false);
        Exit.addActionListener(e -> System.exit(0));
        Exit.setBackground(new Color(0xFFFFFF));
        Exit.setForeground(new Color(0x0C0950));

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
        this.user = new Customer(1,user, "", pass, "", 0,"","",true);
        this.user.loadData();
        if (!this.user.getEmail().isEmpty()){
            if (user.equals("admin") && pass.equals("admin")) {
                frame.dispose();
            }else if(user.equals(this.user.getUsername()) && pass.equals(this.user.getPassword())){
                frame.dispose();
                new CustomerMovie(cinema,this.user);
            }else{
                JOptionPane.showMessageDialog(null, "This username and password has not found!", "Error", JOptionPane.INFORMATION_MESSAGE);
            }
        }
        
    }
    public static void main(String[] args) {
        //new LoginUI();
    }

}

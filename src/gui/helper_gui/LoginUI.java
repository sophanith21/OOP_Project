package gui.helper_gui;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import cinema.Cinema;
import cinema.Hall;
import cinema.ShowTime;
import user.Admin;
import user.Customer;
import user.User;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.ArrayList;
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
        panelCenter.setLayout(new BoxLayout(panelCenter,BoxLayout.Y_AXIS));

        username = new JTextField();
        password = new JPasswordField();
        userLabel = new JLabel();
        passLabel = new JLabel();
        loginLabel = new JLabel();
        loginButton = new JButton();

        loginLabel.setText("<html><h1 style='font-size:30px'><b>Login<b></h1></html>");
        loginLabel.setFont(new Font("Arial", Font.BOLD, 30));
        loginLabel.setBounds(250, 50, 200, 40);
        JPanel loginPanel = new JPanel(new FlowLayout(FlowLayout.CENTER,0,40));
        loginPanel.add(loginLabel);
        loginPanel.setOpaque(false);

        userLabel.setText("Username: ");
        userLabel.setBounds(120, 150, 80, 30);
        username.setPreferredSize(new Dimension(200, 25));
        username.setBounds(200, 150, 200, 25);
        JPanel userPanel = new JPanel();
        userPanel.add(userLabel);
        userPanel.add(username);
        userPanel.setOpaque(false);

        passLabel.setText("Password: ");
        passLabel.setBounds(120, 200, 80, 30);
        password.setPreferredSize(new Dimension(200, 25));
        password.setBounds(200, 200, 200, 25);
        JPanel passPanel = new JPanel();
        passPanel.add(passLabel);
        passPanel.add(password);
        passPanel.setOpaque(false);

        loginButton.setText("<html><p style='font-size:13px'><b>Login<b></p></html>");
        loginButton.setBounds(250, 250, 100, 30);
        loginButton.setFocusable(false);
        loginButton.setBackground(new Color(0x0C0950));
        loginButton.setForeground(new Color(0xFFFFFF));
        loginButton.addActionListener(this);
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(loginButton);
        buttonPanel.setOpaque(false);
        

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
            new RegisterUI(cinema);
        });

        Exit.setText("Back");
        Exit.setFocusable(false);
        Exit.setBackground(new Color(0xFFFFFF));
        Exit.setForeground(new Color(0x0C0950));
        Exit.addActionListener(e -> {
            new HomeUi(cinema);
            frame.dispose();
            
        });

        Login.setMaximumSize(new Dimension(90, 40));
        Login.setAlignmentX(JButton.CENTER_ALIGNMENT);

        Register.setMaximumSize(new Dimension(90, 40));
        Register.setAlignmentX(JButton.CENTER_ALIGNMENT);

        Exit.setMaximumSize(new Dimension(90, 40));
        Exit.setAlignmentX(JButton.CENTER_ALIGNMENT);


        // Add components to center panel
        panelCenter.add(loginPanel);
        panelCenter.add(Box.createRigidArea(new Dimension(0,30)));
        panelCenter.add(userPanel);
        panelCenter.add(Box.createRigidArea(new Dimension(0,30)));
        panelCenter.add(passPanel);
        panelCenter.add(Box.createRigidArea(new Dimension(0,30)));
        panelCenter.add(buttonPanel);
        // Add components to left panel
        panelLeft.setLayout(new BoxLayout(panelLeft, BoxLayout.Y_AXIS));
        panelLeft.add(Box.createRigidArea(new Dimension(0,20)));
        panelLeft.add(Login);
        panelLeft.add(Box.createRigidArea(new Dimension(0,20)));
        panelLeft.add(Register);
        panelLeft.add(Box.createRigidArea(new Dimension(0,20)));
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

        Customer customer = new Customer(1,user, "", pass, "", 0,"","",true);
        customer.loadData();
        Admin admin = new Admin(1, user, "", pass, "", new ArrayList<>(), true);
        admin.loadData();
        if (!customer.getEmail().isEmpty()){
            if(user.equals(customer.getUsername()) && pass.equals(customer.getPassword()) && "CUSTOMER".equals(customer.getRole())){
                JOptionPane.showMessageDialog(null, "Log in successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
                frame.dispose();                
                new CustomerMenu(cinema,customer);
            }
        } else if (!admin.getEmail().isEmpty()) { // Successful login as Admin
            if (user.equals(admin.getUsername()) && pass.equals(admin.getPassword()) && "ADMIN".equals(admin.getRole())) {
                JOptionPane.showMessageDialog(null, "Logged in successfully as Admin", "Success", JOptionPane.INFORMATION_MESSAGE);
                frame.dispose();
                new AdminMenu(cinema);
            }
        } 
        else {
            JOptionPane.showMessageDialog(null, "This username and password has not found!", "Error", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    public static void main(String[] args) {
        Cinema cinema = new Cinema(); // Assuming you have a default constructor
        new LoginUI(cinema);
    }

}

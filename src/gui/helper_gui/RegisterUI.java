package gui.helper_gui;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import cinema.Cinema;
import user.Customer;
import user.User;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegisterUI extends FrameUI implements ActionListener{
    protected JLabel userLabel;
    protected JTextField username;
    protected JLabel passLabel;
    protected JLabel registerLabel;
    protected JButton registerButton;
    protected JPasswordField password;
    protected JLabel reEnterLabel;
    protected JPasswordField reEnterPassword;
    protected JLabel emailLabel;
    protected JTextField email;
    protected JLabel phoneLabel;
    protected JTextField phone;
    //Left components
    protected JButton Login;
    protected JButton Register;
    protected JButton Exit;

    public RegisterUI(Cinema cinema) {
        super(cinema);
        panelCenter.setLayout(new BoxLayout(panelCenter,BoxLayout.Y_AXIS));

        userLabel = new JLabel();
        username = new JTextField();
        passLabel = new JLabel();
        password = new JPasswordField();
        registerLabel = new JLabel();
        registerButton = new JButton();
        reEnterLabel = new JLabel();
        reEnterPassword = new JPasswordField();
        emailLabel = new JLabel();
        email = new JTextField();
        phoneLabel = new JLabel();
        phone = new JTextField();

        registerLabel.setText("<html><h1 style='font-size:30px'><b>Register<b></h1></html>");
        JPanel registerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER,0,0));
        registerPanel.add(registerLabel);
        registerPanel.setOpaque(false);

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

        reEnterLabel.setText("Re-enter: ");
        reEnterLabel.setBounds(120, 150, 80, 30);
        reEnterPassword.setPreferredSize(new Dimension(200, 25));
        reEnterPassword.setBounds(200, 150, 200, 25);
        JPanel reEnterPanel = new JPanel();
        reEnterPanel.add(reEnterLabel);
        reEnterPanel.add(reEnterPassword);
        reEnterPanel.setOpaque(false);

        emailLabel.setText("Email: ");
        emailLabel.setBounds(120, 150, 80, 30);
        email.setPreferredSize(new Dimension(200, 25));
        email.setBounds(200, 150, 200, 25);
        JPanel emailPanel = new JPanel();
        emailPanel.add(emailLabel);
        emailPanel.add(email);
        emailPanel.setOpaque(false);

        phoneLabel.setText("Phone Number: ");
        phoneLabel.setBounds(120, 150, 80, 30);
        phone.setPreferredSize(new Dimension(200, 25));
        phone.setBounds(200, 150, 200, 25);
        JPanel phonePanel = new JPanel();
        phonePanel.add(phoneLabel);
        phonePanel.add(phone);
        phonePanel.setOpaque(false);


        registerButton.setText("<html><p style='font-size:13px'><b>Register<b></p></html>");
        registerButton.setBounds(250, 250, 100, 30);
        registerButton.setFocusable(false);
        registerButton.setBackground(new Color(0x0C0950));
        registerButton.setForeground(new Color(0xFFFFFF));
        registerButton.addActionListener(this);
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(registerButton);
        buttonPanel.setOpaque(false);
        

        Login = new JButton();
        Register = new JButton();
        Exit = new JButton();


        Login.setText("Login");
        Login.setFocusable(false);
        Login.setBackground(new Color(0xFFFFFF));
        Login.setForeground(new Color(0x0C0950));
        Login.addActionListener(e -> {
            frame.dispose();
            new LoginUI(cinema);
        });

        Register.setText("Register");
        Register.setFocusable(false);
        Register.setEnabled(false);
        Register.setBackground(new Color(0xFFFFFF));
        Register.setForeground(new Color(0x0C0950));
       

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
        panelCenter.add(registerPanel);
        panelCenter.add(Box.createRigidArea(new Dimension(0,10)));
        panelCenter.add(userPanel);
        panelCenter.add(Box.createRigidArea(new Dimension(0,20)));
        panelCenter.add(passPanel);
        panelCenter.add(Box.createRigidArea(new Dimension(0,20)));
        panelCenter.add(reEnterPanel);
        panelCenter.add(Box.createRigidArea(new Dimension(0,20)));
        panelCenter.add(emailPanel);
        panelCenter.add(Box.createRigidArea(new Dimension(0,20)));
        panelCenter.add(phonePanel);
        panelCenter.add(Box.createRigidArea(new Dimension(0,20)));
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
        if (e.getSource() == registerButton) {
            String usernameText = username.getText();
            String passwordText = new String(password.getPassword());
            String reEnterText = new String(reEnterPassword.getPassword());
            String emailText = email.getText();
            String phoneNumber = phone.getText();
            int id = User.getLastIdFromDB()+1;
            if (usernameText.equals("") || passwordText.equals("") || reEnterText.equals("")) {
                JOptionPane.showMessageDialog(null, "Please fill in all fields", "Error", JOptionPane.ERROR_MESSAGE);
            } else if (!passwordText.equals(reEnterText)) {
                JOptionPane.showMessageDialog(null, "Passwords do not match", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                // Register the user
                System.out.println("Registering user...");
                Customer customer= new Customer(id,usernameText,emailText,passwordText,phoneNumber,3,"Normal","Horror",true);
                customer.saveData();
                JOptionPane.showMessageDialog(null, "Registrating successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

    public static void main(String[] args) {
        //new RegisterUI();
    }
}

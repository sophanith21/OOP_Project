package gui.helper_gui;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import cinema.Cinema;

public class HomeUi extends FrameUI{

    protected JLabel title;
    protected JButton loginButton;
    protected JButton registerButton;

    public HomeUi(Cinema cinema){
        super(cinema);
        // =====CENTER PANEL=====
        panelCenter.setPreferredSize(new Dimension(100, 100));
        panelCenter.setBackground(new Color(0xFFFFFF));

        title = new JLabel();
        loginButton = new JButton();
        registerButton = new JButton();
        System.out.println(cinema.name);
        title.setText("Welcome to " + cinema.name);
        title.setBounds(120, 200, 200, 30);

        title.setText("Welcome to " + cinema.name);
        title.setBounds(120, 200, 200, 30);

        loginButton.setText("Login");
        loginButton.setBounds(150, 250, 100, 30);
        loginButton.setFocusable(false);
        loginButton.setBackground(new Color(0x0C0950));
        loginButton.setForeground(new Color(0xFFFFFF));
        loginButton.addActionListener(e -> {
            new LoginUI(cinema);
            frame.dispose();
        });

        registerButton.setText("Register");
        registerButton.setBounds(280,250,100,30);
        registerButton.setFocusable(false);
        registerButton.setBackground(new Color(0x0C0950));
        registerButton.setForeground(new Color(0xFFFFFF));
        registerButton.addActionListener(e -> {
            new RegisterUI(cinema);
            frame.dispose();
        });
        panelCenter.add(title);
        panelCenter.add(registerButton);
        panelCenter.add(loginButton);

        //Revalidate and repaint panels (refresh UI)
        frame.revalidate();
        frame.repaint();
        //Add panels to Frame
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        Cinema cinema = new Cinema("Legend","Phnom Penh",5);
        new HomeUi(cinema);
    }
}

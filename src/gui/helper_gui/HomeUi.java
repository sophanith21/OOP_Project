package gui.helper_gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.Border;

import cinema.Cinema;
import cinema.Hall;
import cinema.Seat;
import cinema.ShowTime;

public class HomeUi extends FrameUI{

    protected JLabel title;
    protected JButton loginButton;
    protected JButton registerButton;

    public HomeUi(Cinema cinema){
        super(cinema);
        // =====CENTER PANEL====
        panelCenter.setBackground(new Color(0xFFF9E6));
        panelCenter.setLayout(new BoxLayout(panelCenter,BoxLayout.Y_AXIS));
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER,50,0));

        title = new JLabel();
        loginButton = new JButton();
        registerButton = new JButton();

        JPanel textPanel = new JPanel(new FlowLayout(FlowLayout.CENTER,0,40));
        title.setText("<html><h2 style='text-align:center; font-size:30px'>Welcome to " + cinema.getName() +
         "<br>Cinema management system</h2><br><h3>Location: " + cinema.getLocation() +"</h3><br><h3>Total Hall: " +
         cinema.getTotalHalls()+"</h3></html>");
        title.setBounds(panelCenter.getWidth()/2-title.getWidth()/2, panelCenter.getHeight()/2, 200, 30);


        loginButton.setText("Login");
        loginButton.setFocusable(false);
        loginButton.setBackground(new Color(0x0C0950));
        loginButton.setForeground(new Color(0xFFFFFF));
        loginButton.setPreferredSize(new Dimension(100, 35));
        loginButton.addActionListener(e -> {
            new LoginUI(cinema);
            frame.dispose();
        });

        registerButton.setText("Register");
        registerButton.setFocusable(false);
        registerButton.setBackground(new Color(0x0C0950));
        registerButton.setForeground(new Color(0xFFFFFF));
        registerButton.setPreferredSize(new Dimension(100, 35));
        registerButton.addActionListener(e -> {
            new RegisterUI(cinema);
            frame.dispose();
        });

        textPanel.add(title);

        buttonPanel.add(loginButton);
        buttonPanel.add(registerButton);

        panelCenter.add(textPanel,BorderLayout.PAGE_START);
        panelCenter.add(buttonPanel,BorderLayout.CENTER);

        //Revalidate and repaint panels (refresh UI)
        frame.revalidate();
        frame.repaint();
        //Add panels to Frame
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        Cinema cinema = new Cinema("Cineplex","Phnom Penh",5);
        cinema.loadData();
        new HomeUi(cinema);

        //To test on a new database
        // Cinema cinema = new Cinema("Cineplex","Phnom Penh",5);
        // cinema.saveData();
        // cinema.iniHall();
        // Hall.saveAll(cinema.halls);
        // for (Hall hall: cinema.halls) {
        //     hall.initSeats();
        //     Seat.saveAll(hall.seats);
        //     ShowTime.saveAll(hall.showTimes);
        // }
    }
}

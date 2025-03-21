package gui.helper_gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import cinema.Cinema;
import user.Customer;

public class CustomerMenu extends FrameUI{
    private JLabel menu;
    private JButton exit;
    private JButton seeHalls;
    private JButton checkProfile;

    public CustomerMenu(Cinema cinema, Customer customer) {
        super(cinema);

        menu = new JLabel();
        menu.setText("Select the Option Below");
        menu.setFont(new Font("Arial",Font.BOLD,30));
        menu.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        menu.setForeground(Color.BLACK);

        exit = new JButton();
        exit.setText("Exit");
        exit.setFocusable(false);
        exit.setBackground(new Color(0xFFFFFF));
        exit.setForeground(new Color(0x0C0950));
        exit.addActionListener(e -> {
            frame.dispose();
            System.exit(0);
        });

        seeHalls = new JButton();
        seeHalls.setText("See all Halls");
        seeHalls.setMaximumSize(new Dimension(120,50));
        seeHalls.setAlignmentX(JButton.CENTER_ALIGNMENT);
        seeHalls.setFocusable(false);
        seeHalls.setBackground(Color.BLACK);
        seeHalls.setForeground(Color.white);
        seeHalls.addActionListener(e -> {
            frame.dispose();
            new CustomerHalls(cinema, customer);
        });

        checkProfile = new JButton();
        checkProfile.setAlignmentX(JButton.CENTER_ALIGNMENT);
        checkProfile.setMaximumSize(new Dimension(120,50));
        checkProfile.setText("Check Profile");
        checkProfile.setFocusable(false);
        checkProfile.setBackground(Color.BLACK);
        checkProfile.setForeground(Color.white);
        checkProfile.addActionListener(e -> {
            frame.dispose();
        });

        panelCenter.setLayout(new BoxLayout(panelCenter, BoxLayout.Y_AXIS));
        panelCenter.add(Box.createRigidArea(new Dimension(0,20)));
        panelCenter.add(new JPanel(new FlowLayout()).add(menu));
        panelCenter.add(Box.createRigidArea(new Dimension(0,20)));
        panelCenter.add(new JPanel().add(seeHalls));
        panelCenter.add(Box.createRigidArea(new Dimension(0,20)));
        panelCenter.add(new JPanel().add(checkProfile));

        panelLeft.add(exit);
        //Revalidate and repaint panels (refresh UI)
        frame.revalidate();
        frame.repaint();
        //Add panels to Frame
        frame.setVisible(true);
    }
}

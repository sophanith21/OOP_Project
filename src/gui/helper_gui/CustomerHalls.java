package gui.helper_gui;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import cinema.Cinema;
import cinema.Hall;
import user.Customer;

public class CustomerHalls extends FrameUI {
    private JPanel hallsJPanel;

    public CustomerHalls(Cinema cinema, Customer customer) {
        super(cinema);

        panelCenter.setLayout(new BorderLayout(5,5));
        hallsJPanel = new JPanel();
        hallsJPanel.setLayout(new BoxLayout(hallsJPanel, BoxLayout.Y_AXIS)); 

        ArrayList<Hall> halls = cinema.halls;

        for (Hall hall : halls) {
            JPanel hallPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
            hallPanel.setMaximumSize(new Dimension(800, 50));
            hallPanel.setPreferredSize(new Dimension(500, 50));
            hallPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            hallPanel.setBackground(new Color(0xFFFFFF));

            JLabel titleLabel = new JLabel("Hall " + hall.getHallId());
            JLabel numberOfShowtime = new JLabel(hall.showTimes.size() + " Shows");
            JLabel status = new JLabel(hall.getStatus());

            titleLabel.setPreferredSize(new Dimension(125, 35));
            numberOfShowtime.setPreferredSize(new Dimension(100, 35));
            status.setPreferredSize(new Dimension(100, 35));

            titleLabel.setForeground(new Color(0x0C0950));
            numberOfShowtime.setForeground(new Color(0x0C0950));
            status.setForeground(new Color(0x0C0950));

            JLabel spaceLabel = new JLabel(" ");
            spaceLabel.setPreferredSize(new Dimension(200, 35));

            JButton addButton = new JButton("+");
            addButton.setFocusable(false);
            addButton.setPreferredSize(new Dimension(50, 35));
            addButton.setBackground(new Color(0x0C0950));
            addButton.setForeground(new Color(0xFFFFFF));
            addButton.addActionListener(e -> {
                frame.dispose();
                new CustomerMovie(cinema, customer, hall);
            });

            hallPanel.add(titleLabel);
            hallPanel.add(numberOfShowtime);
            hallPanel.add(status);
            hallPanel.add(spaceLabel);
            hallPanel.add(addButton);

            hallsJPanel.add(hallPanel);  
        }

        JScrollPane scrollPane = new JScrollPane(hallsJPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        JPanel temp = new JPanel();
        temp.setLayout(new BorderLayout(5,5));
        temp.setPreferredSize(new Dimension(400, 400));
        temp.setBackground(new Color(0xFFFFFF));
        temp.add(scrollPane);
        panelCenter.add(temp);

        // Repaint
        frame.revalidate();
        frame.repaint();
        frame.setVisible(true);
    }
}

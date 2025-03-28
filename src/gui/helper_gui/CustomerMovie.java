package gui.helper_gui;

import user.*;

import javax.swing.*;

import cinema.Cinema;
import cinema.Hall;
import cinema.Movie;
import cinema.ShowTime;

import java.awt.*;
import java.util.ArrayList;
import gui.helper_gui.SeatInHallUI;

public class CustomerMovie extends FrameUI {
    protected JButton addMovie;
    protected JButton viewOwnHistory;
    protected JButton Exit;
    protected JPanel cMoviepanel;
    
    CustomerMovie(Cinema cinema,Customer customer,Hall hall) {
        super(cinema);
        // =====LEFT PANEL=====
        addMovie = new JButton();
        addMovie.setText("Add Movie");
        addMovie.setFocusable(false);
        addMovie.setEnabled(false);
        addMovie.setBackground(new Color(0xFFFFFF));
        addMovie.setForeground(new Color(0x0C0950));
        addMovie.addActionListener(e -> {
            frame.dispose();
            new CustomerMovie(cinema,customer,hall);
        });


        Exit = new JButton();
        Exit.setText("Back");
        Exit.setFocusable(false);
        Exit.setBackground(new Color(0xFFFFFF));
        Exit.setForeground(new Color(0x0C0950));
        Exit.addActionListener(e -> {
            frame.dispose();
            new CustomerHalls(cinema,customer);
        });


        //Add panels to frame
        panelLeft.add(Exit);
       

        // Set layout manager to BorderLayout
        panelCenter.setLayout(new BorderLayout(5, 5));

        // =====CENTER PANEL=====
        cMoviepanel = new JPanel();
        cMoviepanel.setLayout(new BorderLayout(5,5));
        cMoviepanel.setPreferredSize(new Dimension(400, 400));
        cMoviepanel.setBackground(new Color(0xFFFFFF));
        
        // Movie List Panel with ScrollPane
        JPanel movieListPanel = new JPanel();
        movieListPanel.setLayout(new BoxLayout(movieListPanel, BoxLayout.Y_AXIS));
        
        ArrayList <ShowTime> showTimes = hall.showTimes;
        
        for (ShowTime showTime : showTimes) {
            JPanel moviePanel = new JPanel();
            moviePanel.setLayout(new FlowLayout(FlowLayout.CENTER));
            moviePanel.setMaximumSize(new Dimension(800, 50));
            moviePanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            moviePanel.setBackground(new Color(0xFFFFFF));
            
            JLabel titleLabel = new JLabel(showTime.movie.getTitle());
            JLabel durationLabel = new JLabel(showTime.movie.getDurationMinutes()+" Min");
            JLabel genreLabel = new JLabel(showTime.movie.getGenre());
            JLabel startEnd = new JLabel(showTime.getStartTime()+ " - " +showTime.getEndTime());

            titleLabel.setPreferredSize(new Dimension(125, 35));
            durationLabel.setPreferredSize(new Dimension(100, 35));
            genreLabel.setPreferredSize(new Dimension(100, 35));
            startEnd.setPreferredSize(new Dimension(125,35));

            titleLabel.setForeground(new Color(0x0C0950));
            durationLabel.setForeground(new Color(0x0C0950));
            genreLabel.setForeground(new Color(0x0C0950));
            startEnd.setForeground(new Color(0x0C0950));

            JLabel spaceLabel = new JLabel(" ");
            spaceLabel.setPreferredSize(new Dimension(200, 35));

            //not yet implemented functionality
            JButton addButton = new JButton("+");
            addButton.setFocusable(false);
            addButton.setPreferredSize(new Dimension(50, 35));
            addButton.setBackground(new Color(0x0C0950));
            addButton.setForeground(new Color(0xFFFFFF));
            addButton.addActionListener(e -> {
                frame.dispose();
                new SeatInHallUI(cinema,customer,hall,showTime);
           });
            
            moviePanel.add(titleLabel);
            moviePanel.add(startEnd);
            moviePanel.add(durationLabel);
            moviePanel.add(genreLabel);
            moviePanel.add(spaceLabel);
            moviePanel.add(addButton);
            
            movieListPanel.add(moviePanel);
        }
        
        JScrollPane scrollPane = new JScrollPane(movieListPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        
        cMoviepanel.add(scrollPane, BorderLayout.CENTER);
        //Refresh frame
        updateCenterPanel(cMoviepanel);
        frame.revalidate();
        frame.repaint();
        frame.setVisible(true);

    }
    public static void main(String[] args) {

    }
}

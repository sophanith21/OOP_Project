package gui.helper_gui;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.ArrayList;

public class AdminMovie extends CustomerMovie {
    JPanel aMoviePanel;
    JPanel adminNav;
    JButton crudMovie;
    JButton viewAllHistory;
    JButton Exit;
    public AdminMovie() {
        aMoviePanel = new JPanel();
        adminNav = new JPanel();
        adminNav.setBackground(new Color(0xFFD600));
        adminNav.setPreferredSize(new Dimension(100, 100));
        // =====LEFT PANEL=====
        crudMovie = new JButton();
        crudMovie.setText("CRUD Movie");
        crudMovie.setFocusable(false);
        crudMovie.setEnabled(false);
        crudMovie.setBackground(new Color(0xFF3D00));
        crudMovie.setForeground(new Color(0xFFF9E6));
        crudMovie.addActionListener(e -> {
            // Update the current panel instead of creating a new instance
            updateCenterPanel(aMoviePanel);
        });

        viewAllHistory = new JButton();
        viewAllHistory.setText("View all History");
        viewAllHistory.setFocusable(false);
        viewAllHistory.setBackground(new Color(0xFF3D00));
        viewAllHistory.setForeground(new Color(0xFFF9E6));
        viewAllHistory.addActionListener(e -> {
            frame.dispose();
            new ViewAllHistory();
        });

        Exit = new JButton();
        Exit.setText("Exit");
        Exit.setFocusable(false);
        Exit.setBackground(new Color(0xFF3D00));
        Exit.setForeground(new Color(0xFFF9E6));
        Exit.addActionListener(e -> {
            frame.dispose();
            new LoginUI();
        });


        // adminNav.setLayout(new BoxLayout(adminNav, BoxLayout.Y_AXIS));
        adminNav.add(crudMovie);
        adminNav.add(viewAllHistory);
        adminNav.add(Exit);
        updateLeftPanel(adminNav);
        //Refresh frame
        frame.revalidate();
        frame.repaint();
        frame.setVisible(true);

        // =====CENTER PANEL=====
        // Set layout manager to BorderLayout
        panelCenter.setLayout(new BorderLayout(5, 5));
        aMoviePanel = new JPanel();
        aMoviePanel.setLayout(new BorderLayout(5,5));
        aMoviePanel.setPreferredSize(new Dimension(400, 400));
        aMoviePanel.setBackground(new Color(0xFFF9E6));
        
        // Movie List Panel with ScrollPane
        JPanel movieListPanel = new JPanel();
        movieListPanel.setLayout(new BoxLayout(movieListPanel, BoxLayout.Y_AXIS));
        
        // Example Movie List
        ArrayList<String[]> movies = new ArrayList<>();
        movies.add(new String[]{"Inception", "148 min", "Sci-Fi"});
        movies.add(new String[]{"Titanic", "195 min", "Romance"});
        movies.add(new String[]{"The Dark Knight", "152 min", "Action"});
        movies.add(new String[]{"Interstellar", "169 min", "Sci-Fi"});
        
        for (String[] movie : movies) {
            JPanel moviePanel = new JPanel();
            moviePanel.setLayout(new FlowLayout(FlowLayout.LEFT));
            // moviePanel.setPreferredSize(new Dimension(350, 50));
            moviePanel.setMaximumSize(new Dimension(600, 50));
            moviePanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            moviePanel.setBackground(new Color(0xFFB350));
            
            JLabel titleLabel = new JLabel(movie[0]);
            JLabel durationLabel = new JLabel(movie[1]);
            JLabel genreLabel = new JLabel(movie[2]);

            titleLabel.setPreferredSize(new Dimension(125, 35));
            durationLabel.setPreferredSize(new Dimension(50, 35));
            genreLabel.setPreferredSize(new Dimension(100, 35));

            titleLabel.setForeground(new Color(0xFFF9E6));
            durationLabel.setForeground(new Color(0xFFF9E6));
            genreLabel.setForeground(new Color(0xFFF9E6));

            JLabel spaceLabel = new JLabel(" ");
            spaceLabel.setPreferredSize(new Dimension(150, 35));

            //not yet implemented functionality
            JButton editButton = new JButton("E");
            editButton.setFocusable(false);
            editButton.setPreferredSize(new Dimension(50, 35));
            editButton.setBackground(new Color(0xFF3D00));
            editButton.setForeground(new Color(0xFFF9E6));

            JButton removeButton = new JButton("x");
            removeButton.setFocusable(false);
            removeButton.setPreferredSize(new Dimension(50, 35));
            removeButton.setBackground(new Color(0xFF3D00));
            removeButton.setForeground(new Color(0xFFF9E6));
            
            moviePanel.add(titleLabel);
            moviePanel.add(durationLabel);
            moviePanel.add(genreLabel);
            moviePanel.add(spaceLabel);
            moviePanel.add(editButton);
            moviePanel.add(removeButton);
            
            movieListPanel.add(moviePanel);
        }
        
        JScrollPane scrollPane = new JScrollPane(movieListPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        
        aMoviePanel.add(scrollPane, BorderLayout.CENTER);
        //Refresh frame
        updateCenterPanel(aMoviePanel);


    }
    public static void main(String[] args) {
        new AdminMovie();
    }

}

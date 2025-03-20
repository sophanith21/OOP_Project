package gui.helper_gui;

import user.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class CustomerMovie extends FrameUI {
    protected JButton addMovie;
    protected JButton viewOwnHistory;
    protected JButton Exit;
    
    CustomerMovie(User user) {
        // =====LEFT PANEL=====
        addMovie = new JButton();
        addMovie.setText("Add Movie");
        addMovie.setFocusable(false);
        addMovie.setEnabled(false);
        addMovie.setBackground(new Color(0xFF3D00));
        addMovie.setForeground(new Color(0xFFF9E6));
        addMovie.addActionListener(e -> {
            frame.dispose();
            new CustomerMovie(user);
        });

        viewOwnHistory = new JButton();
        viewOwnHistory.setText("View Own History");
        viewOwnHistory.setFocusable(false);
        viewOwnHistory.setBackground(new Color(0xFF3D00));
        viewOwnHistory.setForeground(new Color(0xFFF9E6));
        viewOwnHistory.addActionListener(e -> {
            frame.dispose();
            new CustomerHistory();
        });

        Exit = new JButton();
        Exit.setText("Exit");
        Exit.setFocusable(false);
        Exit.setBackground(new Color(0xFF3D00));
        Exit.setForeground(new Color(0xFFF9E6));
        Exit.addActionListener(e -> {
            frame.dispose();
            new LoginUI(user);
        });


        //Add panels to frame
        panelLeft.add(addMovie);
        panelLeft.add(viewOwnHistory);
        panelLeft.add(Exit);
        //Refresh frame
        frame.revalidate();
        frame.repaint();
        frame.setVisible(true);

        // =====CENTER PANEL=====
        panelCenter.setLayout(new BorderLayout(5,5));
        panelCenter.setPreferredSize(new Dimension(400, 400));
        panelCenter.setBackground(new Color(0xFFF9E6));
        
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
            spaceLabel.setPreferredSize(new Dimension(200, 35));

            //not yet implemented functionality
            JButton addButton = new JButton("+");
            addButton.setFocusable(false);
            addButton.setPreferredSize(new Dimension(50, 35));
            addButton.setBackground(new Color(0xFF3D00));
            addButton.setForeground(new Color(0xFFF9E6));
            
            moviePanel.add(titleLabel);
            moviePanel.add(durationLabel);
            moviePanel.add(genreLabel);
            moviePanel.add(spaceLabel);
            moviePanel.add(addButton);
            
            movieListPanel.add(moviePanel);
        }
        
        JScrollPane scrollPane = new JScrollPane(movieListPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        
        panelCenter.add(scrollPane, BorderLayout.CENTER);
        //Refresh frame
        frame.revalidate();
        frame.repaint();
        frame.setVisible(true);

    }
    public static void main(String[] args) {
        //new CustomerMovie();
    }
}

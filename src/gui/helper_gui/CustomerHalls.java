package gui.helper_gui;

import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import cinema.Cinema;
import cinema.Hall;
import user.Customer;

public class CustomerHalls extends FrameUI {
    private JPanel hallsJPanel;
    protected JButton addMovie;
    protected JButton viewOwnHistory;
    protected JButton Exit;

    CustomerHalls(Cinema cinema,Customer customer){
        super(cinema);

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
        
        // Example Movie List
        // ArrayList<String[]> movies = new ArrayList<>();
        // movies.add(new String[]{"Inception", "148 min", "Sci-Fi"});
        // movies.add(new String[]{"Titanic", "195 min", "Romance"});
        // movies.add(new String[]{"The Dark Knight", "152 min", "Action"});
        // movies.add(new String[]{"Interstellar", "169 min", "Sci-Fi"});
        ArrayList <Hall> halls = cinema.halls;
        
        for (Hall hall : halls) {
            JPanel moviePanel = new JPanel();
            moviePanel.setLayout(new FlowLayout(FlowLayout.LEFT));
            // moviePanel.setPreferredSize(new Dimension(350, 50));
            moviePanel.setMaximumSize(new Dimension(600, 50));
            moviePanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            moviePanel.setBackground(new Color(0xFFFFFF));
            
            JLabel titleLabel = new JLabel(hall.getHallId() + "");
            JLabel durationLabel = new JLabel(hall.getStatus());
            JLabel genreLabel = new JLabel(hall.getStatus());

            titleLabel.setPreferredSize(new Dimension(125, 35));
            durationLabel.setPreferredSize(new Dimension(50, 35));
            genreLabel.setPreferredSize(new Dimension(100, 35));

            titleLabel.setForeground(new Color(0x0C0950));
            durationLabel.setForeground(new Color(0x0C0950));
            genreLabel.setForeground(new Color(0x0C0950));

            JLabel spaceLabel = new JLabel(" ");
            spaceLabel.setPreferredSize(new Dimension(200, 35));

            //not yet implemented functionality
            JButton addButton = new JButton("+");
            addButton.setFocusable(false);
            addButton.setPreferredSize(new Dimension(50, 35));
            addButton.setBackground(new Color(0x0C0950));
            addButton.setForeground(new Color(0xFFFFFF));
            
            moviePanel.add(titleLabel);
            moviePanel.add(durationLabel);
            moviePanel.add(genreLabel);
            moviePanel.add(spaceLabel);
            moviePanel.add(addButton);
            
            movieListPanel.add(moviePanel);
        }
        
        JScrollPane scrollPane = new JScrollPane(movieListPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        
        cMoviepanel.add(scrollPane, BorderLayout.CENTER);
        //Refresh frame
        updateCenterPanel(cMoviepanel);
        frame.revalidate();
        frame.repaint();
        frame.setVisible(true);
        
        
    }
    
}

package gui.helper_gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import DBConnection.DBConnection;
import cinema.Cinema;
import cinema.Movie;

public class AddMovie extends JDialog {
    private JTextField titleField;
    private JTextField durationField;
    private JTextField genreField;
    private JButton saveButton;
    private Cinema cinema;
    private AdminMenu adminMenu;

    public AddMovie(Cinema cinema, AdminMenu adminMenu) {
        this.cinema = cinema;
        this.adminMenu = adminMenu;
        initializeUI();
    }

    private void initializeUI() {
        setTitle("Add New Movie");
        setSize(400, 250);
        setLayout(new GridLayout(4, 2, 10, 10));

        // Initialize fields
        titleField = new JTextField();
        durationField = new JTextField();
        genreField = new JTextField();

        // Add components to the dialog
        add(new JLabel("Movie Title:"));
        add(titleField);
        add(new JLabel("Duration (mins):"));
        add(durationField);
        add(new JLabel("Genre:"));
        add(genreField);

        saveButton = new JButton("Save");
        saveButton.setBackground(new Color(0x0C0950));
        saveButton.setForeground(Color.WHITE);
        saveButton.addActionListener(this::saveMovieDetails);
        add(saveButton);

        setVisible(true);
    }

    private void saveMovieDetails(ActionEvent e) {
        String title = titleField.getText();
        String duration = durationField.getText();
        String genre = genreField.getText();

        // Validate the input
        if (title.isEmpty() || duration.isEmpty() || genre.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill in all fields.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            int durationMinutes = Integer.parseInt(duration); // Convert duration to integer
            addMovieToDatabase(title, durationMinutes, genre);
            adminMenu.refreshMovieList(); // Refresh the movie list in AdminMenu
            dispose(); // Close the dialog
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Duration must be a number.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Failed to add the movie.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void addMovieToDatabase(String title, int duration, String genre) throws SQLException {
        String query = "INSERT INTO Movie (movieId, title, durationMinutes, genre) VALUES (?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(query)) {

            String movieId = (Integer.parseInt(Movie.getLastIdFromDB()+1))+""; // Generate a unique movieId
            pstmt.setString(1, movieId);
            pstmt.setString(2, title);
            pstmt.setInt(3, duration);
            pstmt.setString(4, genre);

            pstmt.executeUpdate();
            JOptionPane.showMessageDialog(this, "Movie added successfully!", "Success",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
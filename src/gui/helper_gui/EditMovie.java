package gui.helper_gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import cinema.Cinema;
import cinema.Movie;
import DBConnection.DBConnection;

import java.sql.*;

public class EditMovie extends JDialog {
    private JTextField titleField;
    private JTextField durationField;
    private JTextField genreField;
    private JButton saveButton;
    private Cinema cinema;
    private Movie movie;
    private AdminMenu adminMenu;

    public EditMovie(Cinema cinema, Movie movie, AdminMenu adminMenu) {
        this.cinema = cinema;
        this.movie = movie;
        this.adminMenu = adminMenu;
        initializeUI();
    }

    private void initializeUI() {
        setTitle("EDIT MOVIE");
        setSize(400, 250);
        setLayout(new GridLayout(4, 2, 10, 10));

        // Initialize fields with movie data
        titleField = new JTextField(movie.getTitle());
        durationField = new JTextField(String.valueOf(movie.getDurationMinutes()));
        genreField = new JTextField(movie.getGenre());

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
            updateMovieInDatabase(movie.getMovieID(), title, durationMinutes, genre);
            adminMenu.refreshMovieList(); // Refresh the movie list in AdminMenu
            dispose(); // Close the dialog
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Duration must be a number.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Failed to update the movie.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void updateMovieInDatabase(String movieId, String title, int duration, String genre)
            throws SQLException {
        String query = "UPDATE Movie SET title = ?, durationMinutes = ?, genre = ? WHERE movieId = ?";
        try (Connection conn = DBConnection.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, title);
            pstmt.setInt(2, duration);
            pstmt.setString(3, genre);
            pstmt.setString(4, movieId);

            pstmt.executeUpdate();
            JOptionPane.showMessageDialog(this, "Movie updated successfully!", "Success",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
package gui.helper_gui;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import cinema.Cinema;
import cinema.Movie;
import user.Admin;
import DBConnection.DBConnection;

public class AdminMenu extends FrameUI {
    // panel that will be modified
    private JPanel topPanel;
    private JPanel bottomPanel;
    private JPanel leftPanel;
    private JPanel centerPanel;

    // buttons needed
    private JButton addNewMovieButton;
    private JButton viewAllMoviesButton;
    private JButton viewAllBookingHistoryButton;
    private JButton backButton;
    private JButton exitButton;

    private Cinema cinema;

    public AdminMenu(Cinema cinema, Admin admin) {
        super(cinema); // Call the FrameUI constructor
        this.cinema = cinema;
        initializeUI();
    }

    private void initializeUI() {
        // =====TOP PANEL=====
        topPanel = new JPanel();
        topPanel.setLayout(new FlowLayout(FlowLayout.CENTER)); // Center the text
        topPanel.setBackground(new Color(0xFFD600));

        JLabel topLabel = new JLabel("ADMIN MENU");
        topLabel.setFont(new Font("Arial", Font.BOLD, 24));
        topLabel.setForeground(new Color(0x0C0950));
        topPanel.add(topLabel);

        updateTopPanel(topPanel);

        // =====CENTER PANEL=====
        centerPanel = new JPanel();
        centerPanel.setLayout(new BorderLayout());
        updateCenterPanel(centerPanel); // Update the center panel in FrameUI

        // Create a panel for the initial buttons
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new GridBagLayout()); // Use GridBagLayout for centering
        buttonsPanel.setBackground(new Color(0xFFFFFF));

        // view All Movies Button
        viewAllMoviesButton = new JButton("View All Movies");
        viewAllMoviesButton.setFocusable(false);
        viewAllMoviesButton.setBackground(new Color(0x0C0950));
        viewAllMoviesButton.setForeground(Color.WHITE);
        viewAllMoviesButton.setPreferredSize(new Dimension(150, 30)); // Smaller button
        viewAllMoviesButton.addActionListener(e -> {
            displayAllMovies(); // Display all movies, add movie button, and back button
        });

        viewAllBookingHistoryButton = new JButton("View All Bookings");
        viewAllBookingHistoryButton.setFocusable(false);
        viewAllBookingHistoryButton.setBackground(new Color(0x0C0950));
        viewAllBookingHistoryButton.setForeground(Color.WHITE);
        viewAllBookingHistoryButton.setPreferredSize(new Dimension(150, 30)); // Smaller button
        viewAllBookingHistoryButton.addActionListener(e -> {
            displayBookingHistory(); // Display all bookings and back button
        });

        // Add buttons to the center with spacing
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 10, 10); // Add spacing
        buttonsPanel.add(viewAllMoviesButton, gbc);

        gbc.gridy = 1;
        buttonsPanel.add(viewAllBookingHistoryButton, gbc);

        centerPanel.add(buttonsPanel, BorderLayout.CENTER);

        // =====BACK BUTTON=====
        backButton = new JButton("Back");
        backButton.setFocusable(false);
        backButton.setBackground(new Color(0x0C0950));
        backButton.setForeground(Color.WHITE);
        backButton.setPreferredSize(new Dimension(80, 30)); // Smaller button
        backButton.addActionListener(e -> {
            // Return to the main menu
            centerPanel.removeAll();
            centerPanel.add(buttonsPanel, BorderLayout.CENTER);
            centerPanel.revalidate();
            centerPanel.repaint();

            // Hide the back button
            bottomPanel.remove(backButton);
            bottomPanel.revalidate();
            bottomPanel.repaint();
        });

        // Initially, the back button is not added to the center panel
        updateCenterPanel(centerPanel); // Update the center panel in FrameUI

        // =====LEFT PANEL=====
        leftPanel = new JPanel();
        leftPanel.setLayout(new FlowLayout(FlowLayout.CENTER)); // Center the exit button
        leftPanel.setBackground(new Color(0x261FB3));

        exitButton = new JButton("Exit");
        exitButton.setFocusable(false);
        exitButton.setBackground(new Color(0x0C0950));
        exitButton.setForeground(Color.WHITE);
        exitButton.setPreferredSize(new Dimension(80, 30)); // Smaller button
        exitButton.addActionListener(e -> {
            frame.dispose(); // Close the application
        });

        leftPanel.add(exitButton);
        updateLeftPanel(leftPanel); // Update the left panel in FrameUI

        // =====ADD NEW MOVIE BUTTON=====
        addNewMovieButton = new JButton("Add Movie");
        addNewMovieButton.setFocusable(false);
        addNewMovieButton.setBackground(new Color(0x0C0950));
        addNewMovieButton.setForeground(Color.WHITE);
        addNewMovieButton.setPreferredSize(new Dimension(120, 30)); // Smaller button
        addNewMovieButton.addActionListener(e -> {
            new AddMovie(cinema, this); // Open AddMovieDialog and pass AdminMenu reference
        });

        frame.revalidate();
        frame.repaint();
        frame.setVisible(true);
    }

    private void displayAllMovies() {
        centerPanel.removeAll(); // Clear the center panel
        // System.out.println("Displaying all movies..."); // Debug

        // Create a panel for the "Add Movie" button at the top-right corner
        JPanel topRightPanel = new JPanel();
        topRightPanel.setLayout(new FlowLayout(FlowLayout.RIGHT)); // Align the button to the right
        topRightPanel.add(addNewMovieButton);

        // Add the "Add Movie" button to the top-right corner of the center panel
        centerPanel.add(topRightPanel, BorderLayout.NORTH);

        // Create a panel for the movie list
        JPanel movieListPanel = new JPanel();
        movieListPanel.setLayout(new BoxLayout(movieListPanel, BoxLayout.Y_AXIS));

        List<Movie> movies = loadMoviesFromDatabase();

        // transform each movie into movie panel
        for (Movie movie : movies) {
            // System.out.println("Adding movie to UI: " + movie.getTitle()); // Debug
            JPanel moviePanel = createMoviePanel(movie);
            movieListPanel.add(moviePanel);
        }

        JScrollPane scrollPane = new JScrollPane(movieListPanel);
        centerPanel.add(scrollPane, BorderLayout.CENTER);

        // Add the "Back" button at the bottom-left corner of the center panel
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new FlowLayout(FlowLayout.LEFT)); // Align the back button to the left
        bottomPanel.add(backButton);
        centerPanel.add(bottomPanel, BorderLayout.SOUTH);

        updateCenterPanel(centerPanel); // Update the center panel with new center panel with add movie btn, back btn
                                        // and scrollable list of movie panel
    }

    private List<Movie> loadMoviesFromDatabase() {
        List<Movie> movies = new ArrayList<>();
        String query = "SELECT * FROM Movie";
        try (Connection conn = DBConnection.getConnection();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(query)) {

            System.out.println("Loading movies from the database..."); // Debug
            while (rs.next()) {
                String movieId = rs.getString("movieId");
                String title = rs.getString("title");
                int durationMinutes = rs.getInt("durationMinutes");
                String genre = rs.getString("genre");

                // Debug: Print movie details to the console
                // System.out.println("Loaded Movie: " + title + " (" + genre + ")");

                Movie movie = new Movie(movieId, title, durationMinutes, genre);
                movies.add(movie);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(frame, "Failed to load movies from the database.", "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
        return movies;
    }

    private JPanel createMoviePanel(Movie movie) {
        JPanel moviePanel = new JPanel();
        moviePanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        moviePanel.setMaximumSize(new Dimension(600, 50));
        moviePanel.setBorder(BorderFactory.createLineBorder(Color.BLUE));
        moviePanel.setBackground(new Color(0xFFB350));

        JLabel titleLabel = new JLabel(movie.getTitle());
        JLabel durationLabel = new JLabel(movie.getDurationMinutes
        () + " min");
        JLabel genreLabel = new JLabel(movie.getGenre());

        titleLabel.setPreferredSize(new Dimension(125, 35));
        durationLabel.setPreferredSize(new Dimension(50, 35));
        genreLabel.setPreferredSize(new Dimension(100, 35));

        titleLabel.setForeground(new Color(0x0C0950));
        durationLabel.setForeground(new Color(0x0C0950));
        genreLabel.setForeground(new Color(0x0C0950));

        JLabel spaceLabel = new JLabel(" ");
        spaceLabel.setPreferredSize(new Dimension(150, 35));

        JButton editButton = new JButton("Edit");
        editButton.setFocusable(false);
        editButton.setPreferredSize(new Dimension(60, 30)); // Smaller button
        editButton.setBackground(new Color(0xFFFFFF));
        editButton.setForeground(new Color(0x0C0950));
        editButton.addActionListener(e -> {
            new EditMovie(cinema, movie, this); // Open EditMovieDialog and pass AdminMenu reference
        });

        JButton deleteButton = new JButton("X");
        deleteButton.setFocusable(false);
        deleteButton.setPreferredSize(new Dimension(50, 30)); // Smaller button
        deleteButton.setBackground(Color.RED); // Red background
        deleteButton.setForeground(Color.WHITE); // White text for contrast
        deleteButton.addActionListener(e -> {
            deleteMovie(movie); // Delete the movie
        });

        moviePanel.add(titleLabel);
        moviePanel.add(durationLabel);
        moviePanel.add(genreLabel);
        moviePanel.add(spaceLabel);
        moviePanel.add(editButton);
        moviePanel.add(deleteButton);

        return moviePanel;
    }

    private void deleteMovie(Movie movie) {
        int confirm = JOptionPane.showConfirmDialog(frame, "Are you sure you want to delete this movie?",
                "Confirm Removal", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            String query = "DELETE FROM Movie WHERE movieId = ?";
            try (Connection conn = DBConnection.getConnection();
                    PreparedStatement pstmt = conn.prepareStatement(query)) {

                pstmt.setString(1, movie.getMovieID());
                pstmt.executeUpdate();

                displayAllMovies(); // Refresh the movie list
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(frame, "Failed to delete the movie.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void displayBookingHistory() {
        centerPanel.removeAll(); // Clear the center panel
        // System.out.println("Displaying booking history..."); // Debug

        JPanel bookingListPanel = new JPanel();
        bookingListPanel.setLayout(new BoxLayout(bookingListPanel, BoxLayout.Y_AXIS));

        // Simplified query to fetch booking history
        String query = "SELECT b.bookingId, b.customerId, b.totalPrice, p.paymentDate " +
                "FROM Booking b " +
                "LEFT JOIN Payment p ON b.bookingId = p.bookingId";
        try (Connection conn = DBConnection.getConnection();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(query)) {

            System.out.println("Database connection and query execution successful.");

            boolean hasBookings = false;
            while (rs.next()) {
                hasBookings = true;
                String bookingId = rs.getString("bookingId");
                String customerId = rs.getString("customerId");
                double totalPrice = rs.getDouble("totalPrice");
                String paymentDate = rs.getString("paymentDate");
                // String paymentStatus = rs.getString("status") != null ?
                // rs.getString("status") : "N/A";

                /*
                 * Debug: Print booking details to the console
                 * System.out.println(
                 * "Loaded Booking: " + bookingId + " - " + customerId + " - " + totalPrice +
                 * " - "
                 * + paymentStatus);
                 */

                // Create a panel for the booking
                JPanel bookingPanel = createBookingPanel(bookingId, customerId, totalPrice, paymentDate,
                        "N/A"); // No
                // seats
                // in
                // this
                // query
                bookingListPanel.add(bookingPanel);
            }

            if (!hasBookings) {
                JOptionPane.showMessageDialog(frame, "No booking history found.", "Info",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("SQL Error: " + e.getMessage()); // Print the SQL error message
            JOptionPane.showMessageDialog(frame, "Failed to load booking history.", "Error", JOptionPane.ERROR_MESSAGE);
        }

        JScrollPane scrollPane = new JScrollPane(bookingListPanel);
        centerPanel.add(scrollPane, BorderLayout.CENTER);

        // Add the "Back" button at the bottom-left corner of the center panel
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new FlowLayout(FlowLayout.LEFT)); // Align the back button to the left
        bottomPanel.add(backButton);
        centerPanel.add(bottomPanel, BorderLayout.SOUTH);

        updateCenterPanel(centerPanel); // Update the center panel with the booking history
    }

    private JPanel createBookingPanel(String bookingId, String customerId, double totalPrice, String paymentDate,
            String seats) {
        JPanel bookingPanel = new JPanel();
        bookingPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        bookingPanel.setMaximumSize(new Dimension(600, 50));
        bookingPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        bookingPanel.setBackground(new Color(0xFFB350));

        JLabel bookingIdLabel = new JLabel("Booking ID: " + bookingId);
        JLabel customerIdLabel = new JLabel("Customer ID: " + customerId);
        JLabel totalPriceLabel = new JLabel("Total Price: $" + totalPrice);
        JLabel paymentDateLabel = new JLabel("Payment Date: " + paymentDate);
        // JLabel paymentStatusLabel = new JLabel("Payment Status: " + paymentStatus);
        JLabel seatsLabel = new JLabel("Seats: " + seats);

        bookingIdLabel.setPreferredSize(new Dimension(120, 35));
        customerIdLabel.setPreferredSize(new Dimension(120, 35));
        totalPriceLabel.setPreferredSize(new Dimension(100, 35));
        paymentDateLabel.setPreferredSize(new Dimension(150, 35));
        // paymentStatusLabel.setPreferredSize(new Dimension(120, 35));
        seatsLabel.setPreferredSize(new Dimension(150, 35));

        bookingIdLabel.setForeground(new Color(0x0C0950));
        customerIdLabel.setForeground(new Color(0x0C0950));
        totalPriceLabel.setForeground(new Color(0x0C0950));
        paymentDateLabel.setForeground(new Color(0x0C0950));
        // paymentStatusLabel.setForeground(new Color(0x0C0950));
        seatsLabel.setForeground(new Color(0x0C0950));

        JLabel spaceLabel = new JLabel(" ");
        spaceLabel.setPreferredSize(new Dimension(50, 35));

        // Add components to the booking panel
        bookingPanel.add(bookingIdLabel);
        bookingPanel.add(customerIdLabel);
        bookingPanel.add(totalPriceLabel);
        bookingPanel.add(paymentDateLabel);
        // bookingPanel.add(paymentStatusLabel);
        bookingPanel.add(seatsLabel);

        return bookingPanel;
    }

    public void refreshMovieList() {
        displayAllMovies(); // Refresh the movie list
    }

    public static void main(String[] args) {
        Cinema cinema = new Cinema("Legend", "Phnom Penh", 5);
        Admin admin = new Admin("admin", "admin@example.com", "admin", "123456789", new ArrayList<>(), false);
        new AdminMenu(cinema, admin);
    }
}
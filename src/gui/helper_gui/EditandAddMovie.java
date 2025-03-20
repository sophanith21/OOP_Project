package gui.helper_gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditandAddMovie extends FrameUI {

    private JTextField titleField;
    private JTextField durationField;
    private JTextField genreField;
    private JTextField showTimeField;
    private JButton saveButton;

    public EditandAddMovie() {
        super();
        initializeUI();
    }

    private void initializeUI() {
        // Create a new panel for the center
        JPanel editAddPanel = new JPanel();
        editAddPanel.setBounds(90, 50, 400, 300);
        editAddPanel.setLayout(new GridLayout(5, 2, 20, 20));
        editAddPanel.setBackground(new Color(0xFFF9E6));

        // Add components to the panel
        editAddPanel.add(new JLabel("Movie Title:"));
        titleField = new JTextField();
        editAddPanel.add(titleField);

        editAddPanel.add(new JLabel("Movie Duration (mins):"));
        durationField = new JTextField();
        editAddPanel.add(durationField);

        editAddPanel.add(new JLabel("Movie Genre:"));
        genreField = new JTextField();
        editAddPanel.add(genreField);

        editAddPanel.add(new JLabel("Show Time:"));
        showTimeField = new JTextField();
        editAddPanel.add(showTimeField);

        saveButton = new JButton("Save");
        saveButton.setBackground(new Color(0xFF3D00));
        saveButton.setForeground(new Color(0xFFF9E6));
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveMovieDetails();
            }
        });
        editAddPanel.add(saveButton);

        // Update the center panel with the new content
        updateCenterPanel(editAddPanel);
        frame.setVisible(true); // Ensure the frame is set visible here
    }

    private void saveMovieDetails() {
        String title = titleField.getText();
        String duration = durationField.getText();
        String genre = genreField.getText();
        String showTime = showTimeField.getText();

        // Validate the input
        if (title.isEmpty() || duration.isEmpty() || genre.isEmpty() || showTime.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Please fill in all fields.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Save the movie details (you can replace this with your actual saving logic)
        // For now, we'll just display the details in a message dialog
        String message = String.format("Movie Details:\nTitle: %s\nDuration: %s mins\nGenre: %s\nShow Time: %s",
                title, duration, genre, showTime);
        JOptionPane.showMessageDialog(frame, message, "Movie Saved", JOptionPane.INFORMATION_MESSAGE);

        // Clear the fields after saving
        titleField.setText("");
        durationField.setText("");
        genreField.setText("");
        showTimeField.setText("");
    }

    public static void main(String[] args) {
        new EditandAddMovie();
    }
}
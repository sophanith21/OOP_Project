package gui.helper_gui;

import javax.swing.*;
import cinema.Cinema;
import cinema.Hall;
import cinema.Seat;
import cinema.ShowTime;
import user.Customer;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashSet;

public class SeatInHallUI extends FrameUI {
    private HashSet<JButton> selectedSeatsButton = new HashSet<>();
    private HashSet<Seat> selectedSeats = new HashSet<>();

    public SeatInHallUI(Cinema cinema,Customer customer, Hall hall, ShowTime showTime) {
        super(cinema); // Initialize FrameUI

        frame.setTitle("Seat Selection - Hall " + hall.getHallId());
        frame.remove(panelLeft); // Remove left panel for more space

        // 🔹 Update Top Panel (Hall Name)
        panelTop.removeAll();
        JLabel hallLabel = new JLabel("HALL " + hall.getHallId(), SwingConstants.CENTER);
        hallLabel.setFont(new Font("Arial", Font.BOLD, 30));
        hallLabel.setForeground(Color.WHITE);
        panelTop.add(hallLabel);

        // 🔹 Set panelCenter black background
        panelCenter.removeAll();
        panelCenter.setLayout(new BorderLayout(10,10));
        panelCenter.setBackground(Color.BLACK);

        // 🔹 Seat Grid in Center
        int ROWS = Hall.rowsPerHall;
        int COLS = Hall.seatsPerRow;
        JPanel seatPanel = new JPanel(new GridLayout(ROWS, COLS, 3, 3));
        seatPanel.setBackground(Color.BLACK); // Background of seat area
        ArrayList<ArrayList<Seat>> seatList = hall.seats;

        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
                Seat curSeat = seatList.get(row).get(col);
                JButton seatButton = new JButton(curSeat.getSeatId());

                // 🔹 Remove padding, set font size
                seatButton.setMargin(new Insets(0, 0, 0, 0));
                seatButton.setFont(new Font("Arial", Font.BOLD, 12));
                seatButton.setPreferredSize(new Dimension(30, 30));
                seatButton.setFocusable(false);

                // 🔹 Set color based on whether the seat has services (VIP)
                if (curSeat.getServices() != null && !curSeat.getServices().isEmpty()) {
                    seatButton.setBackground(new Color(218, 165, 32)); // Gold for VIP
                    seatButton.setForeground(Color.BLACK);
                } else if (curSeat.getStatus(showTime.getShowTimeId()).equals("Booked")) {
                    seatButton.setBackground(Color.RED); // Red for booked seats
                    seatButton.setForeground(Color.BLACK);
                    seatButton.setEnabled(false);
                } else {
                    seatButton.setBackground(Color.LIGHT_GRAY); // Normal seat
                    seatButton.setForeground(Color.BLACK);
                }

                // 🔹 Add border for contrast
                seatButton.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1));

                // Click event: Toggle seat selection
                seatButton.addActionListener(e -> toggleSeat(seatButton,curSeat));

                seatPanel.add(seatButton);
            }
        }

        // Wrap seat panel in a scroll pane and add to the center
        JScrollPane scrollPane = new JScrollPane(seatPanel);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
        scrollPane.setBackground(Color.BLACK);
        panelCenter.add(scrollPane, BorderLayout.CENTER);

        // 🔹 Navigation at the Bottom
        panelBottom.removeAll();
        panelBottom.setBackground(Color.BLACK);
        panelBottom.setLayout(new FlowLayout(FlowLayout.CENTER,0,30));
        JButton backButton = new JButton("Back");
        backButton.setFocusable(false);
        JButton confirmButton = new JButton("Confirm");
        confirmButton.setFocusable(false);

        // 🔹 Style buttons
        backButton.setPreferredSize(new Dimension(100, 35));
        backButton.setBackground(new Color(0x0C0950));
        backButton.setForeground(new Color(0xFFFFFF));
        backButton.addActionListener(e -> {
            frame.dispose();
            new CustomerMovie(cinema,customer , hall);
        });
        
        confirmButton.setPreferredSize(new Dimension(100, 35));
        confirmButton.setBackground(new Color(0x0C0950));
        confirmButton.setForeground(new Color(0xFFFFFF));
        confirmButton.addActionListener(e -> {
            frame.dispose();
            new BookingUI(cinema, customer,hall,showTime,selectedSeats);
        });

        panelBottom.add(backButton);
        panelBottom.add(Box.createRigidArea(new Dimension(20,0)));
        panelBottom.add(confirmButton);

        // 🔹 Refresh UI
        frame.revalidate();
        frame.repaint();
    }


    // 🔹 Toggle seat selection (Gray = Unselected, Green = Selected)
    private void toggleSeat(JButton seat,Seat curSeat) {
        if (selectedSeatsButton.contains(seat)) {
            if (curSeat.getServices() != null && !curSeat.getServices().isEmpty()) {
                seat.setBackground(new Color(218, 165, 32)); // Gold for VIP
                seat.setForeground(Color.BLACK);
                selectedSeatsButton.remove(seat);
                selectedSeats.remove(curSeat);
            } else {
                seat.setBackground(Color.LIGHT_GRAY); // Normal seat
                seat.setForeground(Color.BLACK);
                selectedSeatsButton.remove(seat);
                selectedSeats.remove(curSeat);
            }
        } else {
            seat.setBackground(Color.GREEN);
            selectedSeatsButton.add(seat);
            selectedSeats.add(curSeat);
        }
    }

    // 🔹 Confirm Selection
    private void confirmSelection() {
        if (selectedSeats.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "No seats selected!");
        } else {
            JOptionPane.showMessageDialog(frame, "Seats booked: " + selectedSeats.size());
            frame.dispose();
        }
    }
}

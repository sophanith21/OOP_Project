package gui.helper_gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import user.*;
import cinema.Cinema;

public class BookingUI extends CustomerMovie {
    protected JTextField seatAmountField;
    protected JTextField seatIdField;
    protected JComboBox<String> bookingTypeComboBox;
    protected JLabel totalPriceLabel;
    protected JTextField paymentAmountField;
    protected JLabel changeLabel;
    protected JPanel panelBooking;

    public BookingUI(Cinema cinema, Customer customer) {
        super(cinema, customer);
        panelBooking = new JPanel();
        panelBooking.setBounds(90, 50, 400, 300); // Adjust these values as needed
        panelBooking.setBackground(new Color(0xFFFFFF));
        // Set up the center panel for booking details
        panelBooking.setLayout(new GridLayout(7, 2, 20, 20)); // 7 rows, 2 columns, with gaps

        // Add components to the center panel
        panelBooking.add(new JLabel("Number of Seats:"));
        seatAmountField = new JTextField();
        panelBooking.add(seatAmountField);

        panelBooking.add(new JLabel("Seat ID:"));
        seatIdField = new JTextField();
        panelBooking.add(seatIdField);

        panelBooking.add(new JLabel("Booking Type:"));
        String[] bookingTypes = {"Standard", "Premium", "VIP"};
        bookingTypeComboBox = new JComboBox<>(bookingTypes);
        panelBooking.add(bookingTypeComboBox);

        panelBooking.add(new JLabel("Total Price:"));
        totalPriceLabel = new JLabel("$0.00");
        panelBooking.add(totalPriceLabel);

        panelBooking.add(new JLabel("Payment Amount:"));
        paymentAmountField = new JTextField();
        panelBooking.add(paymentAmountField);

        panelBooking.add(new JLabel("Change:"));
        changeLabel = new JLabel("$0.00");
        panelBooking.add(changeLabel);

        // Add a button to calculate the total price and process payment
        JButton calculateButton = new JButton("Calculate Total");
        calculateButton.setBackground(new Color(0x0C0950));
        calculateButton.setForeground(new Color(0xFFFFFF));
        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calculateTotalPrice();
            }
        });
        panelBooking.add(calculateButton);

        JButton payButton = new JButton("Pay");
        payButton.setBackground(new Color(0x0C0950));
        payButton.setForeground(new Color(0xFFFFFF));
        payButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                processPayment();
            }
        });
        panelBooking.add(payButton);
        
        // Set layout manager to null to use absolute positioning
        panelCenter.setLayout(null);
        updateCenterPanel(panelBooking); // Add the booking panel to the center

        // Refresh the frame
        frame.revalidate();
        frame.repaint();
        frame.setVisible(true);
    }

    // Method to calculate the total price
    private void calculateTotalPrice() {
        try {
            int seatAmount = Integer.parseInt(seatAmountField.getText());
            String bookingType = (String) bookingTypeComboBox.getSelectedItem();
            double pricePerSeat = 0.0;

            // Determine price per seat based on booking type
            switch (bookingType) {
                case "Standard":
                    pricePerSeat = 10.00;
                    break;
                case "Premium":
                    pricePerSeat = 15.00;
                    break;
                case "VIP":
                    pricePerSeat = 20.00;
                    break;
            }

            double totalPrice = seatAmount * pricePerSeat;
            totalPriceLabel.setText(String.format("$%.2f", totalPrice));
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(frame, "Please enter a valid number of seats.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Method to process payment
    private void processPayment() {
        try {
            double totalPrice = Double.parseDouble(totalPriceLabel.getText().replace("$", ""));
            double paymentAmount = Double.parseDouble(paymentAmountField.getText());

            if (paymentAmount >= totalPrice) {
                double change = paymentAmount - totalPrice;
                changeLabel.setText(String.format("$%.2f", change));
                            // Open the receipt screen
            // new ReceiptUI(
            //     seatAmountField.getText(), // Number of seats
            //     seatIdField.getText(), // Seat ID
            //     (String) bookingTypeComboBox.getSelectedItem(), // Booking type
            //     totalPriceLabel.getText(), // Total price
            //     paymentAmountField.getText(), // Payment amount
            //     changeLabel.getText() // Change
            // );

            // Optionally, clear the booking fields for a new booking
            seatAmountField.setText("");
            seatIdField.setText("");
            paymentAmountField.setText("");
            totalPriceLabel.setText("$0.00");
            changeLabel.setText("$0.00");
                JOptionPane.showMessageDialog(frame, "Payment successful! Change: $" + String.format("%.2f", change), "Success", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(frame, "Insufficient payment amount.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(frame, "Please enter a valid payment amount.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {

    }
}
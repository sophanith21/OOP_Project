package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BookingUI extends FrameUI {
    protected JTextField seatAmountField;
    protected JTextField seatIdField;
    protected JComboBox<String> bookingTypeComboBox;
    protected JLabel totalPriceLabel;
    protected JTextField paymentAmountField;
    protected JLabel changeLabel;
    protected JPanel panelInner;

    public BookingUI() {
        panelInner = new JPanel();
        panelInner.setBounds(90, 50, 400, 300); // Adjust these values as needed
        panelInner.setBackground(new Color(0xFFF9E6));
        // Set up the center panel for booking details
        panelInner.setLayout(new GridLayout(7, 2, 20, 20)); // 7 rows, 2 columns, with gaps

        // Add components to the center panel
        panelInner.add(new JLabel("Number of Seats:"));
        seatAmountField = new JTextField();
        panelInner.add(seatAmountField);

        panelInner.add(new JLabel("Seat ID:"));
        seatIdField = new JTextField();
        panelInner.add(seatIdField);

        panelInner.add(new JLabel("Booking Type:"));
        String[] bookingTypes = {"Standard", "Premium", "VIP"};
        bookingTypeComboBox = new JComboBox<>(bookingTypes);
        panelInner.add(bookingTypeComboBox);

        panelInner.add(new JLabel("Total Price:"));
        totalPriceLabel = new JLabel("$0.00");
        panelInner.add(totalPriceLabel);

        panelInner.add(new JLabel("Payment Amount:"));
        paymentAmountField = new JTextField();
        panelInner.add(paymentAmountField);

        panelInner.add(new JLabel("Change:"));
        changeLabel = new JLabel("$0.00");
        panelInner.add(changeLabel);

        // Add a button to calculate the total price and process payment
        JButton calculateButton = new JButton("Calculate Total");
        calculateButton.setBackground(new Color(0xFF3D00));
        calculateButton.setForeground(new Color(0xFFF9E6));
        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calculateTotalPrice();
            }
        });
        panelInner.add(calculateButton);

        JButton payButton = new JButton("Pay");
        payButton.setBackground(new Color(0xFF3D00));
        payButton.setForeground(new Color(0xFFF9E6));
        payButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                processPayment();
            }
        });
        panelInner.add(payButton);

        panelCenter.add(panelInner, BorderLayout.CENTER);
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
            new ReceiptUI(
                seatAmountField.getText(), // Number of seats
                seatIdField.getText(), // Seat ID
                (String) bookingTypeComboBox.getSelectedItem(), // Booking type
                totalPriceLabel.getText(), // Total price
                paymentAmountField.getText(), // Payment amount
                changeLabel.getText() // Change
            );

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
            new BookingUI();
    }
}
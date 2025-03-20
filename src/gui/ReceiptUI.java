package gui;

import javax.swing.*;
import java.awt.*;

public class ReceiptUI extends BookingUI {
    protected String seatAmount;
    protected String seatId;
    protected String bookingType;
    protected String totalPrice;
    protected String paymentAmount;
    protected String change;

    public ReceiptUI(String seatAmount, String seatId, String bookingType, String totalPrice, String paymentAmount, String change) {
        this.seatAmount = seatAmount;
        this.seatId = seatId;
        this.bookingType = bookingType;
        this.totalPrice = totalPrice;
        this.paymentAmount = paymentAmount;
        this.change = change;

        // Display the receipt
        displayReceipt();
    }

    private void displayReceipt() {
        // Create a new panel for the receipt
        JPanel receiptPanel = new JPanel();
        receiptPanel.setBounds(90, 50, 400, 300);
        receiptPanel.setLayout(new GridLayout(7, 2, 10, 10)); // 7 rows, 2 columns, with gaps

        // Add receipt details to the panel
        receiptPanel.add(new JLabel("Number of Seats:"));
        receiptPanel.add(new JLabel(seatAmount));

        receiptPanel.add(new JLabel("Seat ID:"));
        receiptPanel.add(new JLabel(seatId));

        receiptPanel.add(new JLabel("Booking Type:"));
        receiptPanel.add(new JLabel(bookingType));

        receiptPanel.add(new JLabel("Total Price:"));
        receiptPanel.add(new JLabel(totalPrice));

        receiptPanel.add(new JLabel("Payment Amount:"));
        receiptPanel.add(new JLabel(paymentAmount));

        receiptPanel.add(new JLabel("Change:"));
        receiptPanel.add(new JLabel(change));

        // Add a button to go back to the booking screen
        JButton backButton = new JButton("Back to Booking");
        backButton.addActionListener(e -> {
            // Clear the receipt and show the booking screen again
            updateCenterPanel(panelInner); // panelInner is the original booking panel
        });
        receiptPanel.add(backButton);

        // Update the center panel with the receipt
        updateCenterPanel(receiptPanel);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
            new ReceiptUI("2", "A1, A2", "Premium", "$30.00", "$40.00", "$10.00");
    }
}
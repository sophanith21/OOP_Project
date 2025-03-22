package gui.helper_gui;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import booking.Booking;
import booking.Payment;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;

import user.*;
import cinema.Cinema;
import cinema.Hall;
import cinema.Seat;
import cinema.ShowTime;

public class BookingUI extends CustomerMovie {
    protected JTextField seatAmountField;
    protected JTextField seatIdField;
    protected JComboBox<String> bookingTypeComboBox;
    protected JLabel totalPriceLabel;
    protected JTextField paymentAmountField;
    protected JLabel changeLabel;
    protected JPanel panelBooking;

    public BookingUI(Cinema cinema, Customer customer,Hall hall,ShowTime showTime,HashSet <Seat> selectedSeats) {
        super(cinema,customer,hall);

        Exit.removeActionListener(Exit.getActionListeners()[0]);;
        Exit.addActionListener(e -> {
            frame.dispose();
            new SeatInHallUI(cinema, customer,hall,showTime);
        });
        panelBooking = new JPanel();
        panelBooking.setBounds(90, 50, 400, 300); // Adjust these values as needed
        panelBooking.setBackground(new Color(0xFFFFFF));
        // Set up the center panel for booking details
        panelBooking.setLayout(new GridLayout(7, 1, 20, 20)); // 7 rows, 2 columns, with gaps

        // Add components to the center panel
        panelBooking.add(new JLabel("Number of Seats: " + selectedSeats.size()));


        String numBookedType = "";
        int normalNum = 0, vipNum =0;
        String temp = "";
        for (Seat seat : selectedSeats){
            temp += seat.getSeatId() + ",";
            if (seat.getServices().isEmpty()) {
                normalNum++;
            } else {
                vipNum++;
            }
        }
        numBookedType = numBookedType + normalNum + " Normal Seat(s), " + vipNum + " VIP seat(s)";

        JLabel seatIds = new JLabel();
        if (!temp.isEmpty()){
            temp.substring(0,temp.length()-2);
        }
        
        seatIds.setText("Seat ID: "+ temp);
        panelBooking.add(seatIds);

        
        panelBooking.add(new JLabel("Booking Type: " + numBookedType));

        double totalPrice = calculateTotalPrice(selectedSeats);
        panelBooking.add(new JLabel("Total Price:" + totalPrice + " $"));

        JPanel paymentpanel = new JPanel(new FlowLayout(FlowLayout.LEFT,0,0));
        paymentpanel.setBorder(null);
        JLabel change = new JLabel();
        paymentpanel.add(new JLabel("Payment Amount:"));
        paymentpanel.add(Box.createRigidArea(new Dimension(30,0)));
        paymentpanel.setOpaque(false);
        paymentAmountField = new JTextField();
        paymentAmountField.setPreferredSize(new Dimension(80,20));
        paymentAmountField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e){
                change.setText("Change: " + (Integer.parseInt(paymentAmountField.getText()) - totalPrice+""));
            }

            @Override
            public void removeUpdate(DocumentEvent e){
                if (change.getText().isEmpty()){
                    change.setText("0");
                } else {
                    change.setText("Change: " + (Integer.parseInt(paymentAmountField.getText()) - totalPrice+""));
                }
                
            }

            @Override
            public void changedUpdate(DocumentEvent e){
                change.setText("Change: " + (Integer.parseInt(paymentAmountField.getText()) - totalPrice+""));
            }
        });
        paymentpanel.add(paymentAmountField);
        panelBooking.add(paymentpanel);

        panelBooking.add(change);

        // Add a button to calculate the total price and process payment
        // JButton calculateButton = new JButton("Calculate Total");
        // calculateButton.setBackground(new Color(0x0C0950));
        // calculateButton.setForeground(new Color(0xFFFFFF));
        // calculateButton.addActionListener(new ActionListener() {
        //     @Override
        //     public void actionPerformed(ActionEvent e) {
        //         calculateTotalPrice(selectedSeats);
        //     }
        // });
        // panelBooking.add(calculateButton);

        JButton payButton = new JButton("Pay");
        payButton.setBackground(new Color(0x0C0950));
        payButton.setForeground(new Color(0xFFFFFF));
        payButton.setMaximumSize(new Dimension(100,30));
        payButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                processPayment(totalPrice,Double.parseDouble(paymentAmountField.getText()),customer,showTime,selectedSeats);
                cinema.loadData();
                frame.dispose();
                new CustomerMenu(cinema, customer);
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
    private double calculateTotalPrice(HashSet<Seat> seats) {
        double total = 0;
        for(Seat seat : seats){
            total += seat.getPrice();
        }

        return total;
    }

    // Method to process payment
    private void processPayment(double totalPrice,double payAmount,Customer customer,ShowTime showTime,HashSet<Seat> seats) {
        // Open the receipt screen
        // new ReceiptUI(
        //     seatAmountField.getText(), // Number of seats
        //     seatIdField.getText(), // Seat ID
        //     (String) bookingTypeComboBox.getSelectedItem(), // Booking type
        //     totalPriceLabel.getText(), // Total price
        //     paymentAmountField.getText(), // Payment amount
        //     changeLabel.getText() // Change
        // );
        if (payAmount < totalPrice) {
            JOptionPane.showMessageDialog(null, "Payment Amount is not enough.", "Payment Issue", JOptionPane.ERROR_MESSAGE);
        } else {
            if (customer.getBalance() < payAmount) {
                JOptionPane.showMessageDialog(null, "Your balance is not enough for the payment amount.", "Payment Issue", JOptionPane.ERROR_MESSAGE);
            } else {
                HashSet <String> set = new HashSet<>();
                for (Seat seat: seats){
                    set.add(seat.getSeatId());
                }
                customer.setWalletBalance(customer.getBalance()-totalPrice);
                customer.saveData();
                LocalDate today = LocalDate.now();
                String paymentId = String.valueOf(Payment.getLastPaymentId()+1);

                Payment payment = new Payment(customer.getId(),paymentId , today+"", totalPrice, "Account-Wallet", "Completed", String.valueOf(Payment.getLastPaymentId()+1));
                ArrayList<Payment> paymentList = new ArrayList<>();
                paymentList.add(payment);
                Payment.saveAll(paymentList);
                
                Booking book = new Booking(String.valueOf(Booking.geLastBookingId()+1), showTime.getShowTimeId(), showTime.movie.getMovieID(), set, totalPrice, paymentId, customer.getId(), "Online");
                ArrayList<Booking> bookList = new ArrayList<>();
                bookList.add(book);
                Booking.saveAll(bookList);
                
               

                JOptionPane.showMessageDialog(null, "Payment Success. Your wallet: " + customer.getBalance() + " $", "Payment Sucesss", JOptionPane.INFORMATION_MESSAGE);

            }
        }
    }

    public static void main(String[] args) {
        // Customer customer = new Customer(0, "admin", "null", "admin", "null", 0, null, null, true);
        // customer.loadData();
        // customer.setWalletBalance(1000000);
        // customer.saveData();
    }
}
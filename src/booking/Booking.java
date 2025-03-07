package src.booking;
import src.user.*;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileReader;
import java.util.Scanner;


public class Booking {
    private String bookingId;
    public String ShowTimeId; //This should be changed to ShowTimeId instead
    private String movieId;
    public HashSet <String> seatId; //More efficient than Array and won't allow duplication
    private int totalSeat;
    private double totalPrice;
    private  Payment payment;
    public Customer customer;
    private String bookingType;

    private static ArrayList<Booking> listOfBookings = new ArrayList<>();
    //Constructor
    public Booking(String bookingId, String ShowTimeId, String movieId, HashSet<String> seatId, int totalSeat,
            double totalPrice, Payment payment, String bookingType) {
        this.bookingId = bookingId;
        this.ShowTimeId = ShowTimeId;
        this.movieId = movieId;
        this.seatId = seatId;
        this.totalSeat = totalSeat;
        this.totalPrice = totalPrice;
        this.payment = payment;
        this.bookingType = bookingType;
    }
    public Booking() {
        this.bookingId = "";
        this.ShowTimeId = "";
        this.movieId = "";
        this.seatId = new HashSet<>();
        this.totalSeat = 0;
        this.totalPrice = 0.0;
        this.payment = new Payment("user123", "pay123", "2025-03-06", 0.0, "method", "status", "transactionID");
        this.bookingType = "";
    }
    
    //Getter
    private String getBookingId() { return bookingId; }
    private String getShowTimeId() { return ShowTimeId; }
    private String getMovieId() { return movieId; }
    private HashSet<String> getSeatId() { return seatId; }
    private int getTotalSeat() { return totalSeat; }
    private double getTotalPrice() { return totalPrice; }
    private Payment getPayment() { return payment; }
    private String getBookingType() { return bookingType; }
    private Customer getCustomer() { return customer; }
    //Setter
    private void setBookingId(String bookingId) { this.bookingId = bookingId; }
    private void setShowTimeId(String ShowTimeId) { this.ShowTimeId = ShowTimeId; }
    private void setMovieId(String movieId) { this.movieId = movieId; }
    private void setSeatId(HashSet<String> seatId) { this.seatId = seatId; }
    private void setTotalSeat(int totalSeat) { this.totalSeat = totalSeat; }
    private void setTotalPrice(double totalPrice) { this.totalPrice = totalPrice; }
    private void setPayment(Payment payment) { this.payment = payment; }
    private void setBookingType(String bookingType) { this.bookingType = bookingType; }
    private void setCustomer(Customer customer) { this.customer = customer; }

    
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Booking other = (Booking) obj;
        if (bookingId == null) {
            if (other.bookingId != null)
                return false;
        } else if (!bookingId.equals(other.bookingId))
            return false;
        if (ShowTimeId == null) {
            if (other.ShowTimeId != null)
                return false;
        } else if (!ShowTimeId.equals(other.ShowTimeId))
            return false;
        if (movieId == null) {
            if (other.movieId != null)
                return false;
        } else if (!movieId.equals(other.movieId))
            return false;
        if (!seatId.equals(other.seatId))
            return false;
        if (customer == null) {
            if (other.customer != null)
                return false;
        } else if (!customer.equals(other.customer))
            return false;
        if (bookingType == null) {
            if (other.bookingType != null)
                return false;
        } else if (!bookingType.equals(other.bookingType))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return    "Booking Id: " + getBookingId() 
                + "\nReservation Time: " + getShowTimeId() 
                + "\nMovie Id: " + getMovieId() 
                + "\nSeat Id: " + getSeatId()
                + "\ntotalSeat: " + getTotalSeat()
                + "\ntotalPrice: $ " + getTotalPrice()
                + "\nPayment : " + getPayment()
                + "\nBooking type: " + getBookingType() + "\n";
    }

    public void writeToFile(String filename){
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(filename))){
            writer.write("Bookingid,revserationtime,movieid,seatid,totalseat,totalprice,payment_amout,payment_type,payment_status,transaction_id,bookingtype\n");
            for (Booking booking : listOfBookings) {
            writer.write(booking.bookingId + "," 
                        + booking.ShowTimeId + "," 
                        + booking.movieId + "," 
                        + booking.seatId + "," 
                        + booking.totalSeat + "," 
                        + booking.totalPrice + "," 
                        + booking.payment.getPaymentAmount() 
                        + ","+ booking.payment.getPaymentMethod() + "," 
                        + booking.payment.getStatus() 
                        + "," + booking.payment.getTransactionID() 
                        + "," + booking.bookingType);
            writer.write("\n");
            }
        } catch (Exception e) {
            System.out.println("Error writing to file");
        }
    }

    public static void readFromFile(String filename){
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            boolean isFirstLine = true;
            while ((line = reader.readLine()) != null) {
                if (isFirstLine) { // Skip header line
                    isFirstLine = false;
                    continue;
                }
                String[] datas = line.split(",");
                if (datas.length != 11) continue; // Ensure correct format
                
                Booking booking = new Booking();
                booking.setBookingId(datas[0]);
                booking.setShowTimeId(datas[1]);
                booking.setMovieId(datas[2]);
                
                HashSet<String> seatSet = new HashSet<>(Arrays.asList(datas[3].replace("[", "").replace("]", "").split(" ")));
                booking.setSeatId(seatSet);
                
                booking.setTotalSeat(Integer.parseInt(datas[4]));
                booking.setTotalPrice(Double.parseDouble(datas[5]));
                
                Payment payment = new Payment("user123", "pay123", "2025-03-06", Double.parseDouble(datas[6]), datas[7], datas[8], datas[9]);
                booking.setPayment(payment);
                
                booking.setBookingType(datas[10]);
                
                listOfBookings.add(booking);
            }
        } catch (IOException e) {
            System.err.println("Error reading from file: " + e.getMessage());
        }
    }

    public static void makeBooking() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Booking ID: ");
        String bookingId = scanner.nextLine();

        System.out.print("Enter Reservation Time: ");
        String ShowTimeId = scanner.nextLine();

        System.out.print("Enter Movie ID: ");
        String movieId = scanner.nextLine();

        System.out.print("Enter number of seats: ");
        int totalSeat = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        HashSet<String> seatId = new HashSet<>();
        for (int i = 0; i < totalSeat; i++) {
            System.out.print("Enter Seat ID: ");
            seatId.add(scanner.nextLine());
        }

        System.out.print("Enter Total Price: ");
        double totalPrice = scanner.nextDouble();
        scanner.nextLine(); // Consume newline

        System.out.print("Enter Payment Method: ");
        String paymentMethod = scanner.nextLine();

        System.out.print("Enter Payment Status: ");
        String status = scanner.nextLine();

        System.out.print("Enter Transaction ID: ");
        String transactionID = scanner.nextLine();

        Payment payment = new Payment("user123", "pay123", "2025-03-06", totalPrice, paymentMethod, status, transactionID);

        System.out.print("Enter Booking Type: ");
        String bookingType = scanner.nextLine();

        Booking newBooking = new Booking(bookingId, ShowTimeId, movieId, seatId, totalSeat, totalPrice, payment, bookingType);
        newBooking.writeToFile("src/booking/Booking.txt");
        listOfBookings.add(newBooking); // Add booking to the list
    }
    private static void displayBookings() {
        for (Booking booking : listOfBookings) {
            if (booking != null) {
                System.out.println(booking.toString());
            } else {
                System.out.println("null");
            }
        }
    }
}

package booking;
import DataControl.DataPersistence;
import cinema.Seat;
import user.*;
import DBConnection.DBConnection;
import java.sql.Connection;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.io.FileReader;


public class Booking implements DataPersistence{
    private String bookingId;
    public String ShowTimeId; //This should be changed to ShowTimeId instead
    private String movieId;
    public HashSet <String> seatIds; //More efficient than Array and won't allow duplication
    private double totalPrice;
    private String paymentId;
    private int customerId;
    private String bookingType;

    private static ArrayList<Booking> listOfBookings = new ArrayList<>();
    //Constructor
    public Booking(String bookingId, String ShowTimeId, String movieId, HashSet<String> seatId, 
            double totalPrice, String paymentId, int customerId, String bookingType) {
        this.bookingId = bookingId;
        this.ShowTimeId = ShowTimeId;
        this.movieId = movieId;
        this.seatIds = seatId;
        this.totalPrice = totalPrice;
        this.paymentId = paymentId;
        this.customerId = customerId;
        this.bookingType = bookingType;
    }
    public Booking() {
        this.bookingId = "";
        this.ShowTimeId = "";
        this.movieId = "";
        this.seatIds = new HashSet<>();
        this.totalPrice = 0.0;
        this.paymentId = "";
        this.bookingType = "";
    }

    public static void saveAll(ArrayList <Booking> bookings){
        try {
            Connection conn = DBConnection.getConnection();
    
            if (conn != null) {
                System.out.println("Database connection successful!");
                String query = "INSERT INTO booking " +
                "VALUES (?, ?, ?, ?, ?, ?) ";
                PreparedStatement pstmt = conn.prepareStatement(query);

                for (Booking book : bookings) {
                    pstmt.setString(1, book.getBookingId());
                    pstmt.setString(6, book.getBookingType());
                    pstmt.setInt(3, book.getCustomerId());
                    pstmt.setDouble(4, book.getTotalPrice());
                    pstmt.setString(5, book.getPaymentId());
                    pstmt.setString(2, book.getShowTimeId());
                    pstmt.executeUpdate();

                    HashSet <String> seatIds = book.getSeatId();
                    String seatIdsBookedQuery = "INSERT INTO Seatbooking (bookingId,seatId) " +
                    "VALUES (?,?) " + 
                    "ON DUPLICATE KEY UPDATE " +
                    "seatId = VALUES(seatId)";
                    PreparedStatement seatIdBookedStmt = conn.prepareStatement(seatIdsBookedQuery);
                    
                    for (String seatId : seatIds) {
                        seatIdBookedStmt.setString(1, book.getBookingId());
                        seatIdBookedStmt.setString(2, seatId);
                        seatIdBookedStmt.executeUpdate();
                    }
                    
                }

                pstmt.close();
                conn.close();
            } else {
                System.out.println("Database connection failed!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public static ArrayList<Booking> loadAll() {
        throw new UnsupportedOperationException("Use loadAll of Hall instead");
    }

    @Override
    public void saveData(){
        throw new UnsupportedOperationException("Use saveAll instead");
    }

    @Override
    public void loadData(){
        throw new UnsupportedOperationException("Use loadAll instead");
    }
      

    //Getter
    public String getBookingId() { return bookingId; }
    public String getShowTimeId() { return ShowTimeId; }
    public String getMovieId() { return movieId; }
    public HashSet<String> getSeatId() { return seatIds; }
    public double getTotalPrice() { return totalPrice; }
    public String getPaymentId() { return paymentId; }
    public String getBookingType() { return bookingType; }
    public int getCustomerId() { return customerId; }
    //Setter
    private void setBookingId(String bookingId) { this.bookingId = bookingId; }
    private void setShowTimeId(String ShowTimeId) { this.ShowTimeId = ShowTimeId; }
    private void setMovieId(String movieId) { this.movieId = movieId; }
    private void setSeatIds(HashSet<String> seatId) { this.seatIds = seatId; }
    private void setTotalPrice(double totalPrice) { this.totalPrice = totalPrice; }
    private void setPayment(String paymentId) { this.paymentId = paymentId; }
    private void setBookingType(String bookingType) { this.bookingType = bookingType; }
    private void setCustomer(int customerId) { this.customerId = customerId; }

    
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
        return true;
    }

    @Override
    public String toString() {
        return "Booking [bookingId=" + bookingId + ", ShowTimeId=" + ShowTimeId + ", movieId=" + movieId + ", seatIds="
                + seatIds + ", totalPrice=" + totalPrice + ", paymentId=" + paymentId + ", customerId=" + customerId
                + ", bookingType=" + bookingType + "]";
    }
   
    // public static void makeBooking() {
    //     Scanner scanner = new Scanner(System.in);
    //     System.out.print("Enter Booking ID: ");
    //     String bookingId = scanner.nextLine();

    //     System.out.print("Enter Reservation Time: ");
    //     String ShowTimeId = scanner.nextLine();

    //     System.out.print("Enter Movie ID: ");
    //     String movieId = scanner.nextLine();

    //     System.out.print("Enter number of seats: ");
    //     int totalSeat = scanner.nextInt();
    //     scanner.nextLine(); // Consume newline

    //     HashSet<String> seatId = new HashSet<>();
    //     for (int i = 0; i < totalSeat; i++) {
    //         System.out.print("Enter Seat ID: ");
    //         seatId.add(scanner.nextLine());
    //     }

    //     System.out.print("Enter Total Price: ");
    //     double totalPrice = scanner.nextDouble();
    //     scanner.nextLine(); // Consume newline

    //     System.out.print("Enter Payment Method: ");
    //     String paymentMethod = scanner.nextLine();

    //     System.out.print("Enter Payment Status: ");
    //     String status = scanner.nextLine();

    //     System.out.print("Enter Transaction ID: ");
    //     String transactionID = scanner.nextLine();

    //     Payment payment = new Payment("user123", "pay123", "2025-03-06", totalPrice, paymentMethod, status, transactionID);

    //     System.out.print("Enter Booking Type: ");
    //     String bookingType = scanner.nextLine();
    //     // Please fix this
    //     //Booking newBooking = new Booking(bookingId, ShowTimeId, movieId, seatId, totalSeat, totalPrice, payment, bookingType);
    //     //listOfBookings.add(newBooking); // Add booking to the list
    // }

    public static int geLastBookingId (){
        try {
            Connection conn = DBConnection.getConnection();
            if (conn != null) {
                String query = "SELECT COUNT(*) as numOfEntries FROM Booking";
                ResultSet set = DBConnection.executeQuery(query);
                int temp;
                set.next();
                temp = set.getInt("numOfEntries");
                conn.close();
                return temp;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
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

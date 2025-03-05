package src.booking;
import src.user.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;


public class Booking {
    private String bookingId;
    public String reserveTime; //This should be changed to ShowTimeId instead
    private String movieId;
    public HashSet <String> seatId; //More efficient than Array and won't allow duplication
    private int totalSeat;
    private double totalPrice;
    private  Payment payment;
    private Customer customer;
    private String bookingType;

    private static ArrayList<Booking> listOfBookings = new ArrayList<>();

    public Booking(String bookingId, String reserveTime, String movieId, String[] seatId, int totalSeat,
            double totalPrice, Payment payment, String bookingType) {
        this.bookingId = bookingId;
        this.reserveTime = reserveTime;
        this.movieId = movieId;
        this.seatId = seatId;
        this.totalSeat = totalSeat;
        this.totalPrice = totalPrice;
        this.payment = payment;
        this.bookingType = bookingType;
    }
    private String getBookingId() { return bookingId; }
    private String getReserveTime() { return reserveTime; }
    private String getMovieId() { return movieId; }
    private String[] getSeatId() { return seatId; }
    private int getTotalSeat() { return totalSeat; }
    private double getTotalPrice() { return totalPrice; }
    private Payment getPayment() { return payment; }
    public String getBookingType() { return bookingType; }

    
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((bookingId == null) ? 0 : bookingId.hashCode());
        result = prime * result + ((reserveTime == null) ? 0 : reserveTime.hashCode());
        result = prime * result + ((movieId == null) ? 0 : movieId.hashCode());
        result = prime * result + Arrays.hashCode(seatId);
        result = prime * result + totalSeat;
        long temp;
        temp = Double.doubleToLongBits(totalPrice);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        result = prime * result + ((payment == null) ? 0 : payment.hashCode());
        result = prime * result + ((customer == null) ? 0 : customer.hashCode());
        result = prime * result + ((bookingType == null) ? 0 : bookingType.hashCode());
        return result;
    }
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
        if (reserveTime == null) {
            if (other.reserveTime != null)
                return false;
        } else if (!reserveTime.equals(other.reserveTime))
            return false;
        if (movieId == null) {
            if (other.movieId != null)
                return false;
        } else if (!movieId.equals(other.movieId))
            return false;
        if (!Arrays.equals(seatId, other.seatId))
            return false;
        if (totalSeat != other.totalSeat)
            return false;
        if (Double.doubleToLongBits(totalPrice) != Double.doubleToLongBits(other.totalPrice))
            return false;
        if (payment == null) {
            if (other.payment != null)
                return false;
        } else if (!payment.equals(other.payment))
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
                + "\nReservation Time: " + getReserveTime() 
                + "\nMovie Id: " + getMovieId() 
                + "\nSeat Id: " + Arrays.toString(getSeatId()) 
                + "\ntotalSeat: " + getTotalSeat()
                + "\ntotalPrice: $ " + getTotalPrice()
                + "\nPayment : " + getPayment()
                + "\nBooking type: " + getBookingType() + "\n";
    }

    // public void displayinfo(String name){
    //     if (name.equals(customer.getName())) {
    //         System.out.println(toString());
    //     } else{
    //         System.out.println("No record of this customer info!");
    //     }
    // }
}

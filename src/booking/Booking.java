package src.booking;
import src.cinema.*;
import src.user.*;
import java.util.ArrayList;
import java.util.Arrays;


public class Booking {//need to be array
    private String bookingId;
    private String reserveTime;// need to reserve the time 
    private String movieId;
    private String [] seatId; //4a 4b need to be array
    private int totalSeat; //4
    private double totalPrice;
    private String paymentId;
    //booking and payment need array
    private static ArrayList<Booking> listOfBookings = new ArrayList<>();

    public Booking(String bookingId, String reserveTime, String movieId, String[] seatId, int totalSeat,
            double totalPrice, String paymentId) {
        this.bookingId = bookingId;
        this.reserveTime = reserveTime;
        this.movieId = movieId;
        this.seatId = seatId;
        this.totalSeat = totalSeat;
        this.totalPrice = totalPrice;
        this.paymentId = paymentId;
    }

    private String getBookingId() { return bookingId; }
    private String getReserveTime() { return reserveTime; }
    private String getMovieId() { return movieId; }
    private String[] getSeatId() { return seatId; }
    private int getTotalSeat() { return totalSeat; }
    private double getTotalPrice() { return totalPrice; }
    private String getPaymentId() { return paymentId; }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((bookingId == null) ? 0 : bookingId.hashCode());
        result = prime * result + ((movieId == null) ? 0 : movieId.hashCode());
        result = prime * result + Arrays.hashCode(seatId);
        result = prime * result + ((paymentId == null) ? 0 : paymentId.hashCode());
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
        if (movieId == null) {
            if (other.movieId != null)
                return false;
        } else if (!movieId.equals(other.movieId))
            return false;
        if (!Arrays.equals(seatId, other.seatId))
            return false;
        if (paymentId == null) {
            if (other.paymentId != null)
                return false;
        } else if (!paymentId.equals(other.paymentId))
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
                + "\nPayment Id: " + getPaymentId() + "\n";
    }

    public void displayinfo(String name, String phoneNum){
        if (name.equals("12345") && phoneNum.equals("12345")) {
            System.out.println(toString());
        } else{
            System.out.println("Invalid customer info!");
        }
    }
    
}

package src.booking;
import src.cinema.*;
import src.user.*;

public class Booking {
    private int bookingId;
    private int totalSeat;
    private double totalPrice;
    private String bookingTime;    
    private String movieId;
    private String seatId;
    private String paymentId;

    public Booking(int bookingId,int totalSeat, double totalPrice, String bookingTime, String movieId, String seatId, Seat [] seat,String paymentId) {
        this.bookingId = bookingId;
        this.totalSeat = totalSeat;
        this.totalPrice = totalPrice;
        this.bookingTime = bookingTime;
        this.seatId = seatId;
        this.movieId =  movieId;
        this.paymentId =  paymentId;
    }
}

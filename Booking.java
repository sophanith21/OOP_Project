public class Booking {
    private int bookingId;
    private int totalSeat;
    private double totalPrice;
    private String bookingTime;    
    private User user;
    private Hall hall;
    private Movie movie;
    private Seat [] seat;
    private Payment payment;

    public Booking(int bookingId,int totalSeat, double totalPrice, String bookingTime, Hall hall, Movie movie, User user, Seat [] seat,Payment payment) {
        this.bookingId = bookingId;
        this.totalSeat = totalSeat;
        this.totalPrice = totalPrice;
        this.bookingTime = bookingTime;
        this.user =  user;
        this.hall =  hall;
        this.movie = movie;
        this.seat =  new Seat[totalSeat];
        this.payment =  payment;
    }
}

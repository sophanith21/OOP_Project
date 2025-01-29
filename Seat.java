public class Seat {
    private int rowNumber;
    private int seatNumber;
    private String status;  
    private String userId;
    private String showtimeId;
    private double price;

    public Seat(int rowNumber, int seatNumber, String status, String userId, String showtimeId, double price) {
        this.rowNumber = rowNumber;
        this.seatNumber = seatNumber;
        this.status = status;
        this.userId = userId;
        this.showtimeId = showtimeId;
        this.price = price;
    }
}

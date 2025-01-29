public class Seat {
    private int rowNumber;
    private int seatNumber;
    private String status; // Can be "Booked", "Available" , or "Occupied" when the movie starts and the seat is taken
    private String userId; //If the seat is booked, this field will contain the user id of the user who booked the seat
    private String hallId; //The hall in which the seat is present
    private double price;
    private String showtimeId;
    public static int numTakenSeats = 0;

    public Seat(int rowNumber, int seatNumber, String status, String userId, String showtimeId, double price) {
        this.rowNumber = rowNumber;
        this.seatNumber = seatNumber;
        this.status = status;
        this.userId = userId;
        this.showtimeId = showtimeId;
        this.price = price;
        if(this.status.equals("Booked")){
            numTakenSeats ++;
        }
    }
}

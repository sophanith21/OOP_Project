public class Seat {
    private int rowNumber;
    private String status; // Can be "Booked", "Available" , or "Occupied" when the movie starts and the seat is taken
    private String userId; //If the seat is booked, this field will contain the user id of the user who booked the seat
    private String hallId; //The hall in which the seat is present
    private double price ;
    private String showtimeId;
    private String seatId;
    private int seatPerRow = 20;
    private int rowPerHall = 10;
    private int seatCount = 0;
    private int rowCount = 1;
    public static int numTakenSeats = 0;

    public Seat(){
        if(seatCount == seatPerRow){
            seatCount = 0;
            rowCount++;
        }else{
            seatCount++;
        }
        this.rowNumber = rowCount ;
        if(rowCount == rowPerHall){
            System.out.println("The hall is full");
        }
        else{
            this.seatId = rowCount + "-" + seatCount;
            this.status = "Available";
            this.userId = "";
            this.showtimeId = "";
            if(rowCount < 5){
                this.price = 15.0;
            }else if(rowCount < 10){
                this.price = 10.0;
            }
        }
        
    }
}

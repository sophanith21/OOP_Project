public class Hall {
    private String hallId;
    private Seat[] seats;
    private int numBookedSeats;
    private Movie [] moviesPlaying ; 
    private String currentPlaying; //Can be null if there is no movie currenly played
    private String status; // Open, Closed, Full, Maintenances
    public static int numberOfHalls = 0;

    public Hall(String hallId, Seat[] seats, int numBookedSeats, Movie [] moviesPlaying , String currentPlaying, String status){
        this.hallId = hallId;
        this.seats = seats;
        this.numBookedSeats = numBookedSeats;
        this.moviesPlaying = moviesPlaying;
        this.currentPlaying = currentPlaying;
        this.status = status;
        numberOfHalls++;
    }
}

public class Hall {
    private String hallId;
    private int maxSeats;
    private int numBookedSeats;
    private String [] moviesPlaying ; 
    private String currentPlaying; //Can be null if there is no movie currenly played
    private String status; // Open, Closed, Full, Maintenances

    public Hall(String hallId, int maxSeats, int numBookedSeats, String [] moviesPlaying , String currentPlaying, String status){
        this.hallId = hallId;
        this.maxSeats = maxSeats;
        this.numBookedSeats = numBookedSeats;
        this.moviesPlaying = moviesPlaying;
        this.currentPlaying = currentPlaying;
        this.status = status;
    }
}

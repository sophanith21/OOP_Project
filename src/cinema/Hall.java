package src.cinema;
import java.util.ArrayList;

public class Hall {
    private int hallId;
    public static int maxSeats = 200;
    public ArrayList<ShowTime> showTimes;    //Stores info about the movie according to the specific showtimes
    public ArrayList<ArrayList<Seat>> seats; //each seat has the id of all users accross all the existed showtimes
    private String status;                    // Open, Closed, Full, Maintenances
    private static int numberOfHalls = 0;
    
    public static int rowsPerHall = 10;
    public static int seatsPerRow = 20;
    
    public Hall(){
        this.hallId = ++numberOfHalls;
        showTimes = new ArrayList<>();
        seats = new ArrayList<>();
        status = "Open";
        for(int i = 0; i < rowsPerHall; i++){

            seats.add(new ArrayList<>());

            for(int j = 0; j < seatsPerRow; j++){
                if((i >= 3 && i <= 6) && (j >= 8 && j <= 11)){   
                    seats.get(i).add(new VIPSeat(hallId, i+1, j+1, ""));
                } else {
                    seats.get(i).add(new Seat(hallId,i+1,j+1));
                }
            }
        }
    }

    public void setStatus(String status){
        if (!status.equals("Open") && !status.equals("Closed") && !status.equals("Full") && !status.equals("Maintenance")) {
            throw new IllegalArgumentException("Invalid status: " + status); //Shoulb be handle in main
        }
        this.status = status;
    }
    public String getStatus(){
        return status;
    }

    public int getHallId() {
        return hallId;
    }

    public void addShowTime() {
        ShowTime newShowTime = new ShowTime(hallId);
        showTimes.add(newShowTime);

        newShowTime.setShowTime();
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Hall other = (Hall) obj;
        if (hallId != other.hallId)
            return false;
        return true;
    }

    @Override
    public String toString() {                          
        return "Hall [hallId=" + hallId + ", showTimes=" + (showTimes.isEmpty() ? "No ShowTimes" : showTimes) + ", status=" + status + "]";
    }
    
}

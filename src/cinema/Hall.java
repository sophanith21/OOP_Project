package src.cinema;
import java.util.ArrayList;

public class Hall {
    private int hallId;
    private int maxSeats;
    private ArrayList<ArrayList<Seat>> seats = new ArrayList<>();
    private ShowTime showTime;
    private String status = "Closed"; // Open, Closed, Full, Maintenances
    private static int numberOfHalls = 0;
    private static int rowsPerHall = 10;
    private static int seatsPerRow = 20;
    
    public Hall(){
        this.hallId = ++numberOfHalls;
        for(int i = 0; i < rowsPerHall; i++){
            seats.add(new ArrayList<>());
            for(int j = 0; j < seatsPerRow; j++){
                seats.get(i).add(new Seat(hallId,i,j));
            }
        }
    }

    public int getMaxSeat(){
        return maxSeats;
    }

    public void setStatus(String status){
        this.status = status;
    }
    public String getStatus(){
        return status;
    }

    public int getHallId() {
        return hallId;
    }

    public int getMaxSeats() {
        return maxSeats;
    }

    public ArrayList<ArrayList<Seat>> getSeats() {
        return seats;
    }

    public ShowTime getShowTime() {
        return showTime;
    }

    public void setShowTime(ShowTime showTime) {
        this.showTime = showTime;
    }

    

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + hallId;
        result = prime * result + maxSeats;
        result = prime * result + ((showTime == null) ? 0 : showTime.hashCode());
        result = prime * result + ((status == null) ? 0 : status.hashCode());
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
        Hall other = (Hall) obj;
        if (hallId != other.hallId)
            return false;
        if (maxSeats != other.maxSeats)
            return false;
        if (showTime == null) {
            if (other.showTime != null)
                return false;
        } else if (!showTime.equals(other.showTime))
            return false;
        if (status == null) {
            if (other.status != null)
                return false;
        } else if (!status.equals(other.status))
            return false;
        return true;
    }

    @Override
    public String toString() {

        return "Hall [hallId=" + hallId +
               ", maxSeats=" + maxSeats +
               ", showTime=" + showTime +
               ", status=" + status + "]";
    }

    
    
}

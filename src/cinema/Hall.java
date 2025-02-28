package src.cinema;
import java.util.ArrayList;

public class Hall {
    private int hallId;
    public static int maxSeats = 200;
    private ArrayList<ShowTime> showTimes;
    private String status = "Closed"; // Open, Closed, Full, Maintenances
    private static int numberOfHalls = 0;
    
    public Hall(){
        this.hallId = ++numberOfHalls;
        showTimes = new ArrayList<>();
        
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

    public void addShowTime() {
        showTimes.add(new ShowTime(hallId));
        showTimes.get(showTimes.size()-1).setShowTime();
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
        return "Hall [hallId=" + hallId + ", maxSeats=" + maxSeats + ", showTimes=" + showTimes + ", status=" + status
                + "]";
    }

    
}

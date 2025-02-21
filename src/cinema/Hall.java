package src.cinema;
import java.util.ArrayList;

public class Hall {
    private int hallId;
    private int maxSeats;
    private ArrayList<ShowTime> showTimes;
    private String status = "Closed"; // Open, Closed, Full, Maintenances
    private static int numberOfHalls = 0;
    
    public Hall(){
        this.hallId = ++numberOfHalls;
        
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


    public void addShowTime() {
        showTimes.add(new ShowTime(hallId));
        showTimes.get(showTimes.size()-1).setShowTime();
    }

    public void showDetails() {
        System.out.println();
        System.out.println("ID: " + hallId);
        System.out.println("Status: " + status);
        for (ShowTime showTime : showTimes) {
            System.out.println(showTime);
        }
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + hallId;
        result = prime * result + maxSeats;
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
        if (status == null) {
            if (other.status != null)
                return false;
        } else if (!status.equals(other.status))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Hall [hallId=" + hallId + ", maxSeats=" + maxSeats + ", status=" + status + "]";
    }
}

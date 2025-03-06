package src.cinema;

import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;

public class ShowTime {
    private String showTimeId;
    private String startTime;
    private String endTime;
    private ArrayList<ArrayList<Seat>> seats = new ArrayList<>();
    private Movie movie;
    

    public ShowTime( int hallId) {
        this.showTimeId = null;
        this.startTime = null;
        this.endTime = null;

    }

    private void setShowTimeId(String showTimeId) { this.showTimeId = showTimeId; }
    private void setStartTime(String startTime) { this.startTime = startTime; }
    private void setEndTime(String endTime) { this.endTime = endTime; }


    
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        ShowTime other = (ShowTime) obj;
        if (showTimeId == null) {
            if (other.showTimeId != null)
                return false;
        } else if (!showTimeId.equals(other.showTimeId))
            return false;
        if (startTime == null) {
            if (other.startTime != null)
                return false;
        } else if (!startTime.equals(other.startTime))
            return false;
        if (endTime == null) {
            if (other.endTime != null)
                return false;
        } else if (!endTime.equals(other.endTime))
            return false;
        if (movie == null) {
            if (other.movie != null)
                return false;
        } else if (!movie.equals(other.movie))
            return false;
        return true;
    }
    public void setShowTime(){
        Scanner scan = new Scanner(System.in);
        while(true){
            System.out.print("Do you want to add show time?(yes/no): ");
            String choose = scan.nextLine();
            if(choose.equals("no")){break;}
            System.out.print("\nSet show time id: ");
            String show = scan.nextLine();            
            System.out.print("\nSet start time: ");
            String start = scan.nextLine();
            System.out.print("\nSet end time: ");
            String end = scan.nextLine();

            LocalTime startTIme = LocalTime.parse(start);
            LocalTime endTime = LocalTime.parse(end);
            Duration duration = Duration.between(startTIme, endTime);
            int durationMinute = (int)duration.toMinutes();
            setStartTime(start);
            setEndTime(end);
            setShowTimeId(show);
            
            System.out.println("Add movie title: ");
            String title = scan.nextLine();
            System.out.println("Add genre: ");
            String genre = scan.nextLine();

            this.movie = new Movie(title, durationMinute, genre);
            System.out.println("Movie has been added: " + movie.getTitle());
            System.out.println("Show time has been set successfully!");
        }
        scan.close();
    }

    @Override
    public String toString() {
        return "ShowTime [showTimeId=" + showTimeId + ", startTime=" + startTime + ", endTime=" + endTime + ", movie="
                + movie + "]";
    }

   
    

}

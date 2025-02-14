package src.cinema;

import java.util.Scanner;

public class ShowTime {
    private String showTimeId;
    private String startTime;
    private String endTime;
    private String movieId;
    private String hallId;

    public ShowTime(String showTimeId, String startTime, String endTime, String movieId, String hallId) {
        this.showTimeId = showTimeId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.movieId = movieId;
        this.hallId = hallId;
    }

    private void setShowTimeId(String showTimeId) { this.showTimeId = showTimeId; }
    private void setStartTime(String startTime) { this.startTime = startTime; }
    private void setEndTime(String endTime) { this.endTime = endTime; }
    private void setMovieId(String movieId) { this.movieId = movieId; }
    private void setHallId(String hallId) { this.hallId = hallId; }

    public void setShowTime(int hallId){
        Scanner scan = new Scanner(System.in);
        System.out.print("Set movie id: ");
        String movie = scan.nextLine();
        // System.out.print("Set hall id: ");
        // String hall = scan.nextLine();
        System.out.print("Set start time: ");
        String start = scan.nextLine();
        System.out.print("Set end time: ");
        String end = scan.nextLine();
        System.out.print("Set show time id: ");
        String show = scan.nextLine();

        setStartTime(start);
        setEndTime(end);
        setMovieId(movie);
        //setHallId(hall);
        setShowTimeId(show);
        scan.close();
    }

    @Override
    public String toString() {
        return "showTimeId: " + showTimeId + "\nstartTime: " + startTime 
                + "\nendTime: " + endTime + "\nmovieId: "
                + movieId + "\nhallId: " + hallId + "\n";
    }
    
}

package src.cinema;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;

import src.DBConnection.DBConnection;
import src.DataControl.DataPersistence;

public class ShowTime implements DataPersistence{
    private String showTimeId;
    private int hallId;
    private String startTime;
    private String endTime;
    private Movie movie;
    //For loading data
    public ShowTime(String showTimeId, String startTime, String endTime, Movie movie, int hallId) {
        this.showTimeId = showTimeId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.movie = movie;
        this.hallId = hallId;
    }

    // For when creating new ShowTime
    public ShowTime( int hallId) {
        this.showTimeId = null;
        this.startTime = null;
        this.endTime = null;

    }

    

    public String getShowTimeId() {
        return showTimeId;
    }

    public int getHallId() {
        return hallId;
    }

    public String getStartTime() {
        return startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    private void setShowTimeId(String showTimeId) { this.showTimeId = showTimeId; } // ? why are they private ?
    private void setStartTime(String startTime) { this.startTime = startTime; }
    private void setEndTime(String endTime) { this.endTime = endTime; }

    public static void saveAll(ArrayList <ShowTime> showTimes){
        try {
            Connection conn = DBConnection.getConnection();

            if (conn != null) {
                System.out.println("Database connection test successful!");

                String query = "INSERT INTO showTime (showTimeId, startTime, endTime,movieId,hallId)" +
                "VALUES (?, ?, ?, ?, ?) " +
                "ON DUPLICATE KEY UPDATE " + 
                "startTime = VALUES(startTime), endTime = VALUES(endTime), movieId = VALUES(movieId)";

                // Use PreparedStatement to prevent SQL injection
                PreparedStatement pstmt = conn.prepareStatement(query);
                for (ShowTime showTime : showTimes) {
                    pstmt.setString(1, showTime.getShowTimeId());    
                    pstmt.setString(2, showTime.getStartTime());   
                    pstmt.setString(3, showTime.getEndTime());    
                    pstmt.setString(4, showTime.movie.getMovieID());
                    pstmt.setInt(5, showTime.getHallId());
                    pstmt.executeUpdate();
                    Movie.saveAll(showTime.movie);
                }

                // Close resources
                pstmt.close();
                conn.close();
            } else {
                System.out.println("Database connection test failed!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    

    public static ArrayList<ShowTime> loadAll() {
        throw new UnsupportedOperationException("Use loadAll in hall instead");
    }

    @Override
    public void saveData() {
        throw new UnsupportedOperationException("Use saveAll instead");
    }

    @Override
    public void loadData(){
        throw new UnsupportedOperationException("Use loadAll instead");
    }

    
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

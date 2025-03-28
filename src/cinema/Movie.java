package cinema;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import DBConnection.DBConnection;
import DataControl.DataPersistence;

public class Movie implements DataPersistence {

    public static int numberOfMovies = 0;
    private String movieID;
    private String title;
    private int durationMinutes;
    private String genre;
    //private String showTimeId;
    
    //For loading data from database
    public Movie(String movieID, String title, int durationMinutes, String genre) {
        this.movieID = movieID;
        this.title = title;
        this.durationMinutes = durationMinutes;
        this.genre = genre;
    }

    // For when adding new movie
    public Movie(String title, int durationMinutes, String genre) {

        this.movieID = "M" + (++numberOfMovies);
        this.title = title;
        this.durationMinutes = durationMinutes;
        this.genre = genre;
    }

    public static void saveAll(Movie movie){
        try {
            Connection conn = DBConnection.getConnection();

            if (conn != null) {
                System.out.println("Database connection test successful!");

                String query = "INSERT INTO movie (movieID, title, durationMinutes,genre,showTimeId) " +
                "VALUES (?, ?, ?, ?) ";
                //"ON DUPLICATE KEY UPDATE "
                //"showTimeId = VALUES(showTimeId)";

                // Use PreparedStatement to prevent SQL injection
                PreparedStatement pstmt = conn.prepareStatement(query);
                pstmt.setString(1, movie.getMovieID());    
                pstmt.setString(2, movie.getTitle() );   
                pstmt.setInt(3, movie.getDurationMinutes());    
                pstmt.setString(4, movie.getGenre());
                //pstmt.setString(5, movie.getShowTimeId());
                pstmt.executeUpdate();

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

    public String getTitle() {
        return title;
    }

    public String getGenre() {
        return genre;
    }

    public int getDurationMinutes() {
        return durationMinutes;
    }

    public String getMovieID() {
        return movieID;
    }

    // public String getShowTimeId() {
    //     return showTimeId;
    // }

    public static int getTotalMovies() {
        return numberOfMovies;
    }

    @Override
    public boolean equals(Object obj) {

        Movie other = (Movie) obj;
        return movieID.equals(other.movieID);
    }

    public static String getLastIdFromDB(){
        try {
            Connection conn = DBConnection.getConnection();
            System.out.println(conn);
            if (conn != null) {
                String query = "SELECT COUNT(*) as lastId FROM movie";
                PreparedStatement stmt = conn.prepareStatement(query);
                ResultSet set = stmt.executeQuery();
                String id = "";
                while (set.next()){
                    id = set.getString("lastId");
                }
                
                conn.close();
                return id;
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "0";
    }

    @Override
    public String toString() {
        return "Movie [movieID=" + movieID + ", title=" + title + ", duration=" + durationMinutes + ", genre=" + genre
                + "]";
    }

    public static ArrayList<Movie> loadAll() {
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

};

package src.main;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.ResultSet;

import src.DBConnection.DBConnection;
import src.cinema.Cinema;
import src.cinema.Movie;

public class SQLTest {
    public static void loadFromDatabase() {
        ArrayList<Movie> movies = new ArrayList<>();
        try {
            Connection conn = DBConnection.getConnection();

            if (conn != null) {
                System.out.println("Database connection test successful!");
                String query = "SELECT * FROM Movies;";
                ResultSet set = DBConnection.executeQuery(query);
                while (set.next()) {
                    Movie temp = new Movie(set.getString("title"), set.getInt("durationMinutes"),
                            set.getString("genre"));
                    movies.add(temp);
                }
                Movie.writeMoviesToFile(movies, "src/main/Movie.csv");
                DBConnection.closeConnection();
            } else {
                System.out.println("Database connection test failed!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        // Test the DB connection using DBConnection class
        // Connection conn = DBConnection.getConnection();

        // if (conn != null) {
        //     System.out.println("Database connection test successful!");
        //     DBConnection.closeConnection();
        // } else {
        //     System.out.println("Database connection test failed!");
        // }
        loadFromDatabase();
    }
}

package src.main;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import java.sql.ResultSet;

import src.DBConnection.DBConnection;
import src.cinema.Cinema;
public class SQLTest {
    public static Cinema loadFromDatabase(){
        Cinema cinema = new Cinema("Hello","Hello",3);
        try {
            Connection conn = DBConnection.getConnection();
            
            if (conn != null) {
                System.out.println("Database connection test successful!");
                String query = "SELECT * FROM Cinema;";
                ResultSet set = DBConnection.executeQuery(query);
                String name = "";
                String location = "";
                int totalHalls;
                while(set.next()){
                    name = set.getString("name");
                    location = set.getString("location");
                    totalHalls = set.getInt("totalHalls");
                    cinema = new Cinema(name,location,totalHalls);
                }
                DBConnection.closeConnection();
            } else {
                System.out.println("Database connection test failed!");
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return cinema;
    }
    public static void main(String[] args) {
        // Test the DB connection using DBConnection class
        Connection conn = DBConnection.getConnection();
        
        if (conn != null) {
            System.out.println("Database connection test successful!");
            DBConnection.closeConnection();
        } else {
            System.out.println("Database connection test failed!");
        }
    }
}

package src.cinema;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import src.DBConnection.DBConnection;
import src.DataControl.DataPersistence;

public class Cinema implements DataPersistence{
    public String name;
    public String location;
    public int totalHalls;
    public ArrayList <Hall> halls;

    public Cinema() { //For loading data in object creation
        loadData();
    }
    public Cinema(String name, String location, int totalHalls) {
        if(totalHalls < 1){
            throw new IllegalArgumentException("Total hall must be 1 or more"); //Should be handle in main (object creation)
        }
        halls = new ArrayList<>();
        this.name = name;
        this.location = location;
        this.totalHalls = totalHalls;
    }
    
    @Override
    public void saveData() {
        try {
            Connection conn = DBConnection.getConnection();

            if (conn != null) {
                System.out.println("Database connection test successful!");

                // Correct query using placeholders
                String query = "INSERT INTO cinema (name, location, totalHalls) VALUES (?, ?, ?)";

                // Use PreparedStatement to prevent SQL injection
                PreparedStatement pstmt = conn.prepareStatement(query);
                pstmt.setString(1, name);       // Assuming name is a String
                pstmt.setString(2, location);   // Assuming location is a String
                pstmt.setInt(3, totalHalls);    // Assuming totalHalls is an int

                // Execute the query
                int rowsInserted = pstmt.executeUpdate();
                if (rowsInserted > 0) {
                    System.out.println("Cinema saved successfully!");
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

    @Override
    public void loadData(){
        try {
            Connection conn = DBConnection.getConnection();

            if (conn != null) {
                System.out.println("Database connection test successful!");
                String query = "SELECT * FROM cinema";
                PreparedStatement stmt = conn.prepareStatement(query);
                ResultSet set = stmt.executeQuery();
                this.name = set.getString("name");
                this.location = set.getString("location");
                this.totalHalls = set.getInt("totalHalls");

                halls = Hall.loadAll();
                
                stmt.close();
                conn.close();
            } else {
                System.out.println("Database connection test failed!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void iniHall()
    {
        if(halls.isEmpty())
        {
            for(int i = 0 ; i < totalHalls ; i++ ){
                Hall newHall = new Hall();
                halls.add(newHall);
            }
        }
        
    }

    public void iniHall(ArrayList<Hall> halls){
        this.halls = halls;
    }

    @Override
    public String toString() {
        return "Cinema [name=" + name + ", location=" + location + ", totalHalls=" + totalHalls + ", halls=" + halls
                + "]";
    }

    
}

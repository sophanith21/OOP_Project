package src.cinema;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;

import src.DBConnection.DBConnection;
import src.DataControl.DataPersistence;
import src.booking.Booking;

public class Hall implements DataPersistence{
    private int hallId;
    public static int maxSeats = 200;
    public ArrayList<ShowTime> showTimes;    //Stores info about the movie according to the specific showtimes
    public ArrayList<ArrayList<Seat>> seats; //each seat has the id of all users accross all the existed showtimes
    private String status;                    // Open, Closed, Full, Maintenances
    private static int numberOfHalls = 0;
    private int managerId;

    public static int rowsPerHall = 10;
    public static int seatsPerRow = 20;
    
    // For initialization for admin
    public Hall(){
        this.hallId = ++numberOfHalls;
        showTimes = new ArrayList<>();
        seats = new ArrayList<>();
        status = "Open";
    }

    // For loading data
    public Hall(int hallId, ArrayList<ShowTime> showTimes, ArrayList<ArrayList<Seat>> seats, String status, int managerId) {
        this.hallId = hallId;
        this.showTimes = showTimes;
        this.seats = seats;
        this.status = status;
        this.managerId = managerId;
    }


    public static void saveAll(ArrayList<Hall> halls) {
        try {
            Connection conn = DBConnection.getConnection();
    
            if (conn != null) {
                System.out.println("Database connection successful!");
                String query = "INSERT INTO hall (hallId,status,managerId) " +
                "VALUES (?, ?, ?) " +
                "ON DUPLICATE KEY UPDATE status = VALUES(status)";
                PreparedStatement pstmt = conn.prepareStatement(query);
    
                for (Hall hall : halls) {
                    pstmt.setInt(1, hall.hallId);
                    pstmt.setString(2, hall.status);
                    pstmt.setInt(3,hall.managerId);
                    pstmt.executeUpdate();
                    Seat.saveAll(hall.seats);
                }
    
                pstmt.close();
                conn.close();
            } else {
                System.out.println("Database connection failed!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    

    public static ArrayList<Hall> loadAll() {
        ArrayList<Hall> halls = new ArrayList<>();
        try {
            Connection conn = DBConnection.getConnection();
    
            if (conn != null) {
                System.out.println("Database connection successful!");
                
                // Step 1: Load all halls
                String hallQuery = "SELECT * FROM hall";
                PreparedStatement hallStmt = conn.prepareStatement(hallQuery);
                ResultSet hallSet = hallStmt.executeQuery();
    
                while (hallSet.next()) {
                    int hallId = hallSet.getInt("hallId");
                    int managerId = hallSet.getInt("managerId");
                    String status = hallSet.getString("status");
    
                    // Step 2: Load ShowTimes for this hall
                    ArrayList<ShowTime> showTimes = new ArrayList<>();
                    String showTimeQuery = "SELECT * FROM showtime WHERE hallId = ?";
                    PreparedStatement showTimeStmt = conn.prepareStatement(showTimeQuery);
                    showTimeStmt.setInt(1, hallId);
                    ResultSet showTimeSet = showTimeStmt.executeQuery();
    
                    while (showTimeSet.next()) {
                        String showTimeId = showTimeSet.getString("showTimeId");
                        String startTime = showTimeSet.getString("startTime");
                        String endTime = showTimeSet.getString("endTime");

                        // Step 3: Load a Movie for this showTime
                        // This is just a false initialization
                        Movie movie = new Movie(startTime, hallId, endTime);
                        String movieQuery = "SELECT * FROM movie WHERE showTimeId = ?";
                        PreparedStatement movieStmt = conn.prepareStatement(movieQuery);
                        movieStmt.setString(1, showTimeId);
                        ResultSet movieSet = movieStmt.executeQuery();
    
                        while (movieSet.next()) {
                                movie = new Movie(
                                movieSet.getString("movieId"),
                                movieSet.getString("title"),
                                movieSet.getInt("durationMinutes"),
                                movieSet.getString("genre"),
                                movieSet.getString("showTimeId")
                            );
                        }
                        movieStmt.close();
    
                        // Create ShowTime object with movies
                        ShowTime showTime = new ShowTime(showTimeId, startTime,endTime, movie,hallId);
                        showTimes.add(showTime);
                    }
                    showTimeStmt.close();
    
                    // Step 4 load all seats for this hall
                    ArrayList<ArrayList<Seat>> seats = new ArrayList<>();
                    for (int i = 0 ; i < 10 ; i++) {
                        seats.add(new ArrayList<>());
                    }
                    String seatsQuery = "SELECT * FROM seat WHERE hallId = ?";
                    PreparedStatement seatsStmt = conn.prepareStatement(seatsQuery);
                    seatsStmt.setInt(1, hallId);
                    ResultSet seatsSet = seatsStmt.executeQuery();

                    while(seatsSet.next()) {
                        String seatId = seatsSet.getString("seatId");
                        String [] rowCol = (seatId.split("-"));
                        int row = Integer.parseInt(rowCol[0]);
                        int col = Integer.parseInt(rowCol[1]);
                        ArrayList <Booking> bookings = new ArrayList<>();
                        String bookQuery = "SELECT b.* FROM booking b " +
                       "JOIN seatbooking bs ON b.bookingId = bs.bookingId " +
                       "WHERE bs.seatId = ?";
                        PreparedStatement bookStmt = conn.prepareStatement(bookQuery);
                        bookStmt.setString(1,seatId);
                        ResultSet bookSet = bookStmt.executeQuery();

                        while (bookSet.next()) {
                            String bookingId = bookSet.getString("bookingId");
                            String seatIdBookedQuery = "SELECT seatId FROM seatbooking WHERE bookingId = ?";
                            PreparedStatement seatIdBookedStmt = conn.prepareStatement(seatIdBookedQuery);
                            seatIdBookedStmt.setString(1, bookingId);
                            ResultSet seatIdbookedSet = seatIdBookedStmt.executeQuery();
                            HashSet <String> seatIds = new HashSet<>();
                
                            while (seatIdbookedSet.next()) {
                                seatIds.add(seatIdbookedSet.getString("seatId"));
                            }

                            Booking booked = new Booking(
                                bookSet.getString("bookingId"),
                                bookSet.getString("showTimeId"),
                                bookSet.getString("movieId"),
                                seatIds,
                                bookSet.getDouble("totalPrice"),
                                bookSet.getString("paymentId"),
                                bookSet.getString("customerId"),
                                bookSet.getString("bookingType")
                            );
                            bookings.add(booked);
                        }
                        Seat seat = new Seat(
                            seatsSet.getString("seatType"),
                            seatsSet.getInt("hallId"), 
                            seatsSet.getString("seatId"),
                            seatsSet.getDouble("price"),
                            seatsSet.getString("services"),
                            bookings
                        );
                        seats.get(row).add(col, seat);
                    }
                    seatsStmt.close();
                    // Create Hall object with showTimes
                    Hall hall = new Hall(hallId, showTimes, seats, status,managerId);
                    halls.add(hall);
                }
    
                hallStmt.close();
                conn.close();
            } else {
                System.out.println("Database connection failed!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return halls;
    }
    
    

    @Override
    public void saveData() {
        throw new UnsupportedOperationException("Use saveAll instead");
    }

    @Override
    public void loadData(){
        throw new UnsupportedOperationException("Use loadAll instead");
    }

    public void initSeats(){
        for(int i = 0; i < rowsPerHall; i++){
            ArrayList <Seat> newSeats = new ArrayList<>();
            seats.add(newSeats);

            for(int j = 0; j < seatsPerRow; j++){
                if((i >= 3 && i <= 6) && (j >= 8 && j <= 11)){
                    seats.get(i).add(new Seat(hallId, i+1, j+1, "Elevated Seat"));
                } else {
                    seats.get(i).add(new Seat(hallId,i+1,j+1));
                }
            }
        }
    }

    public void initSeats(ArrayList<ArrayList<Seat>> seats) {
        this.seats = seats;
    }

    public void setStatus(String status){
        if (!status.equals("Open") && !status.equals("Closed") && !status.equals("Full") && !status.equals("Maintenance")) {
            throw new IllegalArgumentException("Invalid status: " + status); //Shoulb be handle in main
        }
        this.status = status;
    }
    public String getStatus(){
        return status;
    }

    public void setHallId(int hallId){
        this.hallId = hallId;
    }

    public int getHallId() {
        return hallId;
    }

    public void addShowTime() {
        ShowTime newShowTime = new ShowTime(hallId);
        showTimes.add(newShowTime);

        newShowTime.setShowTime();
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
        return "Hall [hallId=" + hallId + ", showTimes=" + (showTimes.isEmpty() ? "No ShowTimes" : showTimes) + ", status=" + status + "]";
    }
    
}

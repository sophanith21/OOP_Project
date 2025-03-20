package cinema;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import DBConnection.DBConnection;
import DataControl.DataPersistence;
import booking.Booking;

public class Seat implements DataPersistence{
    private String seatType;            // "Regular", "VIP"
    public ArrayList <Booking> booked;  // Stores user ID and showtimeID that has booked or occupied the seat
    private String services;

    //No status field because status is dependent on the ShowTime, so logic is written in getStatus() instead

    private int hallId;                 // Hall ID
    private double price;               // Seat price
    private String seatId;              // Unique seat identifier (row-seat)
    public static int seatTaken = 0;    // Tracks booked seats (To be implemented)
    
    public Seat(int hallId, int rowNumber, int colNum){
        this.seatType = "Regular";
        this.hallId = hallId;
        this.seatId = rowNumber + "-" + colNum;
        this.services = "";
        booked = new ArrayList<>();

        this.price = 10.d;
    }

    //Use mainly for loading data
    public Seat(String seatType,int hallId,String seatId,double price,String services,ArrayList <Booking> booked) { 
        this.seatType = seatType;
        this.hallId = hallId;
        this.seatId = seatId;
        this.price = price;
        this.services = services;
        this.booked = booked;
    }

    public Seat(int hallId, int rowNumber, int seatNum , String services) {
        if (services.isBlank()) {
            this.seatType = "Regular";
        } else {
            this.seatType = "VIP";
        }
        
        this.hallId = hallId;
        this.seatId = rowNumber + "-" + seatNum;
        booked = new ArrayList<>();

        this.price = 15.0d;
        this.services = services;
    }

    public static void saveAll(ArrayList <ArrayList<Seat>> seats) {
        try {
            Connection conn = DBConnection.getConnection();
    
            if (conn != null) {
                System.out.println("Database connection successful!");
                String query = "INSERT INTO seat " +
                "VALUES (?, ?, ?, ?, ?, ?, ?) " +
                "ON DUPLICATE KEY " +
                "UPDATE " +
                "seatType = VALUES(seatType)," +
                "price = VALUES(price)" + 
                "services =VALUES(services)";
                PreparedStatement pstmt = conn.prepareStatement(query);
    
                for (int i = 0 ; i < seats.size(); i++) {
                    for (int k = 0 ; k <seats.get(0).size(); k++) {
                        Seat currentSeat = seats.get(i).get(k);
                        pstmt.setString(1, currentSeat.getSeatId());
                        pstmt.setString(2, currentSeat.getSeatType());
                        pstmt.setInt(3, currentSeat.getHallId());
                        pstmt.setDouble(4, currentSeat.getPrice());
                        pstmt.setString(5,currentSeat.getServices());
                        pstmt.executeUpdate();
                        Booking.saveAll(currentSeat.booked);
                    }
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
    

    public static ArrayList<ArrayList<Seat>> loadAll() {
        throw new UnsupportedOperationException("Use loadAll of Hall instead");
    }
    
    @Override
    public void saveData() {
        throw new UnsupportedOperationException("Use saveAll instead");
    }
    
    @Override
    public void loadData(){
        throw new UnsupportedOperationException("Use loadAll instead");
    }

    public String getSeatType() {
        return seatType;
    }

    public void setSeatType(String seatType) { //Though rare in a fixed VIP seat hall structure, if the object is VIP
                                               //but seatType is "Regular", the issue will be handled in ShowTimes
        if(!seatType.equals("Regular") && !seatType.equals("VIP")){
            throw new IllegalArgumentException("Invalid seatType: " + seatType);
        }
        this.seatType = seatType;
    }

    public String getStatus(String showTime) {
        if(!booked.isEmpty()) {
            for(int i = 0; i< booked.size(); i++) {
                if(booked.get(i).ShowTimeId.equals(showTime)){
                    return "Booked";
                } else {
                    return "Available";
                }
            }
        }
        return "Available";
    }

    public String getServices(){
        return services;
    }

    public String getBookingsInfo() {
        if(booked.isEmpty())
        {
            System.out.println("Empty booking information");
            return "Empty booking information";
        }
        String temp = "Show Times that have this seat booked: \n";
        for(int i = 0 ; i < booked.size(); i++){
            temp = temp + i + ". " + booked.get(i).ShowTimeId + "\n";
        }
        return temp;
    }

    public void addBookedSeat(Booking newBook) {
        if(!booked.contains(newBook)){
            if(newBook.seatIds.contains(this.seatId)){
                booked.add(newBook);
            } else {
                System.out.println("This new booked seat's ID is different from this seat");
            }
        }
        
    }

    public int getHallId() {
        return hallId;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }


    public String getSeatId() {
        return seatId;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Seat other = (Seat) obj;
        if (hallId != other.hallId)
            return false;
        if (seatId == null) {
            if (other.seatId != null)
                return false;
        } else if (!seatId.equals(other.seatId))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Seat [seatType=" + seatType + ", booked=" + (booked.isEmpty() ? "Empty":booked) + ", hallId=" + hallId + ", price=" + price
                + ", seatId=" + seatId + ", services=" + (services.isBlank()? "Empty":services) + "]";
    }
     
    
}

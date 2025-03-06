package src.cinema;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import src.booking.Booking;

public class Seat implements DataPersistence{
    private String seatType;            // "Regular", "VIP"
    public ArrayList <Booking> booked;  // Stores user ID and showtimeID that has booked or occupied the seat
    
    //No status field because status is dependent on the ShowTime, so logic is written in getStatus() instead

    private int hallId;                 // Hall ID
    private double price;               // Seat price
    private String seatId;              // Unique seat identifier (row-seat)
    public static int seatTaken = 0;    // Tracks booked seats (To be implemented)
    
    public Seat(int hallId, int rowNumber, int seatNum){
        this.seatType = "Regular";
        this.hallId = hallId;
        this.seatId = rowNumber + "-" + seatNum;
        booked = new ArrayList<>();

        if (seatType.equals( "VIP")) {
            this.price = 15.0d;
        } else {
            this.price = 10.0d;
        }
    }

    public Seat(String seatType,int hallId,String seatId,double price) { //Use mainly for loading data
        this.seatType = seatType;
        this.hallId = hallId;
        this.seatId = seatId;
        this.price = price;
    }

    public Seat(int hallId, int rowNumber, int seatNum, String seatType ) {
        this.seatType = seatType;
        this.hallId = hallId;
        this.seatId = rowNumber + "-" + seatNum;
        booked = new ArrayList<>();

        if (seatType == "VIP") {
            this.price = 15.0d;
        } else {
            this.price = 10.0d;
        }
    }

    public static void saveAll(String fileName,ArrayList<Seat> seats){
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write("Seat Type,Hall ID,Seat ID,Price,Services");
            for(Seat seat: seats) {
                if(seat instanceof VIPSeat) {
                    VIPSeat vipseat = (VIPSeat) seat;
                    writer.write(vipseat.getSeatType() + "," + vipseat.getHallId() + "," + vipseat.getSeatId() + "," + vipseat.getPrice() + "," + vipseat.getService() );
                } else {
                    writer.write(seat.getSeatType() + "," + seat.getHallId() + "," + seat.getSeatId() + "," + seat.getPrice() );
                }
                writer.write("\n");
            }
            System.out.println("Seat's data saved successfully");
        } catch (IOException e) {
            System.out.println("An error occured");
            e.printStackTrace();
        }
    }

    public static ArrayList<Seat> loadAll(String fileName) {
        ArrayList<Seat> seats = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader (new FileReader(fileName))) {
            reader.readLine();
            String line;
            while ((line = reader.readLine()) != null) {
                String [] data = line.split(",");
                Seat seat = new Seat(data[0], Integer.parseInt(data[1]), data[2],Double.parseDouble(data[3]));
                seats.add(seat);
            }
            System.out.println("All Seats loaded successfully");
        } catch (IOException e){
            System.out.println("An error occured while loading seats");
            e.printStackTrace();
        }
        return seats;
    } 
    @Override
    public void saveData(String fileName) {
        throw new UnsupportedOperationException("Use saveAll instead");
    }
    
    @Override
    public void loadData(String fileName){
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
                if(booked.get(i).reserveTime.equals(showTime)){
                    return "Booked";
                } else {
                    return "Available";
                }
            }
        }
        return "Available";
    }

    public String getBookingsInfo() {
        if(booked.isEmpty())
        {
            System.out.println("Empty booking information");
            return "Empty booking information";
        }
        String temp = "Show Times that have this seat booked: \n";
        for(int i = 0 ; i < booked.size(); i++){
            temp = temp + i + ". " + booked.get(i).reserveTime + "\n";
        }
        return temp;
    }

    public void addBookedSeat(Booking newBook) {
        if(!booked.contains(newBook)){
            if(newBook.seatId.contains(this.seatId)){
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
        return "Seat: " + seatType + "," + hallId + "," + price
                + "," + seatId;
    }  
    
}

package src.cinema;

import java.util.ArrayList;

import src.booking.Booking;

public class Seat {
    private String seatType;            // "Regular", "VIP"
    private String status;              // "Booked", "Available", or "Occupied"
    private ArrayList <Booking> booked; // Stores user ID and showtimeID that has booked or occupied the seat
    private final int hallId;           // Hall ID
    private double price;               // Seat price
    private final String seatId;        // Unique seat identifier (row-seat)
    public static int seatTaken = 0;    // Tracks booked seats
    
    public Seat(int hallId, int rowNumber, int seatNum){
        this.seatType = "Regular";
        this.hallId = hallId;
        this.seatId = rowNumber + "-" + seatNum;
        this.status = "Available";
        booked = new ArrayList<>();

        if (seatType == "VIP") {
            this.price = 15.0d;
        } else {
            this.price = 10.0d;
        }
    }
    public Seat(int hallId, int rowNumber, int seatNum, String seatType ) {
        this.seatType = seatType;
        this.hallId = hallId;
        this.seatId = rowNumber + "-" + seatNum;
        this.status = "Available";
        booked = new ArrayList<>();

        if (seatType == "VIP") {
            this.price = 15.0d;
        } else {
            this.price = 10.0d;
        }
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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
        return "Seat [seatType=" + seatType + ", booked=" + getBookingsInfo() + ", hallId=" + hallId + ", price=" + price
                + ", seatId=" + seatId + "]";
    }
    
    

    
    
}

package src.cinema;

import java.util.Objects;

public class Seat {
    protected String seatType;
    private String status; // "Booked", "Available", or "Occupied"
    private String userId; // Stores user ID if booked
    private int hallId; // Hall ID
    private double price; // Seat price
    private String showtimeId; // Showtime ID
    private String seatId; // Unique seat identifier (row-seat)
    private int rowNumber; // Row number
    public static int seatTaken = 0; // Tracks booked seats

    public Seat(int hallId, int rowNumber, int seatNum) {
        this.seatType = "Regular";
        this.hallId = hallId;
        this.rowNumber = rowNumber;
        this.seatId = rowNumber + "-" + seatNum;
        this.status = "Available";
        this.userId = "";
        this.showtimeId = "";

        // Assign price based on row
        if (rowNumber < 5) {
            this.price = 15.0;
        } else if (rowNumber < 10) {
            this.price = 10.0;
        } else {
            this.price = 5.0; // Default price for other rows
        }
    }

    // Update seat status and track booked count
    public void setStatus(String status) {
        if (this.status.equals("Booked") && !status.equals("Booked")) {
            seatTaken--; // Decrement when unbooking
        } else if (!this.status.equals("Booked") && status.equals("Booked")) {
            seatTaken++; // Increment when booking
        }
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getHallId() {
        return hallId;
    }

    public void setHallId(int hallId) {
        this.hallId = hallId;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getShowtimeId() {
        return showtimeId;
    }

    public void setShowtimeId(String showtimeId) {
        this.showtimeId = showtimeId;
    }

    public String getSeatId() {
        return seatId;
    }

    public int getRowNumber() {
        return rowNumber;
    }

    // Only compare seatId and hallId for uniqueness
    @Override
    public int hashCode() {
        return Objects.hash(seatId, hallId);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Seat other = (Seat) obj;
        return Objects.equals(seatId, other.seatId) && hallId == other.hallId;
    }

    @Override
    public String toString() {
        return "Seat [seatType=" + seatType + ", status=" + status + ", userId=" + userId + ", hallId=" + hallId
                + ", price=" + price + ", showtimeId=" + showtimeId + ", seatId=" + seatId + ", rowNumber=" + rowNumber
                + "]\n";
    }

    
}

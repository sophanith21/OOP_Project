package src.cinema;


public class Seat {
    private String seatType;
    private String status; // "Booked", "Available", or "Occupied"
    private String userId; // Stores user ID if booked
    private int hallId; // Hall ID
    private double price; // Seat price
    private String showtimeId; // Showtime ID
    private String seatId; // Unique seat identifier (row-seat)
    public static int seatTaken = 0; // Tracks booked seats

    public Seat(int hallId, int rowNumber, int seatNum, String seatType ) {
        this.seatType = seatType;
        this.hallId = hallId;
        this.seatId = rowNumber + "-" + seatNum;
        this.status = "Available";
        this.userId = "";
        this.showtimeId = "";

        if (seatType == "VIP") {
            this.price = 15.0d;
        } else {
            this.price = 10.0d;
        }
    }

    public String getSeatType() {
        return seatType;
    }

    public void setSeatType(String seatType) {
        this.seatType = seatType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public void setSeatId(String seatId) {
        this.seatId = seatId;
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
        if (showtimeId == null) {
            if (other.showtimeId != null)
                return false;
        } else if (!showtimeId.equals(other.showtimeId))
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
        return "Seat [seatType=" + seatType + ", status=" + status + ", userId=" + userId + ", hallId=" + hallId
                + ", price=" + price + ", showtimeId=" + showtimeId + ", seatId=" + seatId + "]";
    }
    
}

package src.cinema;


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

        // Assign price based on row (Haven't fix about VIP seats yet)
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

    @Override
    public String toString() {
        return "Seat [seatType=" + seatType + ", status=" + status + ", userId=" + userId + ", hallId=" + hallId
                + ", price=" + price + ", showtimeId=" + showtimeId + ", seatId=" + seatId + ", rowNumber=" + rowNumber
                + "]\n";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((seatType == null) ? 0 : seatType.hashCode());
        result = prime * result + ((status == null) ? 0 : status.hashCode());
        result = prime * result + ((userId == null) ? 0 : userId.hashCode());
        result = prime * result + hallId;
        long temp;
        temp = Double.doubleToLongBits(price);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        result = prime * result + ((showtimeId == null) ? 0 : showtimeId.hashCode());
        result = prime * result + ((seatId == null) ? 0 : seatId.hashCode());
        result = prime * result + rowNumber;
        return result;
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
        if (seatType == null) {
            if (other.seatType != null)
                return false;
        } else if (!seatType.equals(other.seatType))
            return false;
        if (status == null) {
            if (other.status != null)
                return false;
        } else if (!status.equals(other.status))
            return false;
        if (userId == null) {
            if (other.userId != null)
                return false;
        } else if (!userId.equals(other.userId))
            return false;
        if (hallId != other.hallId)
            return false;
        if (Double.doubleToLongBits(price) != Double.doubleToLongBits(other.price))
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
        if (rowNumber != other.rowNumber)
            return false;
        return true;
    }

    
}

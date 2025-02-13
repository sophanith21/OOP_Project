package src.user;
import src.booking.Booking;
import java.util.ArrayList;
public class Customer extends User{

    private static int totalCustomers = 0;
    private String id;
    private String role = "Customer";
    private ArrayList <Booking> bookings;
 
    //register
    public Customer (String name, String phone, String email, String password) {

        super(name, phone, email, password);
        this.id = "C" + (++totalCustomers);
        this.bookings = new ArrayList<>();
    }

    public String getID () {
        return id;
    }

    public String getRole () {
        return role;
    }

    public static int getTotalCustomers () {
        return totalCustomers;
    }

    public ArrayList<Booking> getBookings() {
        return new ArrayList<>(bookings);  
    }

    @Override
    public String toString() {
        return "Customer{" +
                "customerID='" + id + '\'' +
                ", customerName='" + userName + '\'' +
                ", isLoggedIn=" + isLoggedIn +
                ", numberOfBookings=" + bookings.size() +
                '}';
    }
}

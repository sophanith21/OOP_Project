package src.user;
import src.booking.Booking;
import java.util.ArrayList;
import java.util.Objects;
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

    public Customer () {
        super();
        this.bookings = new ArrayList<>(); //initial obj for bookings to reference, so we can use .size()
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
    public void register () {
        this.id = "C" + (++totalCustomers);
        super.register(); 
    }

    @Override
    public String toString() {
        return "Customer [userName=" + userName + ", id=" + id + ", userPhoneNumber=" + userPhoneNumber + ", role="
                + role + ", userEmail=" + userEmail + ", isLoggedIn=" + isLoggedIn + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((role == null) ? 0 : role.hashCode());
        result = prime * result + ((bookings == null) ? 0 : bookings.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        if (getClass() != obj.getClass())
            return false;
        Customer other = (Customer) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (role == null) {
            if (other.role != null)
                return false;
        } else if (!role.equals(other.role))
            return false;
        if (bookings == null) {
            if (other.bookings != null)
                return false;
        } else if (!bookings.equals(other.bookings))
            return false;
        return true;
    }

}

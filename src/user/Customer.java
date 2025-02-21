package src.user;

import src.booking.Booking;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Objects;
import java.util.Scanner;
import java.util.Set;
public class Customer extends User{

    private String id;
    private String role = "Customer";
    private Set<Booking> bookings = new HashSet<>(); //each booking must be unique
 
    public Customer (String name, String phone, String email, String password, String id) {

        super(name, phone, email, password);
        this.id = id;
        this.bookings = new HashSet<>();
    }

    @Override
    public String getUserID() {
        return id;
    }

    public String getRole () {
        return role;
    }

    public ArrayList<Booking> getBookings() {
        return new ArrayList<>(bookings);  
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

    @Override
    public String toString() {
        return "Customer [Name=" + userName + ", PhoneNumber=" + userPhoneNumber + ", ID=" + id + ", Email="
                + userEmail + ", isLoggedIn=" + isLoggedIn + "]";
    }
}

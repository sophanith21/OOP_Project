package src.user;

import src.booking.Booking;

import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Scanner;
import java.util.Set;
public class Customer extends User{

    private double walletBalance;
    private Set<String> bookingIds = new LinkedHashSet<>();  //only store booking id instead of entire booking objects
    //uniqueness, fast lookups, and order preservation
 
    // Constructor
    public Customer(int userId, String username, String password, String email, String fullName, 
                    String phoneNumber, LocalDate dateOfBirth, LocalDateTime registrationDate, 
                    String role, double walletBalance) {
        super(userId, username, password, email, fullName, phoneNumber, dateOfBirth, "CUSTOMER");
        this.walletBalance = walletBalance;
    }

    @Override
    public String toString() {
        return super.toString() + ", Customer{" +
                "walletBalance='" + walletBalance + '\'' +
                ", bookingIds=" + bookingIds + '}';
    }
}

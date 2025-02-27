package src.user;

import src.booking.Booking;

import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Scanner;
import java.util.Set;
public class Customer extends User{

    private double walletBalance;
    private String membershipLevel;
    private Set<String> bookingIds = new LinkedHashSet<>();  

    // Constructor
    public Customer(int id, String username, String email, String phone, String password, String membershipLevel, double walletBalance) {
        super(id, username, email, phone, password, "CUSTOMER"); 
        this.walletBalance = walletBalance;
        this.membershipLevel = membershipLevel;
    }

    @Override
    public void displayUser() {
        super.displayUser();
        System.out.println("Wallet Balance: " + walletBalance);
        System.out.println("Membership Level: " + membershipLevel);
        System.out.println("--------------------------------------");
    }

    @Override
    public String toString() {
        return super.toString() + ", Customer{" +
                "walletBalance='" + walletBalance + '\'' +
                ", membershipLevel=" + membershipLevel + '}';
    }
}

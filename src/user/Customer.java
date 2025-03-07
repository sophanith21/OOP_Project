package src.user;

import java.util.Scanner;

public class Customer extends User{

    private double walletBalance = 0.0;
    private String membershipLevel = "silver";
    Scanner sc = new Scanner(System.in);

    public Customer(int id, String username, String email, String phone, String password, double walletBalance, String membershipLevel) {
        super(id, username, email, phone, password, "CUSTOMER"); 
        this.walletBalance = walletBalance;
        this.membershipLevel = membershipLevel;
    }

    @Override
    public String getUserRole() {
        return role;
    }

    public String getMembershipLevel () {
        return membershipLevel;
    }

    public double getBalace () {
        return walletBalance;
    }

    @Override
    public String toString() {
        return super.toString() + "," + walletBalance + "," + membershipLevel;
    }
}

package src.user;

import java.util.Scanner;

public class Customer extends User{

    private double walletBalance;
    private String membershipLevel;
    Scanner sc = new Scanner(System.in);

    public Customer(int id, String username, String email, String phone, String password) {
        super(id, username, email, phone, password, "CUSTOMER"); 
        this.walletBalance = 0.0;
        this.membershipLevel = "basic";
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

package src.user;

import java.util.Scanner;

public class Customer extends User {

    private double walletBalance;
    private String membershipLevel;
    private String favoriteGenre;
    Scanner sc = new Scanner(System.in);

    public Customer(int id, String username, String email, String password, String phone, double walletBalance,
            String membershipLevel, String favoriteGenre, boolean isHashed) {
        super(username, email, password, phone, "CUSTOMER", isHashed);
        this.walletBalance = walletBalance;
        this.membershipLevel = membershipLevel;
        this.favoriteGenre = favoriteGenre;
    }

    public double getBalance() {
        return walletBalance;
    }

    public void setWalletBalance(double walletBalance) {
        if (walletBalance >= 0) {
            this.walletBalance = walletBalance;
        } else {
            throw new IllegalArgumentException("wallet balance can not be negative!");
        }
    }

    public String getMembershipLevel() {
        return membershipLevel;
    }

    public void setMembershipLevel(String membershipLevel)  {
        if (membershipLevel != null && (membershipLevel.equalsIgnoreCase("gold") ||
                membershipLevel.equalsIgnoreCase("silver") ||
                membershipLevel.equalsIgnoreCase("bronze"))) {
            this.membershipLevel = membershipLevel;
        } else {
            throw new IllegalArgumentException ("Invalid membership level. Choose from Gold, Silver, or Bronze.");
        }
    }

    public String getFavoriteGenre() {
        return favoriteGenre;
    }

    public void setFavoriteGenre(String favoriteGenre) {
        if (favoriteGenre != null && !favoriteGenre.trim().isEmpty()) {
            this.favoriteGenre = favoriteGenre;
        } else {
            throw new IllegalArgumentException("Favorite genre cannot be empty.");
        }
    }

    public void displayCustomerMenu() {
        System.out.println("Customer Menu:");
        System.out.println("1. View Balance");
        System.out.println("2. Update Membership Level");
        System.out.println("3. View Favorite Genre");
        System.out.println("4. Log Out");
    }

    @Override
    public String toString() {
        return super.toString() + "," + walletBalance + "," + membershipLevel + "," + favoriteGenre;
    }
}

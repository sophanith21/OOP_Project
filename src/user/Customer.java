package src.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import src.DBConnection.DBConnection;

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
    public void saveData() {
        try {
            Connection conn = DBConnection.getConnection();

            if (conn != null) {
                System.out.println("Database connection test successful!");

                // Correct query using placeholders
                String query = "INSERT INTO user (id, username, hashedPassword, email, phone, role, walletBalance, membershipLevel, favoriteGenre)" + 
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)" + 
                "ON DUPLICATE KEY UPDATE " + 
                "username = VALUES(username)," +
                "hashedPassword = VALUES(hashedPassword)," +
                "email = VALUES(email)," +
                "phone = VALUES(phone)," +
                "role = VALUES(role)," +
                "walletBalance = VALUES(walletBalance)," +
                "membershipLevel = VALUES(membershipLevel)," +
                "favoriteGenre = VALUES(favoriteGenre)";

                // Use PreparedStatement to prevent SQL injection
                PreparedStatement pstmt = conn.prepareStatement(query);
                pstmt.setInt(1, id);
                pstmt.setString(2, username);
                pstmt.setString(3, email);
                pstmt.setString(4, phone);
                pstmt.setString(5, role);
                pstmt.setDouble(6, walletBalance);
                pstmt.setString(7, membershipLevel);
                pstmt.setString(8, favoriteGenre);
                
                // Execute the Query
                pstmt.executeQuery();

                // Close resources
                pstmt.close();
                conn.close();
            } else {
                System.out.println("Database connection test failed!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Load data by checking the current Customer object's username and password
    @Override
    public void loadData(){
        try {
            Connection conn = DBConnection.getConnection();

            if (conn != null) {
                boolean resultChecker = false;
                System.out.println("Database connection test successful!");
                String query = "SELECT * FROM user";
                PreparedStatement stmt = conn.prepareStatement(query);
                ResultSet set = stmt.executeQuery();
                while (set.next()){
                    if(username == set.getString("username")) {
                        if (hashedPassword == set.getString("hashedPassword")) {
                            this.id = set.getInt("id");
                            this.email = set.getString("email");
                            this.phone = set.getString("phone");
                            this.role = set.getString("role");
                            this.walletBalance = set.getDouble("walletBalance");
                            this.membershipLevel = set.getString("membershipLevel");
                            this.favoriteGenre = set.getString("favoriteGenre");

                            resultChecker = true;
                        }
                    }
                }

                if (resultChecker) {
                    System.out.println("Log In successfully");
                } else {
                    System.out.println("Wrong Username or Password");
                }
                
                stmt.close();
                conn.close();
            } else {
                System.out.println("Database connection test failed!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return super.toString() + "," + walletBalance + "," + membershipLevel + "," + favoriteGenre;
    }
}

package src.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.mindrot.jbcrypt.BCrypt;

import src.DBConnection.DBConnection;

public class Admin extends User {
    private ArrayList<Integer> managedHalls; // ad admin can manage multiple halls

    public Admin(int id, String username, String email, String password, String phone, ArrayList<Integer> managedHalls, boolean isHashed) {
        super(username, email, password, phone, "ADMIN", isHashed);
        this.managedHalls = managedHalls;
    }

    public List<Integer> getManagedHalls() {
        return new ArrayList<>(managedHalls); // Return a copy to prevent external modification
    }

    public void setManagedHalls(List<Integer> managedHalls) {
        if (managedHalls != null) {
            this.managedHalls = new ArrayList<>(managedHalls); // Store a copy to prevent external modification
        } else {
            throw new IllegalArgumentException("Managed halls list cannot be null.");
        }
    }

    public void displayAdminMenu() {
        System.out.println("Admin Menu:");
        System.out.println("1. Manage Halls");
        System.out.println("2. View Reports");
        System.out.println("3. Edit User Accounts");
        System.out.println("4. Log Out");
    }

    @Override
    public void saveData() {
        try {
            Connection conn = DBConnection.getConnection();

            if (conn != null) {
                System.out.println("Database connection test successful!");

                // Correct query using placeholders
                String query = "INSERT INTO user (id, username, hashedPassword, email, phone, role) " + 
                "VALUES (?, ?, ?, ?, ?, ?)" + 
                "ON DUPLICATE KEY UPDATE " + 
                "username = VALUES(username)," +
                "hashedPassword = VALUES(hashedPassword)," +
                "email = VALUES(email)," +
                "phone = VALUES(phone)," +
                "role = VALUES(role)";

                // Use PreparedStatement to prevent SQL injection
                PreparedStatement pstmt = conn.prepareStatement(query);
                pstmt.setInt(1, id);
                pstmt.setString(2, username);
                pstmt.setString(3, hashedPassword);
                pstmt.setString(4, email);
                pstmt.setString(5, phone);
                pstmt.setString(6, role);

                // Execute the Update
                pstmt.executeUpdate();

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
                    if(username.equals(set.getString("username"))) {
                        if (hashedPassword.equals(set.getString("hashedPassword"))) {
                            this.id = set.getInt("id");
                            this.email = set.getString("email");
                            this.phone = set.getString("phone");
                            this.role = set.getString("role");

                            ArrayList <Integer> hallIds = new ArrayList<>();
                            String hallIdsQuery = "SELECT hall.hallId FROM hall " +
                            "JOIN user ON hall.managerId = user.id " +
                            "WHERE user.id = ?";
                            PreparedStatement hallIdsStmt = conn.prepareStatement(hallIdsQuery);
                            hallIdsStmt.setInt(1, id);
                            ResultSet hallIdsSet = hallIdsStmt.executeQuery();

                            while(hallIdsSet.next()) {
                                Integer temp = hallIdsSet.getInt("hallId");
                                hallIds.add(temp);
                            }
                            
                            managedHalls = hallIds;

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
        return super.toString() + "," + managedHalls.toString().replaceAll("[\\[\\] ]", "");
    }
}
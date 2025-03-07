package src.user;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;
import java.util.Scanner;

public abstract class User {
    protected int id;
    protected String username;
    protected String hashedPassword; 
    protected String email;
    protected String phone;
    protected String role; // "CUSTOMER" or "ADMIN"
    private boolean isLoggedIn;
    
    public User(int id, String username, String email,  String phone, String password, String role) {
        this.id = id;
        this.username = username;
        this.hashedPassword = hashPassword(password);
        this.email = email;
        this.phone = phone;
        this.role = role;
        this.isLoggedIn = false;
    }
    
    public int getID () { return id; }
    public abstract String getUserRole();
    public String getUsername () { return username; }
    public boolean isLoggedIn() { return isLoggedIn; }
    public boolean checkPassword(String password) {
        return BCrypt.checkpw(password, this.hashedPassword);
    }

    public void setLoggedIn(boolean loggedIn) { this.isLoggedIn = loggedIn; }
    
    // Hash password using SHA-256
    private String hashPassword(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashedBytes = digest.digest(password.getBytes());
            StringBuilder hexString = new StringBuilder();
            for (byte b : hashedBytes) {
                hexString.append(String.format("%02x", b));
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error hashing password", e);
        }
    }

    public boolean verifyPassword(String inputPassword) {
        return this.hashedPassword.equals(hashPassword(inputPassword));
    }
    

    /*public void updateProfile () {}
    public void changePassword () {}
    public void viewProfile () {}*/

   
   @Override
   public String toString() {
       return id + "," + username + "," + email + "," + hashedPassword + ","+ phone + "," + role + "," + isLoggedIn;
   }

    @Override
    public boolean equals(Object obj) {
        
        User other = (User) obj;
        return username.equalsIgnoreCase(other.username) || email.equalsIgnoreCase(other.email) || phone.equals(other.phone);

    }

}

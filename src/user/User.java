package src.user;

import java.util.Objects;
import java.util.Scanner;

public class User {
    protected int id;
    protected String username;
    protected String password; 
    protected String email;
    protected String phone;
    protected String role; // "CUSTOMER" or "ADMIN"
    private boolean isLoggedIn;

    public User(int id, String username, String email,  String phone, String password, String role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.role = role;
        this.isLoggedIn = false;
    }

    public int getID () { return id; }
    public String getRole () { return role; }
    public String getUsername () { return username; }
    public boolean isLoggedIn() { return isLoggedIn; }

    public void setLoggedIn(boolean loggedIn) { this.isLoggedIn = loggedIn; }
    public boolean verifyPassword(String inputPassword) {
        return this.password.equals(inputPassword);
    }

    /*public void updateProfile () {}
    public void changePassword () {}
    public void viewProfile () {}*/

   
   @Override
   public String toString() {
       return id + "," + username + "," + email + "," + phone + "," + role + "," + isLoggedIn;
   }

    @Override
    public boolean equals(Object obj) {
        
        User other = (User) obj;
        if (username == null) {
            if (other.username != null)
                return false;
        } else if (!username.equals(other.username))
            return false;
        if (email == null) {
            if (other.email != null)
                return false;
        } else if (!email.equals(other.email))
            return false;
        if (phone == null) {
            if (other.phone != null)
                return false;
        } else if (!phone.equals(other.phone))
            return false;
        return true;
    }

}

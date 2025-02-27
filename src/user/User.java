package src.user;

import java.util.Objects;
import java.util.Scanner;

public class User {
    private int id;
    private String username;
    private String password; 
    private String email;
    private String phone;
    private String role; // "CUSTOMER" or "ADMIN"

    public User(int id, String username, String email,  String phone, String password, String role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.role = role;
    }

    public int getID () {
        return id;
    }

    public String getRole () {
        return role;
    }

    public String getUsername () {
        return username;
    }

    public void displayUser() {
        System.out.println("Id: " + id);
        System.out.println("Username: " + username);
        System.out.println("Email: " + email);
        System.out.println("Phone: " + phone);
        System.out.println("Role: " + role);
        System.out.println("----------------------");
    }

   // toString()
   @Override
   public String toString() {
       return "User{" +
               "userId=" + id +
               ", username='" + username + '\'' +
               ", email='" + email + '\'' +
               ", phoneNumber='" + phone + '\'' +
               ", role='" + role + '\'' +
               '}';
   }

   @Override
    public boolean equals(Object obj) { // object we compare to the current object.
        if (this == obj) return true; // this: refer to the current instance of the object that calls the equals() method
        if (obj == null || getClass() != obj.getClass()) return false; 

        User user = (User) obj; // Cast to User

        return Objects.equals(username, user.username) &&
               Objects.equals(email, user.email) &&
               Objects.equals(phone, user.phone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, email, phone);
    }
}

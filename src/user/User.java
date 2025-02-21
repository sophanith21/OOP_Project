package src.user;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;
import java.util.Scanner;
public abstract class User {

    protected String userName;
    protected String userPhoneNumber;
    protected String userEmail;
    protected String userPassword;
    protected boolean isLoggedIn;

    public User(String name, String phone, String email, String password) {
        
        this.userName = name;
        this.userPhoneNumber = phone;
        this.userEmail = email;
        this.userPassword = password;
        this.isLoggedIn = false;
    }

    public boolean verifyPassword(String password) {
        return this.userPassword.equals(password);
    }

    public boolean isValidPhoneNumber(String number) {
        return number.length() >= 8 && number.length() <= 9 && number.charAt(0) == '0' && number.matches("[0-9]+");
    }

    public boolean isValidEmail(String email) {
        return email.contains("@") && email.contains(".");
    }

    public abstract String getUserID (); //admin and customer implement this method

    public String getName () {
        return userName;
    }

    public String getEmail () {
        if (isLoggedIn) {
            return userEmail;
        }
        return null;
    }

    public String getPhoneNumber () {
        if (isLoggedIn) {
            return userPhoneNumber;
        }
        return null;
    }

    public void setName(String newName, String password) {
        if (!isLoggedIn) {
            System.out.println("Please log in first.");
            return;
        }
        if (!verifyPassword(password)) {
            System.out.println("Incorrect password.");
            return;
        }
        
        if (newName == null || newName.trim().isEmpty()) {
            System.out.println("Name cannot be empty.");
            return;
        }
        this.userName = newName;
        System.out.println("Name updated successfully.");
    }

    public void setPhone(String 
    phone) { 
        if (isLoggedIn) {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter your password to update password: ");
            String inputPassword = scanner.nextLine();
            
            if (verifyPassword(inputPassword)) {
                this.userPhoneNumber = phone;
                System.out.println("Phone number updated successfully.");
            } else {
                System.out.println("Incorrect password. Phone number update failed.");
            }
        } else {
            System.out.println("You must be logged in to update your phone number.");
        }
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((userName == null) ? 0 : userName.hashCode());
        result = prime * result + ((userPhoneNumber == null) ? 0 : userPhoneNumber.hashCode());
        result = prime * result + ((userEmail == null) ? 0 : userEmail.hashCode());
        result = prime * result + ((userPassword == null) ? 0 : userPassword.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        User other = (User) obj;
        if (userName == null) {
            if (other.userName != null)
                return false;
        } else if (!userName.equals(other.userName))
            return false;
        if (userPhoneNumber == null) {
            if (other.userPhoneNumber != null)
                return false;
        } else if (!userPhoneNumber.equals(other.userPhoneNumber))
            return false;
        if (userEmail == null) {
            if (other.userEmail != null)
                return false;
        } else if (!userEmail.equals(other.userEmail))
            return false;
        if (userPassword == null) {
            if (other.userPassword != null)
                return false;
        } else if (!userPassword.equals(other.userPassword))
            return false;
        return true;
    }
}

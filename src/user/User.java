package src.user;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;
public class User implements Authentication {

    protected String userName;
    protected String userPhoneNumber;
    protected String userEmail;
    protected String userPassword;
    protected boolean isLoggedIn;
    public static ArrayList <User> users = new ArrayList<>(); //store admin and customer obj

    //useful for creating obj with pre-populated data like loading obj from file
    public User(String name, String phone, String email, String password) {
        
        this.userName = name;
        this.userPhoneNumber = phone;
        this.userEmail = email;
        this.userPassword = password;
        this.isLoggedIn = false;

    }

    //default constructor for registeration
    public User () {}

    protected boolean verifyPassword(String password) {
        return this.userPassword.equals(password);
    }

    private boolean isValidPhoneNumber(String number) {
        return number.length() >= 8 && number.length() <= 9 && number.charAt(0) == '0' && number.matches("[0-9]+");
    }

    private boolean isValidEmail(String email) {
        return email.contains("@") && email.contains(".");
    }

    private boolean isEmailTaken(String email) {
        for (User user : users) {
            if (user.userEmail.equals(email)) {
                return true;
            }
        }
        return false;
    }

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

    public void setPhone(String newPhone, String password) {
        if (!isLoggedIn) {
            System.out.println("Please log in first.");
            return;
        }
        if (!verifyPassword(password)) {
            System.out.println("Incorrect password.");
            return;
        }
        if (!isValidPhoneNumber(newPhone)) {
            System.out.println("Invalid phone number format!");
            return;
        }
        this.userPhoneNumber = newPhone;
        System.out.println("Phone number updated successfully.");
    }

    @Override
    public boolean login(String email, String password) {
        if (this.userEmail.equals(email) && this.userPassword.equals(password)) {
            isLoggedIn = true;
            System.out.println("Logged in successfully.");
            return true;
        }
        System.out.println("Invalid credentials");
        return false;
    }

    @Override
    public void logout() {
        isLoggedIn = false;
        System.out.println("Logged out.");
    }

    @Override
    public void register() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter Name: ");
        this.userName = scanner.nextLine();

        System.out.print("Enter Phone Number: ");
        String phone = scanner.nextLine();
        if (!isValidPhoneNumber(phone)) {
            System.out.println("Invalid phone number format!");
            return;
        }
        this.userPhoneNumber = phone;

        System.out.print("Enter Email: ");
        String email = scanner.nextLine();
        if (!isValidEmail(email)) {
            System.out.println("Invalid email format!");
            return;
        }
        if (isEmailTaken(email)) {
            System.out.println("Email is already taken.");
            return;
        }
        this.userEmail = email;

        System.out.print("Enter Password: ");
        this.userPassword = scanner.nextLine();

        // Add user to the list of registered users
        users.add(this);

        System.out.println("Registration successful!");
    }

    @Override
    public String toString() {
        return "User [userName=" + userName + ", userPhoneNumber=" + userPhoneNumber + ", userEmail=" + userEmail
                + ", isLoggedIn=" + isLoggedIn + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((userName == null) ? 0 : userName.hashCode());
        result = prime * result + ((userPhoneNumber == null) ? 0 : userPhoneNumber.hashCode());
        result = prime * result + ((userEmail == null) ? 0 : userEmail.hashCode());
        result = prime * result + ((userPassword == null) ? 0 : userPassword.hashCode());
        result = prime * result + (isLoggedIn ? 1231 : 1237);
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
        if (isLoggedIn != other.isLoggedIn)
            return false;
        return true;
    }  
    
}

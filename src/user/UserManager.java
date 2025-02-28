package src.user;

import src.*;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Pattern;

public class UserManager {
    private Scanner scanner = new Scanner(System.in);
    private Map<String, User> users = new LinkedHashMap<>();
    private HashSet<String> emails = new HashSet<>();
    private HashSet<String> phones = new HashSet<>();
    private int nextId = 1; 
    private User loggedInUser = null; // Track the currently logged-in user
    private int loginAttempts = 0; // Track login attempts

    //public UserManager() {}

    public boolean addAdmin(String username, String email, String password, String phone) {
        try {
            // Validate input fields
            validateInput(username, email, phone, password);

            // Check for duplicate username, email, or phone
            if (users.containsKey(username)) {
                throw new InvalidUserException("Username already exists.");
            }
            if (emails.contains(email)) {
                throw new InvalidUserException("Email already exists.");
            }
            if (phones.contains(phone)) {
                throw new InvalidUserException("Phone number already exists.");
            }

            Admin newAdmin = new Admin(nextId++, username, email, phone, password);
            
            users.put(username, newAdmin);
            emails.add(email);
            phones.add(phone);

            System.out.println("Admin Added successful.");
            return true;

        } catch (InvalidUserException e) {
            System.out.println("Admin added failed: " + e.getMessage());
            return false;
        }
    }

    // Register method
    public boolean registerCustomer(String username, String email, String password, String phone, double walletBalance, String membershipLevel) {
        try {
            // Validate input fields
            validateInput(username, email, phone, password);

            // Check for duplicate username, email, or phone
            if (users.containsKey(username)) {
                throw new InvalidUserException("Username already exists.");
            }
            if (emails.contains(email)) {
                throw new InvalidUserException("Email already exists.");
            }
            if (phones.contains(phone)) {
                throw new InvalidUserException("Phone number already exists.");
            }

            Customer newCustomer = new Customer(nextId++, username, email, phone, password, membershipLevel, walletBalance);
            
            users.put(username, newCustomer);
            emails.add(email);
            phones.add(phone);

            System.out.println("Registration successful.");
            return true;

        } catch (InvalidUserException e) {
            System.out.println("Registration failed: " + e.getMessage());
            return false;
        }
    }

    public boolean login() {
        if (loggedInUser != null) {
            System.out.println("A user is already logged in. Please log out first.");
            return false;
        }
    
        Scanner scanner = new Scanner(System.in);
    
        while (loginAttempts < 3) {
            System.out.print("Enter username: ");
            String username = scanner.nextLine();
    
            System.out.print("Enter password: ");
            String password = scanner.nextLine();
    
            if (!users.containsKey(username)) {
                loginAttempts++;
                System.out.println("Login failed: Username not found. Attempts left: " + (3 - loginAttempts));
                continue; // Skip to the next iteration
            }
    
            User user = users.get(username);
            if (!user.verifyPassword(password)) {
                loginAttempts++;
                System.out.println("Login failed: Incorrect password. Attempts left: " + (3 - loginAttempts));
                continue; // Skip to the next iteration
            }
    
            loggedInUser = user;
            loginAttempts = 0; // Reset login attempts on successful login
            System.out.println("Login successful. Welcome, " + user.getUsername() + "!");
            return true;
        }
    
        System.out.println("Too many failed login attempts. Please try again later.");
        return false;
    }

    // Logout method
    public void logout() {
        if (loggedInUser == null) {
            System.out.println("No user is currently logged in.");
            return;
        }

        System.out.println("Logout successful. Goodbye, " + loggedInUser.getUsername() + "!");
        loggedInUser = null;
    }

    // Validate the user input
    private void validateInput(String username, String email, String phone, String password) throws InvalidUserException {
        if (username.isEmpty() || email.isEmpty() || phone.isEmpty() || password.isEmpty()) {
            throw new InvalidUserException("All fields are required.");
        }
        if (!isValidEmail(email)) {
            throw new InvalidUserException("Invalid email format.");
        }
        if (!isValidPhone(phone)) {
            throw new InvalidUserException("Phone number must be 9 or 10 digits long and start with '0'.");
        }
        if (!isValidPassword(password)) {
            throw new InvalidUserException(
                "Password must be at least 8 characters long, include at least one uppercase letter, " +
                "one lowercase letter, one digit, and one special character (@#$%^&*!)."
            );
        }
    }

    // Validate email format
    private boolean isValidEmail(String email) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        Pattern pattern = Pattern.compile(emailRegex);
        return pattern.matcher(email).matches();
    }

    // Validate phone number format
    private boolean isValidPhone(String phone) {
        return phone.matches("^0\\d{8,9}$"); // Starts with '0' and has 9 or 10 digits
    }

    // Validate password format
    private boolean isValidPassword(String password) {
        String passwordRegex = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@#$%^&*!]).{8,}$";
        return password.matches(passwordRegex);
    }

    public void displayAllUsers() {
        if (users.isEmpty()) {
            System.out.println("No users registered yet.");
            return;
        }

        System.out.println("\n=== Registered Users ===");
        for (User user : users.values()) {
            user.displayUser();
        }
    }

    // Get the currently logged-in user
    public User getLoggedInUser() {
        return loggedInUser;
    }
}


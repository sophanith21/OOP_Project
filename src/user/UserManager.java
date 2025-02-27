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

    //public UserManager() {}

    // Register method
    public boolean register(String username, String email, String password, String phone) {
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

            User newUser = new User(nextId++, username, email, phone, password, "CUSTOMER");
            
            users.put(username, newUser);
            emails.add(email);
            phones.add(phone);

            System.out.println("Registration successful.");
            return true;

        } catch (InvalidUserException e) {
            System.out.println("Registration failed: " + e.getMessage());
            return false;
        }
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
}


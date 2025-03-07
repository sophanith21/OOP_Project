package src.user;

import src.*;
import src.exception.*;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InvalidClassException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;

public class UserManager implements Authentication {
    private static Scanner scanner = new Scanner(System.in);
    private static HashSet<User> users = new HashSet<>();
    private int nextId = 1;
    private User loggedInUser = null; 

    @Override
    public void register(String username, String password, String email, String phone, double walletBalance, String membershipLevel) throws InvalidUserDetailsException, DuplicateUserException {
        //InvalidUserDetailsException: ensures no empty, no wrong format
        
        if (username == null || username.trim().isEmpty()) {
            throw new InvalidUserDetailsException("Username cannot be empty.");
        }
        if (password == null || password.length() < 8) {
            throw new InvalidUserDetailsException("Password must be at least 8 characters long.");
        }
        if (email == null || !email.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")) {
            throw new InvalidUserDetailsException("Invalid email format.");
        }
        if (phone == null || !phone.matches("^[0-9]{9,10}$")) {
            throw new InvalidUserDetailsException("Invalid phone number format.");
        }
        if (walletBalance < 0) {
            throw new InvalidUserDetailsException("Wallet balance cannot be negative.");
        }

        // Validate membership level (must be Silver, Gold, or Platinum)
        List<String> validMembershipLevels = Arrays.asList("Silver", "Gold", "Platinum");
        if (!validMembershipLevels.contains(membershipLevel)) {
            throw new InvalidUserDetailsException("Invalid membership level. Choose Silver, Gold, or Platinum.");
        }

        // Hash password before storing
        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());

        // Create Customer object
        Customer customer = new Customer(nextId++, username, hashedPassword, email, phone, walletBalance, membershipLevel);

        // Check for duplicates (Set uses equals())
        if (!users.add(customer)) {
            throw new DuplicateUserException("Duplicate entry: Username, email, or phone is already registered.");
        }
        
        System.out.println("Customer registered successfully: " + customer);
    }
    
    @Override
    public User login(String username, String password) throws UserNotFoundException, InvalidCredentialsException {

        for (User user : users) {
            if (user.getUsername().equalsIgnoreCase(username)) {
                if (user.verifyPassword(password)) {
                    System.out.println("Login successful for customer: " + user.getUsername());
                    return user;
                } else {
                    throw new InvalidCredentialsException("Incorrect password.");
                }
            }
        }

        throw new UserNotFoundException("Username not found.");
    }

    @Override
    public boolean logout(User u) {
        for (User user : users) {
            if (user.getUsername().equalsIgnoreCase(u.username)) {
                if (user.isLoggedIn()) {
                    user.setLoggedIn(false);
                    System.out.println("Logout successful for customer: " + user.getUsername());
                    return true;
                } else {
                    System.out.println("Customer is not logged in: " + user.getUsername());
                    return false;
                }
            }
        }
    
        System.out.println("Username not found: " + u.username);
        return false;
    }

    public HashSet<User> getUsers() {
        return users;
    }

    public void saveData(List<User> users, String fileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (User user : users) {
                writer.write(user.toString());
                writer.newLine();  
            }
        } catch (IOException e) {
            e.printStackTrace();  
        }
    }

    
    
    public List<User> loadData(String fileName) {
        List<User> users = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                
                String[] userDetails = line.split(",");
                int id = Integer.parseInt(userDetails[0]);
                String username = userDetails[1];
                String email = userDetails[2];
                String password = userDetails[3];
                String phone = userDetails[4];
                String role = userDetails[5];

                if (role.equals("CUSTOMER")) {
                    double balance = Double.parseDouble(userDetails[6]);
                    String membership = userDetails[6];
                    Customer user = new Customer(id, username, email, phone, role, balance, membership);
                    users.add(user);
                } else if (role.equals("ADMIN")) {
                    int managedHallId = Integer.parseInt(userDetails[5]);
                    Admin user = new Admin(id, username, email, phone, role, managedHallId);
                    users.add(user);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found. No users loaded.");
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
        return users;
    }

    public void displayAllUsers() {
        if (users.isEmpty()) {
            System.out.println("No users registered.");
            return;
        }
    
        System.out.println("Registered Users:");
        for (User user : users) {
            System.out.println(user);
        }
    }
 
}

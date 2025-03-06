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
import java.util.HashSet;
import java.util.Scanner;

public class UserManager implements Authentication {
    private static Scanner scanner = new Scanner(System.in);
    private static HashSet<User> users = new HashSet<>();
    private int nextId = 1;
    private User loggedInUser = null; // Track the currently logged-in user
    private static final String FILE_NAME = "users.csv";

    public void register(String username, String password, String email, String phone) throws InvalidUserDetailsException, DuplicateUserException {
        if (username == null || username.trim().isEmpty()) {
            throw new InvalidUserDetailsException("Username cannot be empty.");
        }
        if (password == null || password.length() < 8) {
            throw new InvalidUserDetailsException("Password must be at least 8 characters long.");
        }
        if (email != null && email.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")) {
            throw new InvalidUserDetailsException("Invalid email format.");
        }
        if (phone != null && phone.matches("^\\+?[0-9]{10,15}$")) {
            throw new InvalidUserDetailsException("Invalid phone number format.");
        }

        Customer customer = new Customer(nextId++, username, password, email, phone);

        if (!users.add(customer)) {
            throw new DuplicateUserException("Duplicate entry: Username, email, or phone is already registered.");
        }

        System.out.println("Customer registered successfully: " + customer);
    }
    
    @Override
    public User login(String username, String password)
            throws UserNotFoundException, InvalidCredentialsException {

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
    public boolean logout(String username) {
        for (User user : users) {
            if (user.getUsername().equalsIgnoreCase(username)) {
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
    
        System.out.println("Username not found: " + username);
        return false;
    }

    public HashSet<User> getUsers() {
        return users;
    }



    /*public void writeUsersToFile (){
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (User user : users.values()) {
                writer.write(user + "\n");
               
            }
            System.out.println("Users written successfully.");
        } catch (IOException e) {
            System.out.println(" Error writing users: " + e.getMessage());
        }
    }
    
    public void loadUsersFromFile() {
        users.clear(); // Clear existing users before loading
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                try {
                    String[] parts = line.split(",");
                    int id = Integer.parseInt(parts[0]);
                    String name = parts[1];
                    String email = parts[2];
                    String phone = parts[3];
                    String role = parts[4];

                    if (role.equals("CUSTOMER")) {
                        double balance = Double.parseDouble(parts[5]);
                        String membership = parts[6];
                        Customer user = new Customer(id, name, email, phone, role, membership, balance);
                        users.put(name,user);
                    } else if (role.equals("ADMIN")) {
                        int managedHallId = Integer.parseInt(parts[5]);
                        Admin user = new Admin(id, name, email, phone, role, managedHallId);
                        users.put(name, user);
                    }
                } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                    System.out.println("Skipping invalid data: " + line);
                }
            }
            System.out.println("Users loaded successfully.");
            int totalAdmins = 0;
            int totalCustomers = 0;
            for (User u: users.values()) {
                if (u instanceof Admin){
                    totalAdmins++;
                }else if (u instanceof Customer){
                    totalCustomers ++;
                }
                
            }
            System.out.println("Total Admins: " + totalAdmins);
            System.out.println("Total Customers: " + totalCustomers);

        } catch (FileNotFoundException e) {
            System.out.println("File not found. No users loaded.");
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }  */ 
}

package user;

import exception.UserNotFoundException;
import exception.InvalidCredentialsException;
import exception.UserAlreadyExistsException;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserManager implements Authentication {
    public static Map<String, User> users = new HashMap<>();
    public static int nextId  =1;

    public static int getNextId() {
        return nextId;
    }

    // Setter method for nextId
    public static void setNextId(int nextId) {
        UserManager.nextId = nextId;
    }

    /*
     * public boolean updateUsername(String oldUsername, String newUsername) {
     * if (users.containsKey(newUsername)) {
     * System.out.println("Username already exist.");
     * return false;
     * }
     * 
     * User user = users.remove(oldUsername); // Remove the old entry
     * if (user != null) {
     * user.setUsername(newUsername); // Update the username
     * users.put(newUsername, user); // Add the new entry
     * System.out.println("Username updated successfully.");
     * return true;
     * } else {
     * System.out.println("User not found.");
     * return false;
     * }
     * 
     * }
     */

    @Override
    public User login(String username, String password) throws InvalidCredentialsException{
        if (!users.containsKey(username)) {
            throw new InvalidCredentialsException("User not found!");
        } 

        User user = users.get(username);
        if(!user.verifyPassword(password)) {
            throw new InvalidCredentialsException("Incorrect password");
        }

        System.out.println("Login successfully. Welcome " + username);
        return user;
    }
    

    @Override
    public void logout(String username) throws UserNotFoundException {
        if (users.containsKey(username)) {
            System.out.println("User " + username + " logged out.");
        } else {
            throw new UserNotFoundException("User " + username + " not found.");
        }
    }

    @Override
    public void register(User user) throws UserAlreadyExistsException {
        for (User existingUser : users.values()) {
            // Check if any user already exists with the same username or email
            if (existingUser.getUsername().equals(user.getUsername()) || existingUser.getEmail().equals(user.getEmail())) {
                throw new UserAlreadyExistsException("User with username or email already exists.");
            }
        }

        // Assign a unique ID to the new user
        user.setId(nextId++);
        users.put(user.getUsername(), user);
        System.out.println("User " + user.getUsername() + " registered successfully.");
    }


    public static void writeUsersToFile(String fileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (User user : users.values()) {
                writer.write(user.toString());
                writer.newLine();
            }
            System.out.println("Users saved successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Map<String, User> readUsersFromFile(String filename) {
        users.clear(); // Clear existing users before loading new ones
        int maxId = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] userData = line.split(",");
    
                if (userData.length < 6) { 
                    System.err.println("Skipping invalid entry: " + line);
                    continue; // Skip invalid data
                }

                int id;
                try {
                    id = Integer.parseInt(userData[0]);
                    if (id > maxId) {
                        maxId = id;
                    } // Track highest ID
                } catch (NumberFormatException e) {
                    System.err.println("Invalid ID format, skipping: " + userData[0]);
                    continue;
                }

                String username = userData[1];
                String email = userData[2];
                String hashedPassword = userData[3];
                String phone = userData[4];
                String role = userData[5];
    
                User user = null;
                // If role is ADMIN
                if (role.equals("ADMIN")) {
                    ArrayList<Integer> managedHalls = new ArrayList<>();
                    if (userData.length > 6) {
                        String[] hallsArray = userData[6].split(";");
                        for (String hall : hallsArray) {
                            try {
                                managedHalls.add(Integer.parseInt(hall));
                            } catch (NumberFormatException e) {
                                System.err.println("Skipping invalid hall ID: " + hall);
                            }
                        }
                    }
                    user = new Admin(id, username, email, hashedPassword, phone, managedHalls, true);
                    users.put(user.getUsername(), user);
    
                } else if (role.equals("CUSTOMER")) {
                    if (userData.length < 9) {
                        System.err.println("Skipping incomplete customer entry: " + line);
                        continue;
                    }
    
                    double walletBalance;
                    try {
                        walletBalance = Double.parseDouble(userData[6]);
                    } catch (NumberFormatException e) {
                        System.err.println("Error parsing wallet balance: " + userData[6]);
                        walletBalance = 0.0; // Default to 0 if invalid
                    }
    
                    String membershipLevel = userData[7];
                    String favoriteGenre = userData[8];
    
                    user = new Customer(id, username, email, hashedPassword, phone, walletBalance, membershipLevel, favoriteGenre, true);
                    users.put(user.getUsername(), user);
                }
            }
            UserManager.setNextId(maxId + 1);
            System.out.println("Users loaded successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return users;
    }

    /** Get the users map for operations */
    public static Map<String, User> getUsers() {
        return users;
    }
}

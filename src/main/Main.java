package src.main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import src.cinema.Cinema;
import src.cinema.Hall;
import src.cinema.VIPSeat;
import src.exception.InvalidCredentialsException;
import src.exception.UserAlreadyExistsException;
import src.user.Admin;
import src.user.Customer;
import src.user.User;
import src.user.UserManager;

public class Main{
    public static void main(String[] args){
        /*Cinema cinema = new Cinema("Cooked","Phnom Penh",5);
        cinema.iniHall();
        for (Hall hall : cinema.halls) {
            hall.initSeats();
            for(int i = 0 ; i< Hall.rowsPerHall;i++){
                for(int j = 0 ; j <Hall.seatsPerRow ; j++) {
                    if(hall.seats.get(i).get(j).getSeatType().equals("VIP")){
                        VIPSeat vipSeat = (VIPSeat) hall.seats.get(i).get(j);
                        vipSeat.setService("Elevated Seat");
                        System.out.println(vipSeat);
                    } else {
                        System.out.println(hall.seats.get(i).get(j));
                    }
                    
                }
            }
        }*/
        
        UserManager userManager = new UserManager();
        userManager.readUsersFromFile("users.csv"); 

        // Create Admins
        /*Admin admin1 = new Admin(0, "admin1", "admin1@example.com", "adminPassword", "0123456789", new ArrayList<>(), false);
        Admin admin2 = new Admin(0, "admin2", "admin2@example.com", "adminPassword", "0123456790", new ArrayList<>(), false);

        // Create Customers
        Customer customer1 = new Customer(0, "customer1", "customer1@example.com", "customerPassword", "0123456781", 100.0, "Gold", "Action", false);
        Customer customer2 = new Customer(0, "customer2", "customer2@example.com", "customerPassword", "0123456782", 200.0, "Silver", "Drama", false);
        Customer customer3 = new Customer(0, "customer3", "customer3@example.com", "customerPassword", "0123456783", 300.0, "Bronze", "Comedy", false);
        Customer customer4 = new Customer(0, "customer4", "customer4@example.com", "customerPassword", "0123456784", 400.0, "Gold", "Action", false);
        Customer customer5 = new Customer(0, "customer5", "customer5@example.com", "customerPassword", "0123456785", 500.0, "Silver", "Drama", false);

        try {
            // Register Admins and Customers
            userManager.register(admin1); // Register Admin 1
            userManager.register(admin2); // Register Admin 2

            userManager.register(customer1); // Register Customer 1
            userManager.register(customer2); // Register Customer 2
            userManager.register(customer3); // Register Customer 3
            userManager.register(customer4); // Register Customer 4
            userManager.register(customer5); // Register Customer 5

            // Display all users
            System.out.println("Displaying all users:");
            for (User user : UserManager.getUsers().values()) {
                System.out.println(user);
            }

            userManager.writeUsersToFile("users.csv");
        

            // Test login with a valid username and password
            try {
                System.out.println("\nAttempting to login as 'customer1' with correct password:");
                User loggedInUser = userManager.login("customer1", "customerPassword");
                System.out.println("Login successful! Welcome " + loggedInUser.getUsername());

                // Attempting to login with incorrect password
                System.out.println("\nAttempting to login as 'customer1' with incorrect password:");
                userManager.login("customer1", "wrongPassword"); // This should throw an InvalidCredentialsException
            } catch (InvalidCredentialsException e) {
                System.out.println("Login failed: " + e.getMessage());
            }
        } catch (UserAlreadyExistsException e) {
            System.out.println("Error: " + e.getMessage());
        }*/

        Customer customer5 = new Customer(0, "customer5", "customer5@example.com", "customerPassword", "0123456785", 500.0, "Silver", "Drama", false);
        //Customer customer8 = new Customer(0, "customer8", "customer8@example.com", "customer8Password", "098723452", 500.0, "Silver", "Drama", false);

        try{
            userManager.register(customer5);
            //userManager.register(customer8);
        } catch (UserAlreadyExistsException e) {
            System.out.println(e.getMessage());
        }
        //userManager.writeUsersToFile("users.csv");



    }
}
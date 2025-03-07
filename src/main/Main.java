package src.main;

import java.time.LocalDate;
import java.util.*;

import src.cinema.*;
import src.exception.DuplicateUserException;
import src.exception.InvalidCredentialsException;
import src.exception.InvalidUserDetailsException;
import src.exception.UserNotFoundException;
import src.user.Admin;
import src.user.Customer;
import src.user.User;
import src.user.UserManager;

public class Main{
    public static void main(String[] args){
       /*  int choice = -1;
        while(choice != 0)
        {
            
        }
        Cinema cinema = new Cinema("Legend","TK",5);
        cinema.iniHall();
        cinema.addShowTime();
        cinema.showHalls();
        cinema.saveData("src/main/Data/Cinema_Data.txt"); */

        UserManager u = new UserManager();
        try {
            u.register("johnDoe", "securePass123", "john@example.com", "098765432", 100.0, "Gold");
            u.register("johnDoe", "securePass12", "joh@example.com", "098765432", 100.0, "Gold");
            u.register("aliceSmith", "strongPass456", "alice@example.com", "098", 50.0, "Silver");
            u.register("bobJones", "pass7890secure", "bob@example.com", "012345678", 200.0, "Platinum");
        } catch (InvalidUserDetailsException | DuplicateUserException e) {
            System.out.println("Registration failed: " + e.getMessage());
        }

        u.displayAllUsers();
        try {
            User user = u.login("johnDoe", "securePass123");
            System.out.println("Welcome, " + user.getUsername());
        } catch (UserNotFoundException | InvalidCredentialsException e) {
            System.out.println("Login failed: " + e.getMessage());
        }

        u.displayAllUsers();

    }
}
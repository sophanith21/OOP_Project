package src.main;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.*;

import src.cinema.Cinema;
import src.cinema.Hall;
import src.cinema.Seat;
import src.user.Admin;
import src.user.Customer;
import src.user.User;
import src.user.UserManager;

public class Main{
    public static void main(String[] args){
        int choice = -1;
        // while(choice != 0)
        // {
            
        // }
        Cinema cinema = new Cinema("src/main/Data/Cinema.csv");
        cinema.saveData("src/main/Test/Cinema.csv");
        cinema.iniHall(Hall.loadAll("src/main/Data/Halls.csv"));
        Hall.saveAll("src/main/Test/Halls.csv", cinema.halls);
        
        for(Hall hall : cinema.halls){
            hall.iniSeats(Seat.loadAll("src/main/Data/Seats.csv"));
            Seat.saveAll("src/main/Test/Seats.csv", hall.seats);
        }
        

    }
}
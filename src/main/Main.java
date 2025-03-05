package src.main;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.*;

import src.cinema.Cinema;
import src.cinema.Hall;
import src.user.Admin;
import src.user.Customer;
import src.user.User;
import src.user.UserManager;

public class Main{
    public static void main(String[] args){
        int choice = -1;
        while(choice != 0)
        {
            
        }
        Cinema cinema = new Cinema("Legend","TK",5);
        cinema.iniHall();
        cinema.saveData("src/main/Data/Cinema_Data.txt");

    }
}
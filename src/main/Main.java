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
        Cinema cinema = new Cinema("Legend","TK",5);
        cinema.iniHall();
        ArrayList<Hall> halls = cinema.getHalls(); 
        
       UserManager userManager = new UserManager();
       Scanner scanner = new Scanner (System.in);
    
       userManager.register("monica", "email@gamail.com", "dfAjdhfisu67$", "098765432");
       userManager.register("nica", "nica@gamail.com", "dfAjdhfisu67@", "0987654321");
       userManager.register("nicaaa", "nicaaa@gamail.com", "adfAjdhfisu67@", "0987654322");
       userManager.register("kim", "kim@gamail.com", "df", "012345678");

       userManager.displayAllUsers();
       scanner.close(); 

       System.out.println();
       
    }
}
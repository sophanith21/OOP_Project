package src.main;

import java.util.ArrayList;

import src.cinema.Cinema;
import src.cinema.Hall;
import src.user.Admin;
import src.user.Customer;
import src.user.User;

public class Main{
    public static void main(String[] args){
        Cinema cinema = new Cinema("Legend","TK",5);
        cinema.iniHall();
        ArrayList<Hall> halls = cinema.getHalls();
        System.out.println(halls.get(0).getSeats());
    }

}
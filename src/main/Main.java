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
        cinema.addShowTime();
        cinema.showHalls();

    }
}
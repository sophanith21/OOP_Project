package src.main;

import java.util.ArrayList;

import src.cinema.Cinema;
import src.cinema.Hall;

public class Main{
    public static void main(String[] args){
        Cinema cinema = new Cinema("Legend","TK",5);
        cinema.iniHall();
        ArrayList<Hall> halls = cinema.getHalls(); 
    }

}
package src.main;

import src.cinema.Cinema;
import src.cinema.Hall;
import src.cinema.Seat;

public class FileIOTest{
    public static void saveData(){
        Cinema cinema = new Cinema("Legend","Phnom Penh",5);
        cinema.saveData("src/main/Data/Cinema.csv");
        cinema.iniHall();
        Hall.saveAll("src/main/Data/Halls.csv", cinema.halls);
        
        for(Hall hall : cinema.halls){
            Seat.saveAll("src/main/Data/Seats.csv", hall.seats);
        }
    }

    public static void loadData(){
        Cinema cinema = new Cinema("src/main/Data/Cinema.csv");
        cinema.saveData("src/main/Test/Cinema.csv");
        cinema.iniHall(Hall.loadAll("src/main/Data/Halls.csv"));
        Hall.saveAll("src/main/Test/Halls.csv", cinema.halls);
        
        for(Hall hall : cinema.halls){
            hall.initSeats(Seat.loadAll("src/main/Data/Seats.csv"));
            Seat.saveAll("src/main/Test/Seats.csv", hall.seats);
        }
    }
    public static void main(String[] args){
        saveData();
        loadData();
    }
}
package src.main;

import src.cinema.Cinema;
import src.cinema.Hall;
import src.cinema.VIPSeat;

public class Main{
    public static void main(String[] args){
        Cinema cinema = new Cinema("Cooked","Phnom Penh",5);
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
        }

    }
}
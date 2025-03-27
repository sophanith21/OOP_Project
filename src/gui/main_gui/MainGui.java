package gui.main_gui;

import cinema.Cinema;
import cinema.Hall;
import cinema.Seat;
import gui.helper_gui.HomeUi;
public class MainGui {
    public MainGui () {
        Cinema cinema = new Cinema();
        cinema.loadData();
        // //cinema.iniHall();
        // //Hall.saveAll(cinema.halls);
        // for (Hall hall:cinema.halls){
        //     //hall.initSeats();
        //     //Seat.saveAll(hall.seats);
            
        // }
        new HomeUi(cinema);
    }
}

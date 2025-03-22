package gui.main_gui;

import cinema.Cinema;
import gui.*;
import gui.helper_gui.HomeUi;
import gui.helper_gui.LoginUI;
public class MainGui {
    public MainGui () {
        Cinema cinema = new Cinema("Cineplex","Phnom Penh",5);
        cinema.loadData();
        new HomeUi(cinema);
    }
}

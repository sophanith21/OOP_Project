package src.user;
import src.cinema.Movie;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;
public class Admin extends User{

    public Admin(int id, String username, String email, String phone, String password) {
        super(id, username, email, phone, password, "ADMIN"); // Role is set to "ADMIN"
    }

    @Override
    public void displayUser() {
        super.displayUser();
        System.out.println("--------------------------------------");
    }



}
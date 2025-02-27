package src.user;
import src.cinema.Movie;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;
public class Admin extends User{

    private String adminCode;

    public Admin(String username, String password, String email, String fullName, 
                 String phoneNumber,String adminCode, String position) {
        super(userId, username, password, email,phoneNumber,"ADMIN");
        this.adminCode = adminCode;
    }

    @Override
    public String toString() {
        return super.toString() + ", Admin{" +
                "adminCode='" + adminCode + '\'' +
                '}';
    }

    
}
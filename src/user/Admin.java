package src.user;
import src.cinema.Movie;

import java.beans.Customizer;
import java.util.ArrayList;
import java.util.Objects;
public class Admin extends User{

    public static int totalAdmins = 0;
    private String id;
    private String role = "Admin";
    private ArrayList <Movie> movies; // Movies managed by the admin

    public Admin(String name, String phone, String email, String password) {
        
        super(name, phone, email, password);
        this.id = "A" + (++totalAdmins); 
        this.movies = new ArrayList<>();
    }

    public Admin () {
        super();
        //this.movies = new ArrayList<>();
    }

    public String getID () {
        return id;
    }

    public String getRole () {
        return role;
    }

    public ArrayList<Movie> getMovies() {
        return new ArrayList<>(movies);
    }

    public static int getTotalAdmins () {
        return totalAdmins;
    }

    @Override
    public void register () {
        this.id = "A" + (++totalAdmins);
        super.register(); 
    }

    @Override
    public String toString() {
        return "Admin [userName=" + userName + ", id=" + id + ", userPhoneNumber=" + userPhoneNumber + ", role=" + role
                + ", userEmail=" + userEmail + ", isLoggedIn=" + isLoggedIn + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((role == null) ? 0 : role.hashCode());
        result = prime * result + ((movies == null) ? 0 : movies.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        if (getClass() != obj.getClass())
            return false;
        Admin other = (Admin) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (role == null) {
            if (other.role != null)
                return false;
        } else if (!role.equals(other.role))
            return false;
        if (movies == null) {
            if (other.movies != null)
                return false;
        } else if (!movies.equals(other.movies))
            return false;
        return true;
    }
}
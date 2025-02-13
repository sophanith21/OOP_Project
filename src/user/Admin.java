package src.user;
import src.cinema.Movie;
import java.util.ArrayList;
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
        this.movies = new ArrayList<>();
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
    public String toString() {
        return "Admin{" +
                "adminID='" + id + '\'' +
                ", adminName='" + userName + '\'' +
                ", isLoggedIn=" + isLoggedIn +
                ", numberOfMovies=" + movies.size() +
                '}';
    }

    @Override
    public void register () {
        this.id = "A" + (++totalAdmins);
        super.register(); 
    }
}
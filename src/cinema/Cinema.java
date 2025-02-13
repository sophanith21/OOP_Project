package src.cinema;
import java.util.ArrayList;

public class Cinema { 
    private final String name;
    private final String location;
    private final int totalHalls;
    ArrayList<Hall> halls;
    public Cinema(String name, String location, int totalHalls) {
        this.name = name;
        this.location = location;
        this.totalHalls = totalHalls;
        halls = new ArrayList<Hall>(totalHalls);
    }

    public void iniHall()
    {
        for(int i = 0 ; i < totalHalls ; i++ ){
            Hall newHall = new Hall();
            halls.add(newHall);
        }
    }

    public ArrayList<Hall> getHalls(){
        return halls;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public int getTotalHalls() {
        return totalHalls;
    }

    @Override
    public String toString() {
        return "Cinema [name=" + name + ", location=" + location + ", totalHalls=" + totalHalls + "]";
    }
}

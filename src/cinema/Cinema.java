package src.cinema;
import java.util.ArrayList;

public class Cinema { 
    public String name;
    public String location;
    public int totalHalls;
    public Cinema(String name, String location, int totalHalls) {
        this.name = name;
        this.location = location;
        this.totalHalls = totalHalls;
    }

    public void iniHall(ArrayList<Hall> halls)
    {
        if(halls.isEmpty())
        {
            for(int i = 0 ; i < totalHalls ; i++ ){
                Hall newHall = new Hall();
                halls.add(newHall);
            }
        }
        
    }

    @Override
    public String toString() {
        return "Cinema [name=" + name + ", location=" + location + ", totalHalls=" + totalHalls + "]";
    }

    
}

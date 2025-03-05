package src.cinema;
import java.util.ArrayList;

public class Cinema { 
    public String name;
    public String location;
    public int totalHalls;
    public ArrayList <Hall> halls;
    public Cinema(String name, String location, int totalHalls) {
        if(totalHalls < 1){
            throw new IllegalArgumentException("Total hall must be 1 or more"); //Should be handle in main (object creation)
        }
        halls = new ArrayList<>();
        this.name = name;
        this.location = location;
        this.totalHalls = totalHalls;
    }

    public void iniHall()
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
        return "Cinema [name=" + name + ", location=" + location + ", totalHalls=" + totalHalls + ", halls=" + halls
                + "]";
    }

    
}

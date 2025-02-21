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
        if(halls.isEmpty())
        {
            for(int i = 0 ; i < totalHalls ; i++ ){
                Hall newHall = new Hall();
                halls.add(newHall);
            }
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
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((location == null) ? 0 : location.hashCode());
        result = prime * result + totalHalls;
        result = prime * result + ((halls == null) ? 0 : halls.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Cinema other = (Cinema) obj;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (location == null) {
            if (other.location != null)
                return false;
        } else if (!location.equals(other.location))
            return false;
        if (totalHalls != other.totalHalls)
            return false;
        if (halls == null) {
            if (other.halls != null)
                return false;
        } else if (!halls.equals(other.halls))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Cinema [name=" + name + ", location=" + location + ", totalHalls=" + totalHalls + "]";
    }
}

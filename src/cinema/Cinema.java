package src.cinema;
import java.util.ArrayList;
import java.util.Scanner;

public class Cinema { 
    private String name;
    private String location;
    private int totalHalls;
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

    public void addShowTime()
    {
        Scanner scan = new Scanner(System.in);
        System.out.print("Hall ID to add showTime: ");
        String hallId = scan.nextLine();
        System.out.println();
        halls.get(Integer.parseInt(hallId)).addShowTime(scan);
    }

    public void showHalls() {
        for(int i = 0; i<halls.size();i++) {
            halls.get(i).showDetails();
        }
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

    

    public void setName(String name) {
        this.name = name;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setTotalHalls(int totalHalls) {
        this.totalHalls = totalHalls;
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

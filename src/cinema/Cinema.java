package src.cinema;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import src.booking.Booking;
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

    public void saveData(String fileName) {
        try {
            BufferedWriter writer = new BufferedWriter(
                new FileWriter(fileName)
            );
            writer.write("Cinema: " + name + "," + location + "," + totalHalls + "\n");
            for (Hall hall : halls){
                writer.write("Hall: " + hall.getHallId() + ","+ hall.getStatus() + "\n");
                for(int i = 0; i < Hall.rowsPerHall; i++){
                    for(int j = 0; j < Hall.seatsPerRow; j++){
                        writer.write(hall.seats.get(i).get(j).toString() + "\n");
                        if(!hall.seats.get(i).get(j).booked.isEmpty()){
                            for(Booking book: hall.seats.get(i).get(j).booked){
                                writer.write(book.toString());
                            }
                        }
                    }
                }
                for(ShowTime show : hall.showTimes) {
                    writer.write(show.toString());
                }
            }
            writer.close();
            System.out.println("Cinema data saved successfully");
        } catch (IOException e) {
            System.out.println("An error occured");
            e.printStackTrace();
        }
        
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

package src.cinema;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import src.DataControl.DataPersistence;

public class Cinema implements DataPersistence{
    public String name;
    public String location;
    public int totalHalls;
    public ArrayList <Hall> halls;

    public Cinema(String fileName) { //For loading data in object creation
        loadData(fileName);
    }
    public Cinema(String name, String location, int totalHalls) {
        if(totalHalls < 1){
            throw new IllegalArgumentException("Total hall must be 1 or more"); //Should be handle in main (object creation)
        }
        halls = new ArrayList<>();
        this.name = name;
        this.location = location;
        this.totalHalls = totalHalls;
    }
    @Override
    public void saveData(String fileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))){
            writer.write("name,location,totalHalls\n");
            writer.write(name + "," + location + "," + totalHalls);
            System.out.println("Cinema data saved successfully");
        } catch (IOException e) {
            System.out.println("An error occured");
            e.printStackTrace();
        }
        
    }

    @Override
    public void loadData(String fileName){
        try (BufferedReader reader = new BufferedReader (new FileReader(fileName))){ 
            String line;
            reader.readLine();
            line = reader.readLine();
            ArrayList <String> data = new ArrayList<>(Arrays.asList(line.split(",")));
            this.name = data.get(0);
            this.location = data.get(1);
            this.totalHalls = Integer.parseInt(data.get(2));
        } catch (IOException e){
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

    public void iniHall(ArrayList<Hall> halls){
        this.halls = halls;
    }

    @Override
    public String toString() {
        return "Cinema [name=" + name + ", location=" + location + ", totalHalls=" + totalHalls + ", halls=" + halls
                + "]";
    }

    
}

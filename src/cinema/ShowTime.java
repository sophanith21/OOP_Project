package src.cinema;

import java.util.ArrayList;
import java.util.Scanner;

public class ShowTime {
    private String showTimeId;
    private String startTime;
    private String endTime;
    private ArrayList<ArrayList<Seat>> seats = new ArrayList<>();
    private Movie movie;
    public static int rowsPerHall = 10;
    public static int seatsPerRow = 20;

    public ShowTime( int hallId) {
        this.showTimeId = showTimeId;
        this.startTime = startTime;
        this.endTime = endTime;

        for(int i = 0; i < rowsPerHall; i++){

            seats.add(new ArrayList<>());

            for(int j = 0; j < seatsPerRow; j++){
                if((i >= 3 && i <= 6) && (j >= 8 && j <= 11)){   
                    seats.get(i).add(new VIPSeat(hallId, i+1, j+1, false, false));
                } else {
                    seats.get(i).add(new Seat(hallId,i+1,j+1));
                }
            }
        }
    }

    public ShowTime(){
        for(int i = 0; i < rowsPerHall; i++){

            seats.add(new ArrayList<>());

            for(int j = 0; j < seatsPerRow; j++){
                if((i >= 3 && i <= 6) && (j >= 8 && j <= 11)){   
                    seats.get(i).add(new VIPSeat(hallId, i+1, j+1, false, false));
                } else {
                    seats.get(i).add(new Seat(hallId,i+1,j+1));
                }
            }
        }
    }

    private void setShowTimeId(String showTimeId) { this.showTimeId = showTimeId; }
    private void setStartTime(String startTime) { this.startTime = startTime; }
    private void setEndTime(String endTime) { this.endTime = endTime; }
    private Movie getMovies(){ return movie;}


    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((showTimeId == null) ? 0 : showTimeId.hashCode());
        result = prime * result + ((startTime == null) ? 0 : startTime.hashCode());
        result = prime * result + ((endTime == null) ? 0 : endTime.hashCode());
        result = prime * result + ((movie == null) ? 0 : movie.hashCode());
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
        ShowTime other = (ShowTime) obj;
        if (showTimeId == null) {
            if (other.showTimeId != null)
                return false;
        } else if (!showTimeId.equals(other.showTimeId))
            return false;
        if (startTime == null) {
            if (other.startTime != null)
                return false;
        } else if (!startTime.equals(other.startTime))
            return false;
        if (endTime == null) {
            if (other.endTime != null)
                return false;
        } else if (!endTime.equals(other.endTime))
            return false;
        if (movie == null) {
            if (other.movie != null)
                return false;
        } else if (!movie.equals(other.movie))
            return false;
        return true;
    }
    public void setShowTime(){
        Scanner scan = new Scanner(System.in);
        while(true){
            System.out.println("Do you want to add show time?(yes/no): ");
            String choose = scan.nextLine();
            if(choose.equals("no")){break;}
            System.out.print("Set show time id: ");
            String show = scan.nextLine();            
            System.out.print("Set start time: ");
            String start = scan.nextLine();
            System.out.print("Set end time: ");
            String end = scan.nextLine();

            setStartTime(start);
            setEndTime(end);
            setShowTimeId(show);
            
            System.out.println("Add movie title: ");
            String title = scan.nextLine();
            System.out.println("Add movie's duration: ");
            int duration = scan.nextInt();
            scan.nextLine();
            System.out.println("Add genre: ");
            String genre = scan.nextLine();

            Movie movie = new Movie(title, duration, genre);
            System.out.println("Movie has been added: " + movie.getTitle());
            System.out.println("Show time has been set successfully!");
            scan.close();
        }
    }

    @Override
    public String toString() {
        return "Show Time ID: " + getShowTimeId() + "\n"
                + "Start Time: " + getStartTime() + "\n"
                + "End Time: " + getEndTime() + "\n"
                + "Hall ID: " + getHallId() + "\n"
                + "Movies of halls:\n"
                + "\nTitle: " + getMovie().getTitle() + "\n"
                + "Duration: " + getMovie().getDuration() + " minutes\n"
                + "Genre: " + getMovie().getGenre() + "\n";
    }
    

}

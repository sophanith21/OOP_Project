package src.cinema;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import src.DataControl.DataPersistence;
public class Movie implements DataPersistence {
    
    public static int numberOfMovies = 0;
    private String movieID;
    private String title;
    private int durationMinutes;
    private String genre;

    public Movie (String title, int durationMinutes, String genre) {

        this.movieID = "M" + (++numberOfMovies);
        this.title = title;
        this.durationMinutes = durationMinutes;
        this.genre = genre;  
    }

    public String getTitle() { return title; }
    public String getGenre() { return genre; }
    public int getDurationMinutes() { return durationMinutes; }
    public String getMovieID () { return movieID; }
    public static int getTotalMovies() { return numberOfMovies; }

    @Override
    public boolean equals(Object obj) {
        
        Movie other = (Movie) obj; 
        return movieID.equals(other.movieID);  
    }

    @Override
    public String toString() {
        return "Movie [movieID=" + movieID + ", title=" + title + ", duration=" + durationMinutes + ", genre=" + genre + "]";
    }

    @Override
    public void saveData(String fileName) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'saveData'");
    }

    @Override
    public void loadData(String fileName) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'loadData'");
    }

    public static void saveAll (List <Movie> movies, String fileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (Movie movie : movies) {
                writer.write(movie.getMovieID() + "," +
                             movie.getTitle() + "," +
                             movie.getDurationMinutes() + "," +
                             movie.getGenre());
                writer.newLine();
            }
            System.out.println("Data saved to " + fileName);
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Error while saving data to " + fileName);
        }
    }

    public static List<Movie> loadAll(String fileName) {
        List<Movie> movies = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] movieData = line.split(",");
                if (movieData.length == 4) {
                    String movieID = movieData[0];
                    String title = movieData[1];
                    int durationMinutes = Integer.parseInt(movieData[2]);
                    String genre = movieData[3];
                    Movie movie = new Movie(title, durationMinutes, genre);
                    
                    movie.movieID = movieID;
                    movies.add(movie);
                }
            }
            System.out.println("Data loaded from " + fileName);
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Error while loading data from " + fileName);
        }
        return movies;
    }
};

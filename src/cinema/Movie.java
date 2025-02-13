
import java.util.ArrayList;
public class Movie {
    
    public static int numberOfMovies = 0;
    private String movieID;
    private String title;
    private int duration;
    private String genre;
    private ArrayList<Double> ratings;
    private ArrayList <ShowTime> showTimes;

    public Movie (String title, int duration, String genre) {

        this.movieID = "M" + (++numberOfMovies);
        this.title = title;
        this.duration = duration;
        this.genre = genre;  
        this.ratings = new ArrayList<>(); // means no rating
        this.showTimes = new ArrayList<>();
    }

    public String getMovieID () {
        return movieID;
    }

    public String getTitle () {
        return title;
    }

    public int getDuration () {
        return duration;
    }

    public String getGenre () {
        return genre;
    }

    public double getRating() {
        if (ratings.isEmpty()) {
            return -1.0; // No rating yet
        }
        double sum = 0;
        for (double rating : ratings) {
            sum += rating;
        }
        return sum / ratings.size();
    }

    public ArrayList <ShowTime> getShowTime () {
        return new ArrayList<>(showTimes);
    }

    public static int getTotalMovies() {
        return numberOfMovies;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "ID='" + movieID + '\'' +
                ", Title='" + title + '\'' +
                ", Genre='" + genre + '\'' +
                ", Duration=" + duration +
                " minutes, Average Rating=" + (ratings.isEmpty() ? "Not Yet Rated" : getRating()) +
                ", Total Showtimes=" + showTimes.size() +
                '}';
    }

};

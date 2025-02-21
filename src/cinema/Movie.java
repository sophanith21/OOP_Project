package src.cinema;

//import java.util.Set;
import java.util.ArrayList;
import java.util.HashSet;
public class Movie {
    
    public static int numberOfMovies = 0;
    private String movieID;
    private String title;
    private int duration;
    private String genre;


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

    /*public HashSet <ShowTime> getShowTime () {
        return new HashSet<>(showTimes);
    }*/

    public static int getTotalMovies() {
        return numberOfMovies;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((movieID == null) ? 0 : movieID.hashCode());
        result = prime * result + ((title == null) ? 0 : title.hashCode());
        result = prime * result + duration;
        result = prime * result + ((genre == null) ? 0 : genre.hashCode());
        //result = prime * result + ((showTimes == null) ? 0 : showTimes.hashCode());
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
        Movie other = (Movie) obj;
        if (movieID == null) {
            if (other.movieID != null)
                return false;
        } else if (!movieID.equals(other.movieID))
            return false;
        if (title == null) {
            if (other.title != null)
                return false;
        } else if (!title.equals(other.title))
            return false;
        if (duration != other.duration)
            return false;
        if (genre == null) {
            if (other.genre != null)
                return false;
        } else if (!genre.equals(other.genre))
            return false;
        /*if (showTimes == null) {
            if (other.showTimes != null)
                return false;
        } else if (!showTimes.equals(other.showTimes))
            return false;
        return true;*/
    }

    @Override
    public String toString() {
        return "Movie [movieID=" + movieID + ", title=" + title + ", duration=" + duration + ", genre=" + genre +"]";
    }
};

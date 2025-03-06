package src.cinema;

import java.util.ArrayList;
import java.util.HashSet;
public class Movie {
    
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
        if (durationMinutes != other.durationMinutes)
            return false;
        if (genre == null) {
            if (other.genre != null)
                return false;
        } else if (!genre.equals(other.genre))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Movie [movieID=" + movieID + ", title=" + title + ", duration=" + durationMinutes + ", genre=" + genre + "]";
    }
};

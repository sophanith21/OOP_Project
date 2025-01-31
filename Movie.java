public class Movie {   
    private String movieID;
    private String hallID;
    private String title;
    private int duration;
    private String genre;
    private double rating;
    private ShowTime[] showTimes;
    private int showTimeCount;
    public static int numberOfMovies = 0;

    Movie (String movieID, String hallID, String title, int duration, String genre, int maxShowTimes) {
        this.movieID = movieID;
        this.hallID = hallID;
        this.title = title;
        this.duration = duration;
        this.genre = genre;
        this.rating = 0.0;
        this.showTimes = new ShowTime[maxShowTimes];
        this.showTimeCount = 0;
        
        numberOfMovies ++;
    } 
};

public class Cinema {
    private String name;
    private String location;
    private int totalHalls;
    private int numberOfMovies;
    private Hall[] halls;
    public Cinema(String name, String location, int totalHalls,int numberOfMovies, Hall[] halls) {
        this.name = name;
        this.location = location;
        this.totalHalls = totalHalls;
        this.numberOfMovies = numberOfMovies;
        this.halls = halls;
    }
}

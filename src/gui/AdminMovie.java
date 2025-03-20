package gui;

public class AdminMovie extends CustomerMovie {
    public AdminMovie() {
        addMovie.setEnabled(false);
    }
    public static void main(String[] args) {
        new AdminMovie();
    }

}

public class User {

    private String userID;
    private String email;
    private String password;
    private boolean isLoggedIn;
    private String resetCode = null;

    public User (String userID, String password, String email) {
        this.userID = userID;
        this.password = password;
        this.email = email;
        this.isLoggedIn = false;
    }
}
public class User {

    private String userID;
    private String username;
    private String password;
    private String email;
    private String phoneNumber;

    User (String userID, String username, String password, String email, String phoneNumber) {
        this.userID = userID;
        this.username = username;
        this.password = password;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }
}
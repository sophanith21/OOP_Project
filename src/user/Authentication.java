package src.user;
public interface Authentication {
    boolean login(String username, String password);
    void logout();
} 

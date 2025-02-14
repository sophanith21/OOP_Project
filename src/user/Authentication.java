package src.user;
interface Authentication {
    boolean login(String username, String password);
    void logout();
    void register();
} 

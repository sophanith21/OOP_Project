package src.user;

import java.util.HashMap;

interface Authentication {
    boolean login(String username, String password);
    void logout ();
    boolean register (String name, String phone, String email, String password);
} 

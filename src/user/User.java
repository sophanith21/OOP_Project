package user;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.mindrot.jbcrypt.BCrypt;

import DataControl.DataPersistence;
import DBConnection.DBConnection;
abstract public class User implements DataPersistence{
    protected int id;
    protected String username;
    protected String hashedPassword;
    protected String email;
    protected String phone;
    protected String role; // "CUSTOMER" or "ADMIN"

    public User(String username, String email, String hashedPassword, String phone, String role, boolean isHashed) {
        this.id = 0;
        this.username = username;
        this.email = email;
        this.phone = phone;
        this.role = role;

        if (isHashed) { 
            this.hashedPassword = hashedPassword;  // Use the existing hashed password from CSV
        } else { 
            this.hashedPassword = BCrypt.hashpw(hashedPassword, BCrypt.gensalt()); // Hash only if it's a new user
        }
    }  
    

    public String getRole() {
        return role;
    }


    public void setRole(String role) {
        this.role = role;
    }


    public String getPassword(){
        return hashedPassword;
    }
    
    public int getId () {
        return id;
    }

    public void setId (int id) {
        if (this.id == 0) {
            this.id = id;
        }
    }
    
    public String getUsername () {
        return username;
    }

    public void setUsername (String username) {
        if(username != null && !username.trim().isEmpty()) {
            this.username = username;
        }
    }

    public String getEmail () {
        return email;
    }

    public void setEmail(String email) {
        if (email != null && email.matches("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")) {
            this.email = email;
        } else {
            throw new IllegalArgumentException("Invalid email format.");
        }
    }

    public String getPhone () {
        return phone;
    }

    public void setPhone(String phone) {
        if (phone != null && phone.matches("^0\\d{8,9}$")) {
            this.phone = phone;
        } else {
            throw new IllegalArgumentException("Phone number must start with '0' and contain 9-10 digits.");
        }
    }

    public boolean verifyPassword(String inputPassword) {
        return BCrypt.checkpw(inputPassword, this.hashedPassword);
    }

    @Override
    public void loadData(){
        throw new UnsupportedOperationException ("Do not use it");
    }

    @Override
    public void saveData(){
        throw new UnsupportedOperationException ("Do not use it");
    }

    public static int getLastIdFromDB(){
        try {
            Connection conn = DBConnection.getConnection();
            System.out.println(conn);
            if (conn != null) {
                String query = "SELECT COUNT(*) as lastId FROM user";
                PreparedStatement stmt = conn.prepareStatement(query);
                ResultSet set = stmt.executeQuery();
                int id = 0;
                while (set.next()){
                    id = set.getInt("lastId");
                }
                
                conn.close();
                return id;
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
    
    /*
     * public void updateProfile () {}
     * public void changePassword () {}
     * public void viewProfile () {}
     */

    @Override
    public String toString() {
        return id + "," + username + "," + email + "," + hashedPassword + "," + phone + "," + role;
    }

    @Override
    public boolean equals(Object obj) {

        User other = (User) obj;
        return username.equalsIgnoreCase(other.username) || email.equalsIgnoreCase(other.email)
                || phone.equals(other.phone);

    }
}

package src.user;
import src.cinema.Movie;

import java.util.ArrayList;
import java.util.Objects;
public class Admin extends User{

    private String id;
    private String role = "Admin";

    public Admin(String name, String phone, String email, String password, String id) {
        
        super(name, phone, email, password);
        this.id = id; 
    }

    @Override
    public String getUserID() {
        return id;
    }

    public String getRole () {
        return role;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((role == null) ? 0 : role.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        if (getClass() != obj.getClass())
            return false;
        Admin other = (Admin) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (role == null) {
            if (other.role != null)
                return false;
        } else if (!role.equals(other.role))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Admin [ID=" + id + ", Name=" + userName + ", PhoneNumber=" + userPhoneNumber
                + ", Email=" + userEmail + ", isLoggedIn=" + isLoggedIn + "]";
    }

    

    
}
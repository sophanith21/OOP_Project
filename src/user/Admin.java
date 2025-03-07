package src.user;
public class Admin extends User{ 
    private int managedHallId;  

    public Admin(int id, String username, String email, String phone, String password, int managedHallId) {
        super(id, username, email, phone, password, "ADMIN"); // Role is set to "ADMIN"
        this.managedHallId = managedHallId;
    }

    @Override
    public String getUserRole() {
        return role;
    }
    public int getManagedHallId () { return managedHallId; }
    public void setManagedHallId(int managedHallId) {
        this.managedHallId = managedHallId;
    }
    
    
    @Override
    public boolean equals(Object obj) {
       
        if (!super.equals(obj))
            return false;
        Admin other = (Admin) obj;
        if (managedHallId != other.managedHallId)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return super.toString() + "," + managedHallId;
    }
}
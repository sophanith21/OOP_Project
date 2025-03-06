package src.user;
public class Admin extends User{ 
    private int managedHallId;  

    public Admin(int id, String username, String email, String phone, String password, int managedHallId) {
        super(id, username, email, phone, password, "ADMIN"); // Role is set to "ADMIN"
        this.managedHallId = managedHallId;
    }

    public int getManagedHallId () { return managedHallId; }
    public void setManagedHallId(int managedHallId) {
        this.managedHallId = managedHallId;
    }
    

    @Override
    public String toString() {
        return super.toString() + "," + managedHallId;
    }
 
}
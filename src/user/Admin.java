package src.user;

import java.util.ArrayList;
import java.util.List;

public class Admin extends User {
    private List<Integer> managedHalls; // ad admin can manage multiple halls

    public Admin(int id, String username, String email, String password, String phone, List<Integer> managedHalls, boolean isHashed) {
        super(username, email, password, phone, "ADMIN", isHashed);
        this.managedHalls = managedHalls;
    }

    public List<Integer> getManagedHalls() {
        return new ArrayList<>(managedHalls); // Return a copy to prevent external modification
    }

    public void setManagedHalls(List<Integer> managedHalls) {
        if (managedHalls != null) {
            this.managedHalls = new ArrayList<>(managedHalls); // Store a copy to prevent external modification
        } else {
            throw new IllegalArgumentException("Managed halls list cannot be null.");
        }
    }

    public void displayAdminMenu() {
        System.out.println("Admin Menu:");
        System.out.println("1. Manage Halls");
        System.out.println("2. View Reports");
        System.out.println("3. Edit User Accounts");
        System.out.println("4. Log Out");
    }

    @Override
    public String toString() {
        return super.toString() + "," + managedHalls.toString().replaceAll("[\\[\\] ]", "");
    }
}
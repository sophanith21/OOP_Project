package src.user;

import java.util.HashMap;

public class UserManager implements Authentication {
    private static HashMap <String, User> users = new HashMap<>();
    private static int totalCustomers = 0;
    private static int totalAdmins = 0;
    
        // Adding an Admin (Usually done by another Admin)
        public static boolean addAdmin(String name, String phone, String email, String password) {
            if (users.containsKey(email)) {
                System.out.println("Admin with this email already exists.");
                return false;
            }
    
            String adminId = "A" + (++totalAdmins);
    
            // Create and add Admin to HashMap
            Admin newAdmin = new Admin(name, phone, email, password, adminId);
            users.put(email, newAdmin);
    
            System.out.println("Admin added successfully! Admin ID: " + adminId);
            return true;
        }
    
        public static boolean registerCustomer(String name, String phone, String email, String password) {
            if (users.containsKey(email)) {
            System.out.println("A customer with this email already exists.");
            return false; // Customer already registered with this email
            }

            String customerId = "C" + (++totalCustomers); // Customer IDs start with "C" (C1, C2)

            Customer newCustomer = new Customer(name, phone, email, password, customerId);

            users.put(email, newCustomer);

            System.out.println("Customer registered successfully! Customer ID: " + customerId);
            return true;
        }

        @Override
        public boolean login(String email, String password) {
            if (!users.containsKey(email)) {
                System.out.println("User not found! Please register first.");
                return false;
            }
            User user = users.get(email); //email is key, get(): get the value of key email which is user obj
            if (user.verifyPassword(password)) {
                System.out.println("Login successful");
                user.isLoggedIn = true;
                return true;
            } else {
                System.out.println("Incorrect password!");
                return false;
            }
        }

        public User getUser(String email) {
            return users.get(email); //email used as key
        }

        public void displayUsers() {
            for (User user : users.values()) {
                System.out.println(user);
            }
        }

        public int getTotalCustomers () {
            return totalCustomers;
        }

        public int getTotalAdmins () {
            return totalAdmins;
        }
}

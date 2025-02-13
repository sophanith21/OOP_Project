
public class User implements Authentication {

    protected String userName;
    protected String userPhoneNumber;
    protected String userEmail;
    protected String userPassword;
    protected boolean isLoggedIn;

    public User(String name, String phone, String email, String password) {
        
        this.userName = name;
        this.userPhoneNumber = phone;
        this.userEmail = email;
        this.userPassword = password;
        this.isLoggedIn = false;
    }

    protected boolean verifyPassword(String password) {
        return this.userPassword.equals(password);
    }

    private boolean isValidPhoneNumber(String number) {
        return number.length() >= 8 && number.length() <= 9 && number.charAt(0) == '0' && number.matches("[0-9]+");
    }

    public String getName () {
        return userName;
    }

    public String getEmail ( String password ) {
        if (verifyPassword(password)) {
            return userEmail;
        } else {
            System.out.println("Access denied: Incorrect password.");
            return null;
        }
    }

    public String getPhoneNumber ( String password ) {
        if (verifyPassword(password)) {
            return userPhoneNumber;
        } else {
            System.out.println("Access denied: Incorrect password.");
            return null;
        }
    }

    public void setName(String newName, String password) {
        if (!isLoggedIn) {
            System.out.println("Please log in first.");
            return;
        }
        if (!verifyPassword(password)) {
            System.out.println("Incorrect password.");
            return;
        }
        if (newName == null || newName.trim().isEmpty()) {
            System.out.println("Name cannot be empty.");
            return;
        }
        this.userName = newName;
        System.out.println("Name updated successfully.");
    }

    public void setPhone(String newPhone, String password) {
        if (!isLoggedIn) {
            System.out.println("Please log in first.");
            return;
        }
        if (!verifyPassword(password)) {
            System.out.println("Incorrect password.");
            return;
        }
        if (!isValidPhoneNumber(newPhone)) {
            System.out.println("Invalid phone number format!");
            return;
        }
        this.userPhoneNumber = newPhone;
        System.out.println("Phone number updated successfully.");
    }

    @Override
    public boolean login(String email, String password) {
        if (this.userEmail.equals(email) && this.userPassword.equals(password)) {
            isLoggedIn = true;
            System.out.println("Logged in successfully.");
            return true;
        }
        System.out.println("Invalid credentials");
        return false;
    }

    @Override
    public void logout() {
        isLoggedIn = false;
        System.out.println("Logged out.");
    }
}

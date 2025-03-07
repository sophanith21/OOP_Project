package src.main;
import java.sql.Connection;
import src.DBConnection.DBConnection;
public class SQLTest {
    public static void main(String[] args) {
        // Test the DB connection using DBConnection class
        Connection conn = DBConnection.getConnection();
        
        if (conn != null) {
            System.out.println("Database connection test successful!");
            DBConnection.closeConnection();
        } else {
            System.out.println("Database connection test failed!");
        }
    }
}

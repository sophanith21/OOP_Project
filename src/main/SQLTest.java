package src.main;

import java.sql.Connection;
import java.sql.SQLException;

import src.DBConnection.DBConnection;

public class SQLTest {
    public static void main(String[] args) {
        

        try {
            
            Connection conn = DBConnection.getConnection();

            DBConnection.closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
    }
}

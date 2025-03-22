package booking;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Objects;

import DBConnection.DBConnection;
import DataControl.DataPersistence;

public class Payment implements DataPersistence{ 
    private int userId;
    private String paymentId;
    private String paymentDate;
    private double paymentAmount;
    private String paymentMethod;
    private String status;
    private String transactionId;

    public Payment(int userId, String paymentId, String paymentDate, double paymentAmount, 
                   String paymentMethod, String status, String transactionID) {
        this.userId = userId;
        this.paymentId = paymentId;
        this.paymentDate = paymentDate;
        this.paymentAmount = paymentAmount;
        this.paymentMethod = paymentMethod;
        this.status = status;
        this.transactionId = transactionID;
    }

    public int getUserId() { return userId; }
    public String getPaymentId() { return paymentId; }
    public String getPaymentDate() { return paymentDate; }
    public double getPaymentAmount() { return paymentAmount; }
    public String getPaymentMethod() { return paymentMethod; }
    public String getStatus() { return status; }
    public String getTransactionID() { return transactionId; }

    private void setUserId(int userId) { this.userId = userId; }
    private void setPaymentId(String paymentId) { this.paymentId = paymentId; }
    private void setPaymentDate(String paymentDate) { this.paymentDate = paymentDate; }
    private void setPaymentAmount(double paymentAmount) { this.paymentAmount = paymentAmount; }
    private void setPaymentMethod(String paymentMethod) { this.paymentMethod = paymentMethod; }
    private void setStatus(String status) { this.status = status; }
    private void setTransactionID(String transactionID) { this.transactionId = transactionID; }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Payment other = (Payment) obj;
        return Objects.equals(userId, other.userId) &&
               Objects.equals(paymentId, other.paymentId) &&
               Objects.equals(paymentDate, other.paymentDate) &&
               Objects.equals(paymentAmount, other.paymentAmount) &&
               Objects.equals(paymentMethod, other.paymentMethod) &&
               Objects.equals(status, other.status) &&
               Objects.equals(transactionId, other.transactionId);
    }

    public void paymentAmout(double paymentAmount){
        if (paymentAmount <= 0) {
            throw new IllegalArgumentException("Payment amount must be greater than zero.");
        }
        else {
            System.out.println("Payment amount is valid");
        }
    }

    public void paymentMethod(String paymentMethod){
        if (!paymentMethod.equalsIgnoreCase("Credit Card") && 
            !paymentMethod.equalsIgnoreCase("QR") && 
            !paymentMethod.equalsIgnoreCase("Cash")) {
            throw new IllegalArgumentException("Invalid payment method. Only Credit Card, Debit Card, or Cash are allowed.");
        }
        else{
            System.out.println("Payment method is valid");
        }
    }

     public static void saveAll(ArrayList <Payment> payments){
        try {
            Connection conn = DBConnection.getConnection();
    
            if (conn != null) {
                System.out.println("Database connection successful!");
                String query = "INSERT INTO payment " +
                "VALUES (?, ?, ?, ?, ?, ?) " +
                "ON DUPLICATE KEY UPDATE " +
                "status = VALUES(status)";
                PreparedStatement pstmt = conn.prepareStatement(query);

                for (Payment paymt : payments) {
                    pstmt.setString(1, paymt.getPaymentId());
                    pstmt.setString(2, paymt.getPaymentDate());
                    pstmt.setDouble(3, paymt.getPaymentAmount());
                    pstmt.setString(4, paymt.getPaymentMethod());
                    pstmt.setString(5, paymt.getStatus());
                    pstmt.setString(6, paymt.getTransactionID());
                    pstmt.executeUpdate();
                }

                pstmt.close();
                conn.close();
            } else {
                System.out.println("Database connection failed!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public static ArrayList<Payment> loadAll() {
        ArrayList <Payment> payments = new ArrayList<>();
        try {
            Connection conn = DBConnection.getConnection();

            if (conn != null) {
                System.out.println("Database connection successful!");
                String query = "SELECT * FROM payment ";
                PreparedStatement pstmt = conn.prepareStatement(query);
                ResultSet set = pstmt.executeQuery();
                while (set.next()){
                    Payment pay = new Payment(
                        set.getInt("userId"), 
                        set.getString("paymentId"), 
                        set.getString("paymentDate"),
                        set.getInt("paymentAmount"),
                        set.getString("paymentMethod"), 
                        set.getString("status"), 
                        set.getString("transactionId")
                    );
                    payments.add(pay);
                }

                pstmt.close();
                conn.close();
            } 
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return payments;
    }

    public static int getLastPaymentId (){
        try {
            Connection conn = DBConnection.getConnection();
            if (conn != null) {
                String query = "SELECT COUNT(*) as numOfEntries FROM Booking";
                ResultSet set = DBConnection.executeQuery(query);
                int temp;
                set.next();
                temp = set.getInt("numOfEntries");
                conn.close();
                return temp;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public void saveData(){
        throw new UnsupportedOperationException("Use saveAll instead");
    }

    @Override
    public void loadData(){
        throw new UnsupportedOperationException("Use loadAll instead");
    }


    @Override
    public String toString() {
        return "Payment [userId=" + userId + ", paymentId=" + paymentId + ", paymentDate=" + paymentDate
                + ", paymentAmount=" + paymentAmount + ", paymentMethod=" + paymentMethod + ", status=" + status
                + ", transactionID=" + transactionId + "]";
    }
}

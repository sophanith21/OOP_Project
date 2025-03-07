package src.booking;
import java.util.Objects;

public class Payment { 
    private String userId;
    private String paymentId;
    private String paymentDate;
    private double paymentAmount;
    private String paymentMethod;
    private String status;
    private String transactionID;

    //Payment constructor: To be modified
    public Payment(String userId, String paymentId, String paymentDate, double paymentAmount, 
                   String paymentMethod, String status, String transactionID) {
        this.userId = userId;
        this.paymentId = paymentId;
        this.paymentDate = paymentDate;
        this.paymentAmount = paymentAmount;
        this.paymentMethod = paymentMethod;
        this.status = status;
        this.transactionID = transactionID;
    }

    public String getUserId() { return userId; }
    public String getPaymentId() { return paymentId; }
    public String getPaymentDate() { return paymentDate; }
    public double getPaymentAmount() { return paymentAmount; }
    public String getPaymentMethod() { return paymentMethod; }
    public String getStatus() { return status; }
    public String getTransactionID() { return transactionID; }

    private void setUserId(String userId) { this.userId = userId; }
    private void setPaymentId(String paymentId) { this.paymentId = paymentId; }
    private void setPaymentDate(String paymentDate) { this.paymentDate = paymentDate; }
    private void setPaymentAmount(double paymentAmount) { this.paymentAmount = paymentAmount; }
    private void setPaymentMethod(String paymentMethod) { this.paymentMethod = paymentMethod; }
    private void setStatus(String status) { this.status = status; }
    private void setTransactionID(String transactionID) { this.transactionID = transactionID; }

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
               Objects.equals(transactionID, other.transactionID);
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

    @Override
    public String toString() {
        return "Payment [userId=" + userId + ", paymentId=" + paymentId + ", paymentDate=" + paymentDate
                + ", paymentAmount=" + paymentAmount + ", paymentMethod=" + paymentMethod + ", status=" + status
                + ", transactionID=" + transactionID + "]";
    }
}

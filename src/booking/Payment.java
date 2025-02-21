package src.booking;
import java.util.Objects;

public class Payment { 
    private final String userId;
    private final String paymentId;
    private final String paymentDate;
    private final double paymentAmount;
    private final String paymentMethod;
    private final String status;
    private final String transactionID;

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

    @Override
    public int hashCode() {
        return Objects.hash(userId, paymentId, paymentDate, paymentAmount, 
                            paymentMethod, status, transactionID);
    }
    
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

    @Override
    public String toString() {
        return "Payment [userId=" + userId + ", paymentId=" + paymentId + ", paymentDate=" + paymentDate
                + ", paymentAmount=" + paymentAmount + ", paymentMethod=" + paymentMethod + ", status=" + status
                + ", transactionID=" + transactionID + "]";
    }
}

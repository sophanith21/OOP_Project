public class Payment {
    private String userId;
    private String paymentId;
    private String paymentDate;
    private double paymentAmount;
    private String paymentMethod;
    private String status;
    private String transactionID;

    public Payment(String userId, String paymentId, String paymentDate, double paymentAmount, String paymentMethod, String status, String transactionID) {
        this.userId = userId;
        this.paymentId = paymentId;
        this.paymentDate = paymentDate;
        this.paymentAmount = paymentAmount;
        this.paymentMethod = paymentMethod;
        this.status = status;
        this.transactionID = transactionID;
    }
}
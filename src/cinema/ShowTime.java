package src.cinema;
public class ShowTime {
    private int showTimeID;
    private String date;
    private String time;
    private double pricePerTicket;
    private String startTime;
    private String endTime;

    public ShowTime(int showTimeID, String date, String time, double pricePerTicket, String startTime, String endTime) {
        this.showTimeID = showTimeID;
        this.date = date;
        this.time = time;
        this.pricePerTicket = pricePerTicket;
        this.startTime = startTime;
        this.endTime = endTime;
    }
}

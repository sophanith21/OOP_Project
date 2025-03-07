package src.cinema;

public class VIPSeat extends Seat {
    private String services;

    public VIPSeat(String seatType,int hallId,String seatId,double price,String services) { //Use mainly for loading data
        super(seatType, hallId, seatId, price);
        this.services = services;
    }

    public VIPSeat(int hallId,int rowNumber,int seatNum,String service){
        super(hallId,rowNumber,seatNum, "VIP");
        this.services = service;
    }

    public String getServices() {
        return services;
    }

    public void setService(String service) {
        this.services = service;
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        if (getClass() != obj.getClass())
            return false;
        VIPSeat other = (VIPSeat) obj;
        if (services == null) {
            if (other.services != null)
                return false;
        } else if (!services.equals(other.services))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return super.toString().substring(0, super.toString().length()-1) + ", Service=" + services + "]";
    }

}

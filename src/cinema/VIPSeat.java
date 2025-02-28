package src.cinema;

public class VIPSeat extends Seat {
    private String service;

    public VIPSeat(int hallId,int rowNumber,int seatNum,String service){
        super(hallId,rowNumber,seatNum, "VIP");
        this.service = service;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
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
        if (service == null) {
            if (other.service != null)
                return false;
        } else if (!service.equals(other.service))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return super.toString() + "VIPSeat [service=" + service + "]";
    }

}

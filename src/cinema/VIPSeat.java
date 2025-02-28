package src.cinema;

public class VIPSeat extends Seat {
    private boolean hasRecliner;
    private boolean hasFoodService;

    public VIPSeat(int hallId,int rowNumber,int seatNum,boolean hasRecliner,boolean hasFoodService){
        super(hallId,rowNumber,seatNum, "VIP");
        this.hasRecliner = hasRecliner;
        this.hasFoodService = hasFoodService;
    }

    
    
    public boolean isHasRecliner() {
        return hasRecliner;
    }



    public void setHasRecliner(boolean hasRecliner) {
        this.hasRecliner = hasRecliner;
    }



    public boolean isHasFoodService() {
        return hasFoodService;
    }



    public void setHasFoodService(boolean hasFoodService) {
        this.hasFoodService = hasFoodService;
    }



    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + (hasRecliner ? 1231 : 1237);
        result = prime * result + (hasFoodService ? 1231 : 1237);
        return result;
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
        if (hasRecliner != other.hasRecliner)
            return false;
        if (hasFoodService != other.hasFoodService)
            return false;
        return true;
    }

    @Override
    public String toString(){
        String temp = super.toString() + "\b\b, Extra Services: ";
        int count = 0;
        if(hasRecliner){
            temp = temp + "Recliner";
            count++;
        }
        if(hasFoodService){
            temp = temp + " Food_Service";
            count++;
        }
        if(count == 0)
        {
            temp = temp + " None ]\n";
        }
        else if (count == 1)
        {
            temp = temp + "]\n";
        }
        return temp;
    }
}

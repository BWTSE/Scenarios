package tickets;

import java.util.List;

public class SingleTicket extends Ticket {

    private final int startHour;
    private final int endHour;

    public SingleTicket(String name, 
                        String description, 
                        double price, 
                        List<Zone> validZones, 
                        int startHour,
                        int endHour) {
        super(name, description, price, validZones);
        this.startHour = startHour;
        this.endHour = endHour;
    }

    @Override
    public boolean isValidFor(Zone startZone, Zone endZone, User user, int tripStartHour) {
        if (this.getValidZones().contains(startZone) && this.getValidZones().contains(endZone)
                && (this.startHour < this.endHour 
                    ? (tripStartHour >= this.startHour && tripStartHour < this.endHour)
                    : (tripStartHour <= this.startHour || tripStartHour < this.endHour))) {
            return true;
        }
        return false;
    }

    public int getStartHour() {
        return this.startHour;
    }

    public int getEndHour() {
        return this.endHour;
    }
}

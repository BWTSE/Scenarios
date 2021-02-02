package tickets;

import java.util.List;

public class PeriodTicket extends Ticket {

    private final long duration;

    public PeriodTicket(String name, String description, double price, List<Zone> validZones ,long duration) {
        super(name, description, price, validZones);
        this.duration = duration;
    }

    @Override
    public boolean isValidFor(Zone startZone, Zone endZone, User user, int tripStartHour) {
        if (this.getValidZones().contains(startZone) && this.getValidZones().contains(endZone)) {
            return true;
        }
        return false;
    }

    public long getDuration() {
        return this.duration;
    }
}

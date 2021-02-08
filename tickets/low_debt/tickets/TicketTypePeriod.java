package tickets;

import java.util.Objects;
import java.util.Set;

public class TicketTypePeriod extends AbstractTicketType {

    private final long duration;

    public TicketTypePeriod(String name, double price, Set<Zone> validZones, long duration) {
        super(name, price, validZones);
        this.duration = duration;
    }

    @Override
    public boolean isValidFor(Trip trip, User user) {
        return this.getValidZones().contains(trip.getStartZone()) 
            && this.getValidZones().contains(trip.getEndZone());
    }

    public long getDuration() {
        return this.duration;
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o)
            && Objects.equals(this.getDuration(), ((TicketTypePeriod) o).getDuration()
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(
            super.hashCode(), this.getDuration()
        );
    }
}

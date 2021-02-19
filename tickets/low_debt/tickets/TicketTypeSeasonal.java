package tickets;

import java.util.Objects;
import java.util.Set;

public class TicketTypeSeasonal extends TicketType {

    private final Season validSeason;

    public TicketTypeSeasonal(String name, double price, Set<Zone> validZones, Season validSeason) {
        super(name, price, validZones);
        this.validSeason = validSeason;
    }

    @Override
    public boolean isValidFor(Trip trip, User user) {
        return zonesValid(trip) && timeValid(trip);
    }

    private boolean zonesValid(Trip trip){ 
        return this.getValidZones().contains(trip.getStartZone()) 
            && this.getValidZones().contains(trip.getEndZone());
    }

    private boolean timeValid(Trip trip) {
        return this.getValidSeason().isDateWithin(trip.getTripStartTime());
    }

    public Season getValidSeason() {
        return this.validSeason;
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o)
            && Objects.equals(this.getValidSeason(), ((TicketTypeSeasonal) o).getValidSeason());
    }

    @Override
    public int hashCode() {
        return Objects.hash(
            super.hashCode(), this.getValidSeason()
        );
    }
}

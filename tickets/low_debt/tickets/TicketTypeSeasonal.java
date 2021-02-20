package tickets;

import java.util.Objects;
import java.util.Set;

public class TicketTypeSeasonal extends TicketType {

    private final Season validSeason;

    public TicketTypeSeasonal(String name, double price, Set<Zone> validZones, Season validSeason) {
        super(name, price, validZones);
        this.validSeason = validSeason;
    }

    /*
    Make sure that the trip is during the correct season.
     */
    @Override
    public boolean isValidFor(Trip trip, User user) {
        return super.isValidFor(trip, user)
            && this.getValidSeason().isDateWithin(trip.getTripStartTime());
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

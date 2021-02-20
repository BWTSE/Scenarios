package tickets;

import java.util.Objects;
import java.util.Set;

public class TicketTypeSingle extends TicketType {

    private final int startHour;
    private final int endHour;

    public TicketTypeSingle(
        String name,  
        double price, 
        Set<Zone> validZones, 
        int startHour,
        int endHour
    ) {
        super(name, price, validZones);
        this.startHour = startHour;
        this.endHour = endHour;
    }

    /*
    Makes sure that the trip is during the right time of the day.
     */
    @Override
    public boolean isValidFor(Trip trip, User user) {
        if (this.startHour < this.endHour) {
            return
                trip.getTripStartTime().getHour() >= this.startHour 
                && trip.getTripStartTime().getHour() < this.endHour
                && super.isValidFor(trip, user);
        } else {
            return
                (
                    trip.getTripStartTime().getHour() >= this.startHour
                    || trip.getTripStartTime().getHour() > this.endHour
                )
                && super.isValidFor(trip, user);
        }
    }

    public int getStartHour() {
        return this.startHour;
    }

    public int getEndHour() {
        return this.endHour;
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o)
            && Objects.equals(this.getStartHour(), ((TicketTypeSingle) o).getStartHour())
            && Objects.equals(this.getEndHour(), ((TicketTypeSingle) o).getEndHour());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), this.getStartHour(), this.getEndHour());
    }
}

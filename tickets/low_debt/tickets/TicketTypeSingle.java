package tickets;

import java.util.List;
import java.util.Objects;

public class TicketTypeSingle extends AbstractTicketType {

    private final int startHour;
    private final int endHour;

    public TicketTypeSingle(
        String name,  
        double price, 
        List<Zone> validZones, 
        int startHour,
        int endHour
    ) {
        super(name, price, validZones);
        this.startHour = startHour;
        this.endHour = endHour;
    }

    @Override
    public boolean isValidFor(Trip trip, User user) {
        if (this.startHour < this.endHour) {
            return
                trip.getTripStartTime().getHour() >= this.startHour 
                && trip.getTripStartTime().getHour() < this.endHour
                && this.getValidZones().contains(trip.getStartZone()) 
                && this.getValidZones().contains(trip.getEndZone());
        } else {
            return (trip.getTripStartTime().getHour() >= this.startHour 
                    || trip.getTripStartTime().getHour() > this.endHour) 
                && this.getValidZones().contains(trip.getStartZone()) 
                && this.getValidZones().contains(trip.getEndZone());
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
        if (this == o) {
            return true;
        }
        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }
        TicketTypeSingle ticketType = (TicketTypeSingle) o;
        return Objects.equals(this.getName(), ticketType.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getName(), this.getStartHour());
    }
}

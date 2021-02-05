package tickets;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

public class TicketTypeSingle extends AbstractTicketType {

    private final int startHour;
    private final int endHour;

    public TicketTypeSingle(
        String name, 
        String description, 
        double price, 
        List<Zone> validZones, 
        int startHour,
        int endHour
    ) {
        super(name, description, price, validZones);
        this.startHour = startHour;
        this.endHour = endHour;
    }

    @Override
    public boolean isValidFor(Trip trip, User user, LocalDateTime tripStartHour) {
        if (this.startHour < this.endHour) {
            return
                tripStartHour.getHour() >= this.startHour 
                && tripStartHour.getHour() < this.endHour
                && this.getValidZones().contains(trip.getStartZone()) 
                && this.getValidZones().contains(trip.getEndZone());
        } else {
            return (tripStartHour.getHour() >= this.startHour 
                    || tripStartHour.getHour() > this.endHour) 
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
}

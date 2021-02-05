package tickets;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

public class TicketTypePeriod extends AbstractTicketType {

    private final long duration;

    public TicketTypePeriod(
        String name, 
        String description, 
        double price, 
        List<Zone> validZones, 
        long duration
    ) {
        super(name, description, price, validZones);
        this.duration = duration;
    }

    @Override
    public boolean isValidFor(Trip trip, User user, LocalDateTime tripStartHour) {
        return this.getValidZones().contains(trip.getStartZone()) 
            && this.getValidZones().contains(trip.getEndZone());
    }

    public long getDuration() {
        return this.duration;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }
        TicketTypePeriod ticketType = (TicketTypePeriod) o;
        return Objects.equals(this.getName(), ticketType.getName())
            && Objects.equals(this.getDuration(), ticketType.getDuration()
        );
    } 
}

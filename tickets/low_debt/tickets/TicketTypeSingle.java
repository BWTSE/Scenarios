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
    public boolean isValidFor(
        Zone startZone, 
        Zone endZone, 
        User user, 
        LocalDateTime tripStartHour
    ) {
        if (this.startHour < this.endHour) {
            return
                tripStartHour.getHour() >= this.startHour 
                && tripStartHour.getHour() < this.endHour
                && this.getValidZones().contains(startZone) 
                && this.getValidZones().contains(endZone);
        } else {
            return (tripStartHour.getHour() >= this.startHour 
                    || tripStartHour.getHour() > this.endHour) 
                && this.getValidZones().contains(startZone) 
                && this.getValidZones().contains(endZone);
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
        return Objects.hash(this.getName());
    }

    @Override
    public String toString() {
        return String.format(
                "Ticket %s ",
                this.getName()
        );
    }
}

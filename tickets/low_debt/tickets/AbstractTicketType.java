package tickets;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Objects;

public abstract class AbstractTicketType {
    private final String name;
    private final String description;
    private final double price;
    private final Collection<Zone> validZones;

    protected AbstractTicketType(
        String name, 
        String description, 
        double price, 
        Collection<Zone> validZones
    ) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.validZones = new LinkedList<>(validZones);
    }

    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return this.description;
    }

    public double getPrice() {
        return this.price;
    }

    public Collection<Zone> getValidZones() {
        return new LinkedList<>(this.validZones);
    }

    public abstract boolean isValidFor(
        Trip trip, 
        User user, 
        LocalDateTime tripStartHour
    );

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }
        AbstractTicketType ticketType = (AbstractTicketType) o;
        return Objects.equals(this.getName(), ticketType.getName())
            && Objects.equals(this.getDescription(), ticketType.getDescription()) 
            && Objects.equals(this.getValidZones(), ticketType.getValidZones());
    }

    public int hashCode() {
        return Objects.hash(this.getName());
    }

    public String toString() {
        return String.format(
                "Ticket %s ",
                this.getName()
        );
    }
}

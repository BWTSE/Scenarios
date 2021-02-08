package tickets;

import java.util.Collection;
import java.util.LinkedList;
import java.util.Objects;

public abstract class AbstractTicketType {
    private final String name;
    private final double price;
    private final Collection<Zone> validZones;

    protected AbstractTicketType(
        String name,
        double price, 
        Collection<Zone> validZones
    ) {
        this.name = name;
        this.price = price;
        this.validZones = new LinkedList<>(validZones);
    }

    public String getName() {
        return this.name;
    }

    public double getPrice() {
        return this.price;
    }

    public Collection<Zone> getValidZones() {
        return new LinkedList<>(this.validZones);
    }

    public abstract boolean isValidFor(
        Trip trip, 
        User user
    );

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }
        AbstractTicketType ticketType = (AbstractTicketType) o;
        return Objects.equals(this.getName(), ticketType.getName())
            && Objects.equals(this.getValidZones(), ticketType.getValidZones());
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

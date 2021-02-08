package tickets;

import java.util.EnumSet;
import java.util.Objects;
import java.util.Set;

public abstract class AbstractTicketType {
    private final String name;
    private final double price;
    private final Set<Zone> validZones;

    protected AbstractTicketType(String name, double price, Set<Zone> validZones) {
        this.name = name;
        this.price = price;
        this.validZones = EnumSet.copyOf(validZones);
    }

    public String getName() {
        return this.name;
    }

    public double getPrice() {
        return this.price;
    }

    public Set<Zone> getValidZones() {
        return EnumSet.copyOf(this.validZones);
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
            && Objects.equals(this.getValidZones(), ticketType.getValidZones())
            && Objects.equals(this.getPrice(), ticketType.getPrice());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getName(), this.getValidZones(), this.getPrice());
    }

    @Override
    public String toString() {
        return String.format("Ticket %s ", this.getName());
    }
}

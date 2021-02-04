package tickets;

import java.util.List;
import java.util.Objects;

public abstract class TicketType {
    private final String name;
    private final String description;
    private final double price;
    private final List<Zone> validZones;

    protected TicketType(String name, String description, double price, List<Zone> validZones) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.validZones = validZones;
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

    public List<Zone> getValidZones() {
        return this.validZones;
    }

    public abstract boolean isValidFor(Zone startZonne, Zone endZone, User user, int tripStartHour);

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }
        TicketType ticketType = (TicketType) o;
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
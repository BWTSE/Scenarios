package tickets;

import java.util.Collection;

public interface TicketType {

    public String getName();

    public double getPrice();

    public Collection<Zone> getValidZones();

    public abstract boolean isValidFor(Trip t, User u);
}

package tickets;

import java.util.Collection;

public interface TicketTypeInterface {

    public String getName();

    public String getDescription();

    public double getPrice();

    public Collection<Zone> getValidZones();

    public abstract boolean isValidFor(Trip t, User u);
}

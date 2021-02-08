package tickets;

import java.util.Collection;

public interface TicketType {

    String getName();

    double getPrice();

    Collection<Zone> getValidZones();

    boolean isValidFor(Trip t, User u);
}

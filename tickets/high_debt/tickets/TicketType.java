package tickets;

import java.util.Set;

public interface TicketType {
    String getName();
    double getPrice();
    Set<Zone> getValidZones();
    boolean isValidFor(Trip t, User u);
}

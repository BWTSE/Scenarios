package tickets;

import java.util.EnumSet;
import java.util.Set;

public class TicketTypePeriodRestricted extends TicketTypePeriod {

    EnumSet<User.Occupation> allowedOccupations;

    public TicketTypePeriodRestricted(String name, double price, Set<Zone> validZones, long duration, Set<User.Occupation> allowedOccupations) {
        super(name, price, validZones, duration);
        this.allowedOccupations = EnumSet.copyOf(allowedOccupations);
    }
    
    @Override
    public boolean isValidFor(Trip trip, User user) {
        return true;
    }
}

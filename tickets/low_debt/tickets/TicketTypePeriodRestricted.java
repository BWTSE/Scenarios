package tickets;

import java.util.List;


public class TicketTypePeriodRestricted extends TicketTypePeriod {

    private final boolean allowStudent;
    private final boolean allowRetiree;

    // EXAMPLE SOLUTION

    public TicketTypePeriodRestricted(String name, 
                        String description, 
                        double price, 
                        List<Zone> validZones,
                        long duration, 
                        boolean allowStudent,
                        boolean allowRetiree) {
        super(name, description, price, validZones, duration);
        this.allowStudent = allowStudent;
        this.allowRetiree = allowRetiree;
    }

    @Override
    public boolean isValidFor(Zone startZone, Zone endZone, User user, int tripStartHour) {
        if (allowStudent && user.isStudent() || allowRetiree && user.isRetiree()) {
            return super.isValidFor(startZone, endZone, user, tripStartHour);
        } else {
            return false;
        }
    }
}

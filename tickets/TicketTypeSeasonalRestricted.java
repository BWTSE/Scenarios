package tickets;

import java.util.EnumSet;
import java.util.Objects;
import java.util.Set;

public class TicketTypeSeasonalRestricted extends TicketTypeSeasonal {

    private final Set<User.Occupation> allowedOccupations;

    public TicketTypeSeasonalRestricted(
        String name,
        double price, 
        Set<Zone> validZones, 
        Season season,
        Set<User.Occupation> allowedOccupations
    ) {
        super(name, price, validZones, season);
        this.allowedOccupations = EnumSet.copyOf(allowedOccupations);
    }

    public Set<User.Occupation> getAllowedOccupations() {
        return EnumSet.copyOf(this.allowedOccupations);
    }

    @Override
    public boolean isValidFor(Trip trip, User user) {
        return super.isValidFor(trip, user)
            && this.getAllowedOccupations().contains(user.getOccupation());
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o)
            && Objects.equals(
                this.getAllowedOccupations(),
                ((TicketTypeSeasonalRestricted) o).getAllowedOccupations()
            );
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), this.getAllowedOccupations());
    }
}

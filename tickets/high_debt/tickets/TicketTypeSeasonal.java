package tickets;

import java.util.EnumSet;
import java.util.Objects;
import java.util.Set;

public class TicketTypeSeasonal implements TicketType {

    private final String n;
    private final double p;
    private final Set<Zone> zs;
    private final Season vs;

    public TicketTypeSeasonal(String n, double p, Set<Zone> zs, Season vs) {
        this.n = n;
        this.p = p;
        this.zs = EnumSet.copyOf(zs);
        this.vs = vs;
    }

    /*
    Make sure that the trip is during the correct season.
    Also checks that the ticket is valid in the zones of the trip.
     */
    @Override
    public boolean isValidFor(Trip t, User u) {
        return this.getValidZones().contains(t.getStartZone())
            && this.getValidZones().contains(t.getEndZone())
            && this.getValidSeason().isDateWithin(t.getTripStartTime());
    }

    public String getName() {
        return this.n;
    }

    public double getPrice() {
        return this.p;
    }

    public Set<Zone> getValidZones() {
        return EnumSet.copyOf(this.zs);
    }
    
    public Season getValidSeason() {
        return this.vs;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }

        TicketTypeSeasonal tt = (TicketTypeSeasonal) o;
        return Objects.equals(this.getName(), tt.getName())
            && Objects.equals(this.getValidSeason(), tt.getValidSeason())
            && Objects.equals(this.getValidZones(), tt.getValidZones())
            && Objects.equals(this.getPrice(), tt.getPrice());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getName(), this.getPrice(), this.getValidSeason());
    }

    @Override
    public String toString() {
        return String.format("Ticket %s ", this.getName());
    }
}

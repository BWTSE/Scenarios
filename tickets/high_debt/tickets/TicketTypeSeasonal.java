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

    @Override
    public boolean isValidFor(Trip t, User u) {
        return zonesValid(t) && timeValid(t);
    }

    private boolean zonesValid(Trip t){ 
        return this.getValidZones().contains(t.getStartZone()) 
            && this.getValidZones().contains(t.getEndZone());
    }

    private boolean timeValid(Trip trip) {
        return this.getValidSeason().isDateWithin(trip.getTripStartTime());
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
    
    private Season getValidSeason() {
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
}

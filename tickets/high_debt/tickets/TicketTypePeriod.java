package tickets;

import java.util.EnumSet;
import java.util.Objects;
import java.util.Set;

public class TicketTypePeriod implements TicketType {

    private final String n;
    private final double p;
    private final Set<Zone> zs;
    private final long d;

    public TicketTypePeriod(String n, double p, Set<Zone> zs, long d) {
        this.n = n;
        this.p = p;
        this.zs = EnumSet.copyOf(zs);
        this.d = d;
    }

    @Override
    public boolean isValidFor(Trip t, User u) {
        return this.getValidZones().contains(t.getStartZone()) 
            && this.getValidZones().contains(t.getEndZone());
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

    public long getDuration() {
        return this.d;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }
        TicketTypePeriod tt = (TicketTypePeriod) o;
        return Objects.equals(this.getName(), tt.getName())
            && Objects.equals(this.getDuration(), tt.getDuration())
            && Objects.equals(this.getPrice(), tt.getPrice());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getName(), this.getPrice(), this.getDuration());
    }
}

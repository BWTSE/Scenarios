package tickets;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class TicketTypePeriod implements TicketTypeInterface {

    private final String n;
    private final String d;
    private final double p;
    private final Collection<Zone> validz;
    private final long dur;

    public TicketTypePeriod(
        String n, 
        String d, 
        double p, 
        List<Zone> validz, 
        long dur
    ) {
        this.n = n;
        this.d = d;
        this.p = p;
        this.validz = new LinkedList<>(validz);
        this.dur = dur;
    }

    @Override
    public boolean isValidFor(Trip t, User u) {
        return this.getValidZones().contains(t.getStartZone()) 
            && this.getValidZones().contains(t.getEndZone());
    }

    public String getName() {
        return this.n;
    }

    public String getDescription() {
        return this.d;
    }

    public double getPrice() {
        return this.p;
    }

    public Collection<Zone> getValidZones() {
        return new LinkedList<>(this.validz);
    }

    public long getDuration() {
        return this.dur;
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
            && Objects.equals(this.getDuration(), tt.getDuration()
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getName(), this.getDuration());
    }
}

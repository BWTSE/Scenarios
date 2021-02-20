package tickets;

import java.util.EnumSet;
import java.util.Objects;
import java.util.Set;

public class TicketTypeSingle implements TicketType {

    private final String n;
    private final double p;
    private final Set<Zone> zs;
    private final int sh;
    private final int eh;

    public TicketTypeSingle(String n, double p, Set<Zone> zs, int sh, int eh) {
        this.n = n;
        this.p = p;
        this.zs = EnumSet.copyOf(zs);
        this.sh = sh;
        this.eh = eh;
    }

    /*
    Makes sure that the trip is during the right time of the day.
    Also checks that the ticket is valid in the zones of the trip.
     */
    @Override
    public boolean isValidFor(Trip t, User u) {
        if (this.sh < this.eh) {
            return
                t.getTripStartTime().getHour() >= this.sh 
                && t.getTripStartTime().getHour() < this.eh
                && this.getValidZones().contains(t.getStartZone()) 
                && this.getValidZones().contains(t.getEndZone());
        } else {
            return (t.getTripStartTime().getHour() >= this.sh 
                    || t.getTripStartTime().getHour() > this.eh) 
                && this.getValidZones().contains(t.getStartZone()) 
                && this.getValidZones().contains(t.getEndZone());
        }
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

    public int getStartHour() {
        return this.sh;
    }

    public int getEndHour() {
        return this.eh;
    } 

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }

        TicketTypeSingle tt = (TicketTypeSingle) o;
        return Objects.equals(this.getName(), tt.getName())
            && Objects.equals(this.getValidZones(), tt.getValidZones())
            && Objects.equals(this.getStartHour(), tt.getStartHour())
            && Objects.equals(this.getEndHour(), tt.getEndHour())
            && Objects.equals(this.getPrice(), tt.getPrice());
    }

    @Override
    public int hashCode() {
        return Objects.hash(
            this.getName(),
            this.getPrice(),
            this.getStartHour(),
            this.getEndHour()
        );
    }

    @Override
    public String toString() {
        return String.format("Ticket %s ", this.getName());
    }
}

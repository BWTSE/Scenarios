package tickets;

import java.util.EnumSet;
import java.util.Objects;
import java.util.Set;

public class TicketTypeSingle implements TicketType {

    private final String n;
    private final double p;
    private final Set<Zone> validz;
    private final int starth;
    private final int endh;

    public TicketTypeSingle(String n, double p, Set<Zone> validz, int starth,int endh) {
        this.n = n;
        this.p = p;
        this.validz = EnumSet.copyOf(validz);
        this.starth = starth;
        this.endh = endh;
    }

    @Override
    public boolean isValidFor(Trip t, User u) {
        if (this.starth < this.endh) {
            return
                t.getTripStartTime().getHour() >= this.starth 
                && t.getTripStartTime().getHour() < this.endh
                && this.getValidZones().contains(t.getStartZone()) 
                && this.getValidZones().contains(t.getEndZone());
        } else {
            return (t.getTripStartTime().getHour() >= this.starth 
                    || t.getTripStartTime().getHour() > this.endh) 
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
        return EnumSet.copyOf(this.validz);
    }

    public int getStartHour() {
        return this.starth;
    }

    public int getEndHour() {
        return this.endh;
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
            && Objects.equals(this.getStartHour(), tt.getStartHour())
            && Objects.equals(this.getEndHour(), tt.getEndHour())
            && Objects.equals(this.getPrice(), tt.getPrice());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getName(), this.getPrice(), this.getStartHour());
    }
}

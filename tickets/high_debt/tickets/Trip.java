package tickets;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class Trip {

    private final Zone sz;
    private final Zone ez;
    private final LocalDateTime t;

    public Trip(Zone sz, Zone ez, LocalDateTime t) {
        this.sz = sz;
        this.ez = ez;
        this.t = t;
    }

    public Zone getStartZone() {
        return this.sz;
    }

    public Zone getEndZone() {
        return this.ez;
    }
    
    public LocalDateTime getTripStartTime() {
        return this.t;
    }

    @Override 
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }

        Trip trip = (Trip) o;
        return Objects.equals(this.getStartZone(), trip.getStartZone())
            && Objects.equals(this.getEndZone(), trip.getEndZone())
            && Objects.equals(this.getTripStartTime(), trip.getTripStartTime());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getStartZone(), this.getEndZone(), this.getTripStartTime());
    }

    @Override
    public String toString() {
        DateTimeFormatter f = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");

        return String.format(
            "Trip from %s to %s starting at %s", 
            this.getStartZone(), 
            this.getEndZone(),
            f.format(this.getTripStartTime())
        );
    }
}

package tickets;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class Trip {

    private final Zone startZone;
    private final Zone endZone;
    private final LocalDateTime tripStartTime;

    public Trip(Zone startZone, Zone endZone, LocalDateTime tripStartTime) {
        this.startZone = startZone;
        this.endZone = endZone;
        this.tripStartTime = tripStartTime;
    }

    public Zone getStartZone() {
        return this.startZone;
    }

    public Zone getEndZone() {
        return this.endZone;
    }
    
    public LocalDateTime getTripStartTime() {
        return this.tripStartTime;
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
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");

        return String.format(
            "Trip from %s to %s starting at %s", 
            this.getStartZone(), 
            this.getEndZone(),
            dateTimeFormatter.format(this.getTripStartTime())
        );
    }
}

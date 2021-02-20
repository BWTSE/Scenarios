package booking;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class Interval {
    private final LocalDateTime start;
    private final LocalDateTime end;

    public Interval(LocalDateTime start, LocalDateTime end) {
        this.start = start;
        this.end = end;
    }

    public LocalDateTime getStart() {
        return start;
    }

    public LocalDateTime getEnd() {
        return end;
    }

    public boolean isDuring(LocalDateTime time) {
        return !time.isBefore(this.getStart()) && !time.isAfter(this.getEnd());
    }

    public boolean overlapsWith(Interval other) {
        return this.isDuring(other.getStart())
            || this.isDuring(other.getEnd())
            || other.isDuring(this.getStart());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }
        
        Interval interval = (Interval) o;
        return Objects.equals(this.getStart(), interval.getStart()) &&
                Objects.equals(this.getEnd(), interval.getEnd());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getStart(), getEnd());
    }

    @Override
    public String toString() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");

        return String.format(
                "%s to %s",
                dateTimeFormatter.format(this.getStart()),
                dateTimeFormatter.format(this.getEnd())
        );
    }
}

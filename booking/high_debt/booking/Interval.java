package booking;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class Interval {
    private final LocalDateTime s;
    private final LocalDateTime e;

    public Interval(LocalDateTime s, LocalDateTime e) {
        this.s = s;
        this.e = e;
    }

    public LocalDateTime getStart() {
        return s;
    }

    public LocalDateTime getEnd() {
        return e;
    }

    public boolean isDuring(LocalDateTime t) {
        return !t.isBefore(this.getStart()) && !t.isAfter(this.getEnd());
    }

    public boolean overlapsWith(Interval o) {
        return this.isDuring(o.getStart())
            || this.isDuring(o.getEnd())
            || o.isDuring(this.getStart());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }
        
        Interval i = (Interval) o;
        return Objects.equals(this.getStart(), i.getStart()) &&
                Objects.equals(this.getEnd(), i.getEnd());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getStart(), getEnd());
    }

    @Override
    public String toString() {
        DateTimeFormatter f = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");

        return String.format(
                "%s to %s",
                f.format(this.getStart()),
                f.format(this.getEnd())
        );
    }
}

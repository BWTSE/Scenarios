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
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");
        return String.format(
                "%s to %s",
                dtf.format(this.getStart()),
                dtf.format(this.getEnd())
        );
    }
}

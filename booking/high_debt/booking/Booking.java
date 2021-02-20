package booking;

import java.util.Objects;

public class Booking {
    private final Interval i;
    private final User u;

    public Booking(Interval i, User u) {
        this.i = i;
        this.u = u;
    }

    public Interval getInterval() {
        return this.i;
    }

    public User getBooker() {
        return this.u;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }

        Booking b = (Booking) o;
        return Objects.equals(this.getInterval(), b.getInterval()) &&
                Objects.equals(this.getBooker(), b.getBooker());
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                this.getInterval(),
                this.getBooker()
        );
    }

    @Override
    public String toString() {
        return String.format(
                "[%s] by (%s)",
                this.getInterval().toString(),
                this.getBooker().toString()
        );
    }
}

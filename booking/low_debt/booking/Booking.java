package booking;

import java.util.Objects;

public class Booking {
    private final Interval interval;
    private final User booker;

    public Booking(Interval interval, User booker) {
        this.interval = interval;
        this.booker = booker;
    }

    public Interval getInterval() {
        return this.interval;
    }

    public User getBooker() {
        return this.booker;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }

        Booking booking = (Booking) o;
        return Objects.equals(this.getInterval(), booking.getInterval()) &&
                Objects.equals(this.getBooker(), booking.getBooker());
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

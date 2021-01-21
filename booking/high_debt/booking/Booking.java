package booking;

import java.util.Objects;

public class Booking {
    private final Interval i;
    private final User c;

    public Booking(Interval i, User c) {
        this.i = i;
        this.c = c;
    }

    public Interval getInterval() {
        return this.i;
    }

    public User getCustomer() {
        return this.c;
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
                Objects.equals(this.getCustomer(), b.getCustomer());
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                this.getInterval(),
                this.getCustomer()
        );
    }

    @Override
    public String toString() {
        return String.format(
                "[%s] by (%s)",
                this.getInterval().toString(),
                this.getCustomer().toString()
        );
    }
}

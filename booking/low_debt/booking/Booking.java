package booking;

import java.util.Objects;

public class Booking {
    private final Interval interval;
    private final User customer;

    public Booking(Interval interval, User customer) {
        this.interval = interval;
        this.customer = customer;
    }

    public Interval getInterval() {
        return this.interval;
    }

    public User getCustomer() {
        return this.customer;
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
                Objects.equals(this.getCustomer(), booking.getCustomer());
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

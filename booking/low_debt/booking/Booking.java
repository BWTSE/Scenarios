package booking;

import java.util.Objects;

public class Booking {
    private final Interval interval;
    private final User customer;
    private final Resource resource;

    public Booking(Interval interval, User customer, Resource resource) {
        this.interval = interval;
        this.customer = customer;
        this.resource = resource;
    }

    public Interval getInterval() {
        return this.interval;
    }

    public User getCustomer() {
        return this.customer;
    }

    public Resource getResource() {
        return this.resource;
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
                Objects.equals(this.getCustomer(), booking.getCustomer()) &&
                Objects.equals(this.getResource(), booking.getResource());
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                this.getInterval(),
                this.getCustomer(),
                this.getResource()
        );
    }

    @Override
    public String toString() {
        return String.format(
                "[%s] (%s) booked by (%s)",
                this.getInterval().toString(),
                this.getResource().toString(),
                this.getCustomer().toString()
        );
    }
}

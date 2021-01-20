package booking;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class Booking {
    private final LocalDateTime start;
    private final LocalDateTime end;
    private final User customer;
    private final Resource resource;

    public Booking(LocalDateTime start, LocalDateTime end, User customer, Resource resource) {
        this.start = start;
        this.end = end;
        this.customer = customer;
        this.resource = resource;
    }

    public LocalDateTime getStart() {
        return this.start;
    }

    public LocalDateTime getEnd() {
        return this.end;
    }

    public User getCustomer() {
        return this.customer;
    }

    public Resource getResource() {
        return this.resource;
    }

    public boolean isDuring(LocalDateTime time) {
        return !time.isBefore(this.getStart()) && !time.isAfter(this.getEnd());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Booking)) {
            return false;
        }
        Booking booking = (Booking) o;
        return Objects.equals(this.getStart(), booking.getStart()) &&
                Objects.equals(this.getEnd(), booking.getEnd()) &&
                Objects.equals(this.getCustomer(), booking.getCustomer()) &&
                Objects.equals(this.getResource(), booking.getResource());
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                this.getStart(),
                this.getEnd(),
                this.getCustomer(),
                this.getResource()
        );
    }

    @Override
    public String toString() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");
        return String.format(
                "[%s to %s] (%s) booked by (%s)",
                dateTimeFormatter.format(this.getStart()),
                dateTimeFormatter.format(this.getEnd()),
                this.getResource().toString(),
                this.getCustomer().toString()
        );
    }
}

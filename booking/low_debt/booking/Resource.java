package booking;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class Resource {
    private final String name;
    private final String description;

    private final List<Booking> bookings = new LinkedList<>();

    protected Resource(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return this.description;
    }

    public List<Booking> getBookings() {
        return List.copyOf(this.bookings);
    }

    public boolean available(Interval interval) {
        for (Booking booking : this.getBookings()) {
            if (booking.getInterval().overlapsWith(interval)) {
                return false;
            }
        }
        return true;
    }

    public Booking book(Interval interval, User customer) {
        if (this.available(interval)) {
            Booking booking = new Booking(interval, customer);
            bookings.add(booking);
            return booking;
        } else {
            return null;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }
        Resource resource = (Resource) o;
        return Objects.equals(this.getName(), resource.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getName());
    }

    @Override
    public String toString() {
        return String.format(
                "Resource %s \"%s\" #bookings: %s",
                this.getName(),
                this.getDescription(),
                this.getBookings().size()
        );
    }
}


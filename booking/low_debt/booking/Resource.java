package booking;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public abstract class Resource {
    private final String name;
    private final String description;

    private final List<Booking> bookings = new LinkedList<>();

    public Resource(String name, String description) {
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

    public boolean available(LocalDateTime start, LocalDateTime end) {
        for (Booking booking : this.getBookings()) {
            if (booking.isDuring(start)
                    || booking.isDuring(end)
                    || (booking.getStart().isAfter(start) && booking.getStart().isBefore(end))
            ) {
                return false;
            }
        }
        return true;
    }

    public Booking book(LocalDateTime start, LocalDateTime end, User customer) {
        if (this.available(start, end)) {
            Booking booking = new Booking(start, end, customer, this);
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
        if (!(o instanceof Resource)) {
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


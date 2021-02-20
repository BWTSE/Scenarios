package booking;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class Room {
    private final String name;
    private final String description;

    private final List<Booking> bookings = new LinkedList<>();

    protected Room(String name, String description) {
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

    private boolean available(Interval interval) {
        for (Booking booking : this.getBookings()) {
            if (booking.getInterval().overlapsWith(interval)) {
                return false;
            }
        }

        return true;
    }

    /*
    Makes sure that the booking is valid and that the room is available.
     */
    public Optional<Booking> book(Interval interval, User customer) {
        if (
            !this.available(interval) 
            || interval.getEnd().isBefore(interval.getStart())
            || interval.getStart().isBefore(LocalDateTime.now())
        ) {
            return Optional.empty();
        }

        Booking booking = new Booking(interval, customer);
        bookings.add(booking);

        return Optional.of(booking);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        
        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }

        Room room = (Room) o;
        return Objects.equals(this.getName(), room.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getName());
    }

    @Override
    public String toString() {
        return String.format(
                "Room %s \"%s\" #bookings: %s",
                this.getName(),
                this.getDescription(),
                this.getBookings().size()
        );
    }
}


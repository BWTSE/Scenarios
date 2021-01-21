package booking;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class Car implements Resource {

    private final String name;
    private final String description;

    private final List<Booking> bookings = new LinkedList<>();

    protected Car(String name, String description) {
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
            if ((
                    !interval.getStart().isBefore(booking.getInterval().getStart())
                            && !interval.getStart().isAfter(booking.getInterval().getEnd())
            ) || (
                    !interval.getEnd().isBefore(booking.getInterval().getStart())
                            && !interval.getEnd().isAfter(booking.getInterval().getEnd())
            ) || (
                    !booking.getInterval().getStart().isBefore(interval.getStart())
                            && !booking.getInterval().getStart().isAfter(interval.getEnd())
            )) {
                return false;
            }
        }
        return true;
    }

    /*
    Due to the popularity of some specific cars,
     no user is allowed to have more than one upcoming booking for a car.
     */
    public Optional<Booking> book(Interval interval, User customer) {
        for (Booking booking : this.getBookings()) {
            if (booking.getInterval().getEnd().isAfter(LocalDateTime.now())
                    && booking.getCustomer().equals(customer)
            ) {
                return Optional.empty();
            }
        }

        if (this.available(interval)) {
            Booking booking = new Booking(interval, customer);
            bookings.add(booking);
            return Optional.of(booking);
        } else {
            return Optional.empty();
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
        Car resource = (Car) o;
        return Objects.equals(this.getName(), resource.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getName());
    }

    @Override
    public String toString() {
        return String.format(
                "Car %s \"%s\" #bookings: %s",
                this.getName(),
                this.getDescription(),
                this.getBookings().size()
        );
    }
}

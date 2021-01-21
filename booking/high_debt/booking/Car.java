package booking;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class Car implements Resource {

    private final String n;
    private final String d;

    private final List<Booking> bs = new LinkedList<>();

    protected Car(String n, String d) {
        this.n = n;
        this.d = d;
    }

    public String getName() {
        return this.n;
    }

    public String getDescription() {
        return this.d;
    }

    public List<Booking> getBookings() {
        return List.copyOf(this.bs);
    }

    private boolean available(Interval i) {
        for (Booking b : this.getBookings()) {
            if ((
                    !i.getStart().isBefore(b.getInterval().getStart())
                            && !i.getStart().isAfter(b.getInterval().getEnd())
                ) || (
                    !i.getEnd().isBefore(b.getInterval().getStart())
                            && !i.getEnd().isAfter(b.getInterval().getEnd())
                ) || (
                    !b.getInterval().getStart().isBefore(i.getStart())
                            && !b.getInterval().getStart().isAfter(i.getEnd())
                )
            ) {
                return false;
            }
        }
        return true;
    }

    /*
    Due to the popularity of some specific cars,
     no user is allowed to have more than one upcoming booking for a car.
     */
    public Optional<Booking> book(Interval i, User u) {
        for (Booking b : this.getBookings()) {
            if (b.getInterval().getEnd().isAfter(LocalDateTime.now())
                    && b.getCustomer().equals(u)
            ) {
                return Optional.empty();
            }
        }

        if (this.available(i)) {
            Booking b = new Booking(i, u);
            bs.add(b);
            return Optional.of(b);
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
        Car c = (Car) o;
        return Objects.equals(this.getName(), c.getName());
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

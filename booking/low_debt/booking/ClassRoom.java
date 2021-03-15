package booking;

import java.time.LocalDateTime;
import java.time.temporal.ChronoField;
import java.util.Objects;
import java.util.Optional;

public class ClassRoom extends Room {

    private final boolean hasProjector;

    public ClassRoom(String name, String description, boolean hasProjector) {
        super(name, description);
        this.hasProjector = hasProjector;
    }

    public boolean hasProjector() {
        return this.hasProjector;
    }

    /*
    Only allows bookings that start on the hour. (Floors second and microsecond values).
    */
    @Override
    public Optional<Booking> book(Interval interval, User customer) {
        LocalDateTime bookingStart = interval.getStart()
            .with(ChronoField.SECOND_OF_MINUTE, 0)
            .with(ChronoField.NANO_OF_SECOND, 0);

        LocalDateTime bookingEnd = interval.getEnd()
            .with(ChronoField.SECOND_OF_MINUTE, 0)
            .with(ChronoField.NANO_OF_SECOND, 0);

        if (
            bookingStart.getMinute() != 0
            || bookingEnd.getMinute() != 0
            || bookingEnd.with(ChronoField.HOUR_OF_DAY, 0).isAfter(bookingStart)
        ) {
            return Optional.empty();
        }

        return super.book(new Interval(bookingStart, bookingEnd), customer);
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o)
            && Objects.equals(this.hasProjector(), ((ClassRoom) o).hasProjector());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), this.hasProjector());
    }
}

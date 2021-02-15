package booking;

import java.time.LocalDateTime;
import java.time.temporal.ChronoField;
import java.util.Optional;

public class ClassRoom extends Resource {

    public ClassRoom(String name, String description) {
        super(name, description);
    }

    /*
    Only allows bookings that start on the hour. (Floors second and microsecond values)
    Does not allow 
    */
    @Override
    public Optional<Booking> book(Interval interval, User customer) {
        LocalDateTime bookingStart = interval.getStart()
            .with(ChronoField.SECOND_OF_MINUTE, 0)
            .with(ChronoField.NANO_OF_SECOND, 0);
        LocalDateTime bookingEnd = interval.getStart()
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
}

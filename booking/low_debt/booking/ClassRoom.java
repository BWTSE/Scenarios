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
    */

    @Override
    public Optional<Booking> book(Interval interval, User customer) {
        LocalDateTime bookingStart = interval.getStart()
            .with(ChronoField.SECOND_OF_MINUTE, 0)
            .with(ChronoField.MICRO_OF_SECOND, 0);
        LocalDateTime bookingEnd = interval.getStart()
            .with(ChronoField.SECOND_OF_MINUTE, 0)
            .with(ChronoField.MICRO_OF_SECOND, 0);

        if ((bookingStart.getMinute() != 0 && bookingEnd.getMinute() != 0)
            || bookingStart.getDayOfMonth() != bookingEnd.getDayOfMonth()
            || bookingStart.getMonth() != bookingEnd.getMonth()
            || bookingStart.getYear() != bookingEnd.getYear()
            || !bookingStart.isAfter(LocalDateTime.now())) {
            return Optional.empty();
        }
        return super.book(new Interval(bookingStart, bookingEnd), customer);
    }
}

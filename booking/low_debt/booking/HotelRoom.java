package booking;

import java.time.LocalDateTime;
import java.time.temporal.ChronoField;
import java.util.Optional;

public class HotelRoom extends Resource {

    private static final int CHECK_IN_TIME = 15;
    private static final int CHECK_OUT_TIME = 11;

    public HotelRoom(String name, String description) {
        super(name, description);
    }

    /*
    Makes sure booking has check-in at 15 and check-out at 11.
     */
    @Override
    public Optional<Booking> book(Interval interval, User customer) {

        LocalDateTime checkIn = interval.getStart()
                .with(ChronoField.HOUR_OF_DAY, CHECK_IN_TIME)
                .with(ChronoField.MINUTE_OF_HOUR, 0)
                .with(ChronoField.SECOND_OF_MINUTE, 0)
                .with(ChronoField.MICRO_OF_SECOND, 0);

        LocalDateTime checkOut = interval.getEnd()
                .with(ChronoField.HOUR_OF_DAY, CHECK_OUT_TIME)
                .with(ChronoField.MINUTE_OF_HOUR, 0)
                .with(ChronoField.SECOND_OF_MINUTE, 0)
                .with(ChronoField.MICRO_OF_SECOND, 0);

        return super.book(new Interval(checkIn, checkOut), customer);
    }
}

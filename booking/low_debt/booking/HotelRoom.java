package booking;

import java.time.LocalDateTime;
import java.time.temporal.ChronoField;

public class HotelRoom extends Resource {

    public HotelRoom(String name, String description) {
        super(name, description);
    }

    /*
    Makes sure booking has check-in at 15 and check-out at 11.
     */
    @Override
    public Booking book(LocalDateTime start, LocalDateTime end, User customer) {

        LocalDateTime checkIn = start
                .with(ChronoField.HOUR_OF_DAY, 15)
                .with(ChronoField.MINUTE_OF_HOUR, 0)
                .with(ChronoField.SECOND_OF_MINUTE, 0)
                .with(ChronoField.MICRO_OF_SECOND, 0);

        LocalDateTime checkOut = end
                .with(ChronoField.HOUR_OF_DAY, 11)
                .with(ChronoField.MINUTE_OF_HOUR, 0)
                .with(ChronoField.SECOND_OF_MINUTE, 0)
                .with(ChronoField.MICRO_OF_SECOND, 0);

        return super.book(checkIn, checkOut, customer);
    }
}

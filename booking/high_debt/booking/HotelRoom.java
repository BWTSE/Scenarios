package booking;

import java.time.LocalDateTime;
import java.time.temporal.ChronoField;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class HotelRoom implements Resource {

    private static final int CHECK_IN_TIME = 15;
    private static final int CHECK_OUT_TIME = 11;

    private final String name;
    private final String description;

    private final List<Booking> bookings = new LinkedList<>();

    protected HotelRoom(String name, String description) {
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
    Makes sure booking has check-in at 15 and check-out at 11.
     */
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

        Interval newInterval = new Interval(checkIn, checkOut);

        if (this.available(newInterval)) {
            Booking booking = new Booking(newInterval, customer);
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
        HotelRoom resource = (HotelRoom) o;
        return Objects.equals(this.getName(), resource.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getName());
    }

    @Override
    public String toString() {
        return String.format(
                "Hotel room %s \"%s\" #bookings: %s",
                this.getName(),
                this.getDescription(),
                this.getBookings().size()
        );
    }
}

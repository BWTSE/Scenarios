package booking;

import java.time.LocalDateTime;
import java.time.temporal.ChronoField;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class HotelRoom implements Resource {

    private static final int I = 15;
    private static final int O = 11;

    private final String n;
    private final String d;

    private final List<Booking> bs = new LinkedList<>();

    protected HotelRoom(String n, String d) {
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
    Makes sure booking has check-in at 15 and check-out at 11.
     */
    public Optional<Booking> book(Interval i, User u) {
        LocalDateTime f = i.getStart()
                           .with(ChronoField.HOUR_OF_DAY, I)
                           .with(ChronoField.MINUTE_OF_HOUR, 0)
                           .with(ChronoField.SECOND_OF_MINUTE, 0)
                           .with(ChronoField.MICRO_OF_SECOND, 0);

        LocalDateTime t = i.getEnd()
                           .with(ChronoField.HOUR_OF_DAY, O)
                           .with(ChronoField.MINUTE_OF_HOUR, 0)
                           .with(ChronoField.SECOND_OF_MINUTE, 0)
                           .with(ChronoField.MICRO_OF_SECOND, 0);

        Interval i0 = new Interval(f, t);

        if (this.available(i0)) {
            Booking booking = new Booking(i0, u);
            bs.add(booking);
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
        HotelRoom h = (HotelRoom) o;
        return Objects.equals(this.getName(), h.getName());
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

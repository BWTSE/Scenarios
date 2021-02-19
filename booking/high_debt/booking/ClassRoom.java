package booking;

import java.time.LocalDateTime;
import java.time.temporal.ChronoField;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class ClassRoom implements Resource {

    private final String n;
    private final String d;

    private final List<Booking> bs = new LinkedList<>();

    protected ClassRoom(String n, String d) {
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

    private boolean available(Interval interval) {
        for (Booking booking : this.getBookings()) {
            if (booking.getInterval().overlapsWith(interval)) {
                return false;
            }
        }

        return true;
    }

    /*
    Only allows bookings that start on the hour. (Floors second and microsecond values)
    Does not allow 
    */
    public Optional<Booking> book(Interval i, User u) {
        if (
            i.getStart().with(ChronoField.HOUR_OF_DAY, 0).isAfter(i.getEnd())
            || !available(i)
            || !startAndEndAtHour(i)
            || !startBeforeEnd(i)
        ) {
            return Optional.empty();
        }

        Booking b = new Booking(i, u);
        bs.add(b);
        return Optional.of(b);
    }

    private static boolean startBeforeEnd(Interval i) {
        return !(i.getEnd().isBefore(i.getStart()) || i.getStart().isBefore(LocalDateTime.now()));
    }

    private static boolean startAndEndAtHour(Interval i) {
        LocalDateTime bst = i.getStart()
            .with(ChronoField.SECOND_OF_MINUTE, 0)
            .with(ChronoField.NANO_OF_SECOND, 0);
        LocalDateTime bed = i.getStart()
            .with(ChronoField.SECOND_OF_MINUTE, 0)
            .with(ChronoField.NANO_OF_SECOND, 0);

        return !(bed.getMinute() != 0|| bed.with(ChronoField.HOUR_OF_DAY, 0).isAfter(bst));
    }
}

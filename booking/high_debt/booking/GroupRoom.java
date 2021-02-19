package booking;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class GroupRoom implements Resource {

    private final String n;
    private final String d;

    private final List<Booking> bs = new LinkedList<>();

    protected GroupRoom(String n, String d) {
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
            if (b.getInterval().overlapsWith(i)) {
                return false;
            }
        }

        return true;
    }

    public Optional<Booking> book(Interval i, User u) {
        for (Booking b : this.getBookings()) {
            if (
                b.getInterval().getEnd().isAfter(LocalDateTime.now())
                && b.getBooker().equals(u)
                || !this.available(i) 
                || i.getEnd().isBefore(i.getStart())
                || i.getStart().isBefore(LocalDateTime.now())
            ) {
                return Optional.empty();
            }
        }

        Booking b = new Booking(i, u);
        bs.add(b);
        return Optional.of(b);
    }
}

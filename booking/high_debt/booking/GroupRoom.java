package booking;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class GroupRoom implements Room {

    private final String n;
    private final String d;
    private final boolean hasWhiteboard;

    private final List<Booking> bs = new LinkedList<>();

    protected GroupRoom(String n, String d, boolean hasWhiteboard) {
        this.n = n;
        this.d = d;
        this.hasWhiteboard = hasWhiteboard;
    }

    public String getName() {
        return this.n;
    }

    public String getDescription() {
        return this.d;
    }

    public boolean getHasWhiteboard() {
        return this.hasWhiteboard;
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

    /*
    Due to the popularity of some specific group rooms,
     no user is allowed to have more than one upcoming booking for a group room.
     */
    public Optional<Booking> book(Interval i, User u) {
        if (this.userHasBookingAlready(u) || !this.available(i) || !startBeforeEnd(i)) {
            return Optional.empty();
        }

        Booking b = new Booking(i, u);
        bs.add(b);
        return Optional.of(b);
    }

    private boolean userHasBookingAlready (User u) {
        for (Booking b : this.getBookings()) {
            if (b.getInterval().getEnd().isAfter(LocalDateTime.now()) && b.getBooker().equals(u)) {
                return true;
            }
        }

        return false;
    }

    private static boolean startBeforeEnd(Interval i) {
        return !(i.getEnd().isBefore(i.getStart()) || i.getStart().isBefore(LocalDateTime.now()));
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o)
            && Objects.equals(this.getHasWhiteboard(), ((GroupRoom) o).getHasWhiteboard());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), this.getHasWhiteboard());
    }

    @Override
    public String toString() {
        return String.format(
                "Room %s \"%s\" #bookings: %s",
                this.getName(),
                this.getDescription(),
                this.getBookings().size()
        );
    }
}

package booking;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;

public class GroupRoom extends Resource {

    private final boolean hasWhiteoard;
    
    public GroupRoom(String name, String description, boolean hasWhiteboard) {
        super(name, description);
        this.hasWhiteoard = hasWhiteboard;
    }

    public boolean getHasWhiteboard() {
        return this.hasWhiteoard;
    }

    /*
    Due to the popularity of some specific group rooms,
     no user is allowed to have more than one upcoming booking for a group room.
     */
    @Override
    public Optional<Booking> book(Interval interval, User customer) {
        for (Booking booking : this.getBookings()) {
            if (booking.getInterval().getEnd().isAfter(LocalDateTime.now())
                    && booking.getBooker().equals(customer)
            ) {
                return Optional.empty();
            }
        }
        
        return super.book(interval, customer);
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
}

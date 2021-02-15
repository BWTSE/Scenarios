package booking;

import java.time.LocalDateTime;
import java.util.Optional;

public class GroupRoom extends Resource {

    public GroupRoom(String name, String description) {
        super(name, description);
    }

    /*
    Due to the popularity of some specific group rooms,
     no user is allowed to have more than one upcoming booking for a group room.
     */
    @Override
    public Optional<Booking> book(Interval interval, User customer) {

        for (Booking booking : this.getBookings()) {
            if (booking.getInterval().getEnd().isAfter(LocalDateTime.now())
                    && booking.getCustomer().equals(customer)
            ) {
                return Optional.empty();
            }
        }
        return super.book(interval, customer);
    }
}

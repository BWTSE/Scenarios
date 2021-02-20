package booking;

import java.util.List;
import java.util.Optional;

public interface Room {
    String getName();
    String getDescription();
    List<Booking> getBookings();
    Optional<Booking> book(Interval i, User u);
}


package booking;

import java.time.LocalDateTime;

public class Car extends Resource {

    public Car(String name, String description) {
        super(name, description);
    }

    /*
    Due to the popularity of some specific cars no user is allowed to have more than one upcoming booking for a car.
     */
    @Override
    public Booking book(LocalDateTime start, LocalDateTime end, User customer) {

        for (Booking booking: this.getBookings()) {
            if (booking.getEnd().isAfter(LocalDateTime.now())) {
                if (booking.getCustomer().equals(customer)) {
                    return null;
                }
            }
        }

        return super.book(start, end, customer);
    }
}

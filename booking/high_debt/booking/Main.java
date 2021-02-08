package booking;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public class Main {
    private static final LocalDateTime n = LocalDateTime.now().plusMinutes(5);

    private static final User u1 = new User("Alice", "alice@crypto");
    private static final User u2 = new User("Bob", "bob@crypto");

    private static final HotelRoom r1 =
            new HotelRoom("921", "in The Stanley Hotel");
    private static final Cabin r2 = new Cabin("Whoville 26", "Home of the Who-steins");
    private static final Car r3 = new Car("ABC 123", "Volvo");

    private static final Collection<Resource> rs = List.of(
            r1,
            r2,
            r3
    );

    // Runs some simple tests for the booking system
    public static void main(String[] args) {

        // Tests basic booking functionality
        for (Resource r : rs) {
            Interval i1 = new Interval(n.plusDays(1), n.plusDays(8));
            Interval i2 = new Interval(n.plusDays(10), n.plusDays(15));

            Optional<Booking> b1 = r.book(i1, u1);
            if (b1.isEmpty()) {
                System.out.println("Resources should be bookable: " + r.toString());
            }

            Optional<Booking> b2 = r.book(i1, u2);
            if (b2.isPresent()) {
                System.out.println("Resources should not be double booked: " + r.toString());
            }

            Optional<Booking> b3 = r.book(i2, u2);
            if (b3.isEmpty()) {
                System.out.println(
                        "Resources should be bookable after previous booking: "
                                + r.toString()
                );
            }
        }

        // Test Cabin booking
        Optional<Booking> cb = r2.book(new Interval(now.plusDays(20), now.plusDays(21)), alice);
        if (cb.isPresent()) {
            System.out.println("Cabin should only be bookable for a minimum of 3 days");
        }

        // Test Hotel booking
        Optional<Booking> hb = r1.book(
                new Interval(n.plusDays(20), n.plusDays(25)),
                u2
        );
        if (hb.isPresent()) {
            if (hb.get().getInterval().getStart().getHour() != 15) {
                System.out.println("Hotel bookings should always start at 15");
            }
            if (hb.get().getInterval().getEnd().getHour() != 11) {
                System.out.println("Hotel bookings should always end at 11");
            }
        } else {
            System.out.println("Hotel bookings should be possible");
        }

        // Test Car booking
        // Previous booking is made in the loop above
        Optional<Booking> cb2 = r3.book(
                new Interval(n.plusDays(20), n.plusDays(25)),
                u1
        );
        if (cb2.isPresent()) {
            System.out.println("Every user should only be able to have one upcoming car booking");
        }

        System.out.println("If there were no previous output all tests passed");
    }
}

package booking;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public class Main {
    private static final LocalDateTime now = LocalDateTime.now().plusMinutes(5);

    private static final User u1 = new User("Alice", "alice@crypto");
    private static final User u2 = new User("Bob", "bob@crypto");

    private static final ClassRoom r1 = new ClassRoom("HB2", " at Hörsalsvägen 2");
    private static final ComputerRoom r2 = new ComputerRoom("J029", "in Jupiter", 7, 17);
    private static final GroupRoom r3 = new GroupRoom("J317", "in Jupiter");

    private static final Collection<Resource> rs = List.of(
            r1,
            r2,
            r3
    );

    // Runs some simple tests for the booking system
    public static void main(String[] args) {

        // Tests basic booking functionality
        for (Resource r : rs) {
            LocalDateTime t1 = LocalDateTime.of(2021, 6, 1, 12, 0, 0);
            Interval i1 = new Interval(t1, t1.plusHours(3));
            LocalDateTime t2 = t1.plusDays(1);
            Interval i2 = new Interval(t2, t2.plusHours(2));

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

        // Test GroupRoom booking
        LocalDateTime t = LocalDateTime.of(2021, 6, 1, 5, 0, 0);
        Optional<Booking> b = r2.book(new Interval(t, t.plusHours(3)), u2);
        if (b.isPresent()) {
            System.out.println("A computer room was successfully booked outside of office hours, this should not be possible");
        }

        t = LocalDateTime.of(2021, 6, 5, 5, 0, 0);
        b = r2.book(new Interval(t, t.plusHours(3)), u2);
        if (b.isPresent()) {
            System.out.println("A computer room was successfully booked during a weekend, this should not be possible");
        }

        t = LocalDateTime.of(2021, 6, 8, 12, 0, 0);
        b = r2.book(new Interval(t, t.plusHours(3)), u2);
        if (!b.isPresent()) {
            System.out.println("Failed to book computer room at 12:00 on a Tuesday, this should be possible");
        }

        // Test Class Room booking
        b = r1.book(
            new Interval(t, t.plusHours(1)),
            u2
        );
        if (b.isPresent()) {
            if (b.get().getInterval().getStart().getMinute() != 0) {
                System.out.println("Class room bookings should always start at whole hours.");
            }
        }

        b = r1.book(
            new Interval(t, t.plusDays(1)),
            u2
        );
        if (b.isPresent()) {
            System.out.println("Class room bookings not stretch over two days.");
        }

        // Test GroupRoom booking
        // Previous booking is made in the loop above
        Optional<Booking> groupRoomBooking = r3.book(
                new Interval(now.plusDays(20), now.plusDays(25)),
                u1
        );
        if (groupRoomBooking.isPresent()) {
            System.out.println("Every user should only be able to have one upcoming groupRoom booking");
        }

        System.out.println("If there were no previous output all tests passed");
    }
}

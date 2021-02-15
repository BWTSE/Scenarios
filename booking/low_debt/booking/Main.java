package booking;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public class Main {
    private static final LocalDateTime now = LocalDateTime.now().plusMinutes(5);

    private static final User alice = new User("Alice", "alice@crypto");
    private static final User bob = new User("Bob", "bob@crypto");

    private static final ClassRoom classRoom = new ClassRoom("HB2", " at Hörsalsvägen 2");
    private static final ComputerRoom computerRoom = new ComputerRoom("J029", "in Jupiter", 7, 17);
    private static final GroupRoom groupRoom = new GroupRoom("J317", "in Jupiter");

    private static final Collection<Resource> resources = List.of(
            classRoom,
            computerRoom,
            groupRoom
    );

    // Runs some simple tests for the booking system
    public static void main(String[] args) {

        // Tests basic booking functionality
        for (Resource resource : resources) {
            LocalDateTime testTime = LocalDateTime.of(2021, 6, 1, 12, 0, 0);
            Interval firstInterval = new Interval(testTime, testTime.plusHours(3));
            LocalDateTime testTime2 = testTime.plusDays(1);
            Interval secondInterval = new Interval(testTime2, testTime2.plusHours(2));

            Optional<Booking> booking1 = resource.book(firstInterval, alice);
            if (booking1.isEmpty()) {
                System.out.println("Resources should be bookable: " + resource.toString());
            }

            Optional<Booking> booking2 = resource.book(firstInterval, bob);
            if (booking2.isPresent()) {
                System.out.println("Resources should not be double booked: " + resource.toString());
            }

            Optional<Booking> booking3 = resource.book(secondInterval, bob);
            if (booking3.isEmpty()) {
                System.out.println(
                        "Resources should be bookable after previous booking: "
                                + resource.toString()
                );
            }
        }

        // Test GroupRoom booking
        LocalDateTime testTime = LocalDateTime.of(2021, 6, 1, 5, 0, 0);
        Optional<Booking> compRoomBooking = computerRoom.book(new Interval(testTime, testTime.plusHours(3)), bob);
        if (compRoomBooking.isPresent()) {
            System.out.println("A computer room was successfully booked outside of office hours, this should not be possible");
        }

        testTime = LocalDateTime.of(2021, 6, 5, 5, 0, 0);
        compRoomBooking = computerRoom.book(new Interval(testTime, testTime.plusHours(3)), bob);
        if (compRoomBooking.isPresent()) {
            System.out.println("A computer room was successfully booked during a weekend, this should not be possible");
        }

        testTime = LocalDateTime.of(2021, 6, 8, 12, 0, 0);
        compRoomBooking = computerRoom.book(new Interval(testTime, testTime.plusHours(3)), bob);
        if (!compRoomBooking.isPresent()) {
            System.out.println("Failed to book computer room at 12:00 on a Tuesday, this should be possible");
        }

        // Test Class Room booking
        Optional<Booking> classRoomBooking = classRoom.book(
            new Interval(testTime, testTime.plusHours(1)),
            bob
        );
        if (classRoomBooking.isPresent()) {
            if (classRoomBooking.get().getInterval().getStart().getMinute() != 0) {
                System.out.println("Class room bookings should always start at whole hours.");
            }
        }

        classRoomBooking = classRoom.book(
            new Interval(testTime, testTime.plusDays(1)),
            bob
        );
        if (classRoomBooking.isPresent()) {
            System.out.println("Class room bookings not stretch over two days.");
        }

        // Test GroupRoom booking
        // Previous booking is made in the loop above
        Optional<Booking> groupRoomBooking = groupRoom.book(
                new Interval(now.plusDays(20), now.plusDays(25)),
                alice
        );
        if (groupRoomBooking.isPresent()) {
            System.out.println("Every user should only be able to have one upcoming groupRoom booking");
        }

        System.out.println("If there were no previous output all tests passed");
    }
}

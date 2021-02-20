package booking;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public class Main {
    private static final LocalDateTime now = LocalDateTime.now().plusMinutes(5);

    private static final User alice = new User("Alice", "alice@crypto");
    private static final User bob = new User("Bob", "bob@crypto");

    private static final ClassRoom classRoom = new ClassRoom("HB2", " at Hörsalsvägen 2", true);
    private static final ComputerClassRoom computerRoom = new ComputerClassRoom("J029", "in Jupiter", false, 7, 17);
    private static final GroupRoom groupRoom = new GroupRoom("J317", "in Jupiter", true);

    private static final Collection<Room> rooms = List.of(
        classRoom,
        computerRoom,
        groupRoom
    );

    // Runs some simple tests for the booking system
    public static void main(String[] args) {

        // Tests basic booking functionality
        for (Room room : rooms) {
            LocalDateTime testTime = LocalDateTime.of(2021, 6, 1, 12, 0, 0);
            Interval firstInterval = new Interval(testTime, testTime.plusHours(3));
            LocalDateTime testTime2 = testTime.plusDays(1);
            Interval secondInterval = new Interval(testTime2, testTime2.plusHours(2));

            Optional<Booking> booking1 = room.book(firstInterval, alice);
            if (booking1.isEmpty()) {
                System.out.println("Rooms should be bookable: " + room.toString());
            }

            Optional<Booking> booking2 = room.book(firstInterval, bob);
            if (booking2.isPresent()) {
                System.out.println("Rooms should not be double booked: " + room.toString());
            }

            Optional<Booking> booking3 = room.book(secondInterval, bob);
            if (booking3.isEmpty()) {
                System.out.println(
                    "Rooms should be bookable after previous booking: "
                    + room.toString()
                );
            }
        }

        // Test ComputerRoom booking
        LocalDateTime testTime = LocalDateTime.of(2021, 6, 1, 5, 0, 0);
        Optional<Booking> compRoomBooking = computerRoom.book(new Interval(testTime, testTime.plusHours(3)), bob);
        if (compRoomBooking.isPresent()) {
            System.out.println("A computer room was successfully booked outside of allowed hours, this should not be possible");
        }

        testTime = LocalDateTime.of(2021, 6, 8, 12, 0, 0);
        compRoomBooking = computerRoom.book(new Interval(testTime, testTime.plusHours(3)), bob);
        if (!compRoomBooking.isPresent()) {
            System.out.println("Failed to book computer room during office allowed, this should be possible");
        }

        // Test Class Room booking
        Optional<Booking> classRoomBooking = classRoom.book(
            new Interval(testTime, testTime.plusHours(1)),
            bob
        );
        if (
            classRoomBooking.isPresent()
            && classRoomBooking.get().getInterval().getStart().getMinute() != 0
        ) {
                System.out.println("Class room bookings should always start at whole hours.");
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

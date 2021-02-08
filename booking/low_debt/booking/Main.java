package booking;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public class Main {
    private static final LocalDateTime now = LocalDateTime.now().plusMinutes(5);

    private static final User alice = new User("Alice", "alice@crypto");
    private static final User bob = new User("Bob", "bob@crypto");

    private static final HotelRoom hotelRoom =
            new HotelRoom("921", "in The Stanley Hotel");
    private static final Cabin cabin = new Cabin("Whoville 26", "Home of the Who-steins");
    private static final Car car = new Car("ABC 123", "Volvo");

    private static final Collection<Resource> resources = List.of(
            hotelRoom,
            cabin,
            car
    );

    // Runs some simple tests for the booking system
    public static void main(String[] args) {

        // Tests basic booking functionality
        for (Resource resource : resources) {
            Interval firstInterval = new Interval(now.plusDays(1), now.plusDays(8));
            Interval secondInterval = new Interval(now.plusDays(10), now.plusDays(15));

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

        // Test Cabin booking
        Optional<Booking> cabinBooking = cabin.book(new Interval(now.plusDays(20), now.plusDays(21)), bob);
        if (cabinBooking.isPresent()) {
            System.out.println("Cabin should only be bookable for a minimum of 3 days");
        }

        // Test Hotel booking
        Optional<Booking> hotelRoomBooking = hotelRoom.book(
                new Interval(now.plusDays(20), now.plusDays(25)),
                bob
        );
        if (hotelRoomBooking.isPresent()) {
            if (hotelRoomBooking.get().getInterval().getStart().getHour() != 15) {
                System.out.println("Hotel bookings should always start at 15");
            }
            if (hotelRoomBooking.get().getInterval().getEnd().getHour() != 11) {
                System.out.println("Hotel bookings should always end at 11");
            }
        } else {
            System.out.println("Hotel bookings should be possible");
        }

        // Test Car booking
        // Previous booking is made in the loop above
        Optional<Booking> carBooking2 = car.book(
                new Interval(now.plusDays(20), now.plusDays(25)),
                alice
        );
        if (carBooking2.isPresent()) {
            System.out.println("Every user should only be able to have one upcoming car booking");
        }

        System.out.println("If there were no previous output all tests passed");
    }
}

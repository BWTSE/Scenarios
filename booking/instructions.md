# Booking task

This system is a simple booking system, where there are currently two types of resources available: hotel rooms and cars.

Your task is to extend system with a new class for bookable cabins (`Cabin`).
The `Cabin` class should have the constructor `Cabin(String name, String description)` and be a subtype of `Resource`.

Cabins are different from hotel rooms because the minimum duration you can book them for is three (3) days.
Make sure to add this restriction to the `Cabin` class.

Feel free to modify the existing codebase in any way you want as long as you maintain existing functionality.

## Task checklist
* Added a `Cabin` class as a subtype of `Resource`
* Any Cabin bookings shorter than three (3) days are denied
* Passes all tests in `Main.java`

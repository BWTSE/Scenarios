# Booking task

This system is a simple booking system, where there are currently two types of resources available: hotel rooms and cars.

Your task is to extend system with a new class (`Cabin`) for bookable cabins.
The `Cabin` class should have the constructor `Cabin(String, String)` (arguments representing name and description) and be a subtype of `Resource`.

Cabins are different from hotel rooms and cars because the minimum duration you can book them for is three (3) days.
Make sure to add this restriction to the `Cabin` class.

Reference for LocalDateTime can be found here: <a href="https://docs.oracle.com/javase/8/docs/api/java/time/LocalDateTime.html" target="_blank" >Class LocalDateTime</a>

Feel free to modify the existing codebase in any way you want as long as you maintain existing functionality.

## Task checklist
* Added a `Cabin` class as a subtype of `Resource`
* Any Cabin bookings shorter than three (3) days are denied
* Passes all tests in `Main.java`

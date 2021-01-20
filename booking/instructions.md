# Booking task

Your task is to extend this booking system with a new class for bookable Cabins (`Cabin`).
The `Cabin` class should have the constructor `Cabin(String name, String description)` and be a subtype of `Resource`.

Cabins are different from hotel rooms because the minimum duration you cna book them for is three (3) days.
Make sure to add this restriction to the `Cabin` class. 
Hint: You can return null to deny a booking.

Make sure to uncomment the testcases for Cabins in `Main.java`.

Feel free to modify the existing codebase in any way you want as long as you maintain existing functionality.

## Task checklist
* Added a `Cabin` class as a subtype of `Resource`
* Any Cabin bookings shorter than three (3) days are denied
* Passes all tests in `Main.java`

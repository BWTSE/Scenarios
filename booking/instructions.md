# Booking task

This system is a simple booking system, where there are currently two types of resources available: classrooms and group rooms.

Your task is to extend the system with a new class (`ComputerRoom`) representing classrooms filled with computers. Because of the valuable hardware inside them, access to computer rooms is more limited than to other rooms.

Computer rooms should only be bookable between two full hours, (e.g. 13:00 to 14:00, but not 13:06 to 14:06) but, unlike other classrooms, they should only be available during Office Hours of working days, i.e 7:00 to 17:00 Monday through Friday. 
The new class should make sure these rules are not broken.
The class should be a subtype of `Resource` and the prewritten tests assume the constructor `ComputerRoom(String, String)` (arguments representing name and description).

Reference for LocalDateTime can be found here: <a href="https://docs.oracle.com/javase/8/docs/api/java/time/LocalDateTime.html" target="_blank" >Class LocalDateTime</a>

Feel free to modify the existing codebase (even the tests) in any way you want as long as you maintain existing functionality.

Tip: the editor supports a <a href="https://github.com/ajaxorg/ace/wiki/Default-Keyboard-Shortcuts" target="_blank" >set of shortcuts</a>.

## Task checklist
* Added a `Cabin` class as a subtype of `Resource`
* Any computer room bookings outside of office hours are rejected.
* Passes all tests in `Main.java`


Use the **Run Code** button below to test your solution.

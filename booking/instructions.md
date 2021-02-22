# Booking task

This system is a simple booking system, where there are currently two types of rooms available: classrooms and group rooms.

Your task is to add a `ComputerClassRoom` class that works like an ordinary classroom, but unlike them, they should also only be available during a specified period of time each day.

The prewritten tests assume the following constuctor for the new class: `ComputerClassRoom(String, String, boolean, int, int)` where the arguments represent: the name of the room, a description of the room, whether the room has a projector, the hour of opening, the hour of closing.

Feel free to modify the existing codebase (even the tests) in any way you want as long as you maintain existing functionality.

## Task checklist
* Added a `ComputerClassRoom` class that:
	* has all of the same booking limitations that `ClassRoom` has.
	* can only be booked between the opening and closing hours passed to the constructor.
	* can be stored in collections of `Room` and be used as a substitute for `Room`.
* The code compiles and passes all tests in `Main.java` 
	* Use the **Run Code** button below to test your solution

<br>

Reference for LocalDateTime can be found here: <a href="https://docs.oracle.com/javase/8/docs/api/java/time/LocalDateTime.html" target="_blank" >Class LocalDateTime</a>

Tip: the editor supports a <a href="https://github.com/ajaxorg/ace/wiki/Default-Keyboard-Shortcuts" target="_blank" >set of shortcuts</a>.

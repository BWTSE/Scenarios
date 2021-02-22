# Tickets Task

This is a system that models a public transport ticket system. It finds the ticket types that are valid for a specific trip and user at a specific time.

Your task is to add a `TicketTypeSeasonalRestricted` class that works like the seasonal ticket type but only is valid for users with certain occupations, i.e. students and/or retirees.

The prewritten tests assume the following constuctor for the new class: `TicketTypeSeasonalRestricted(String, double, Set<Zone>, Season, Set<User.Occupation>)` where the arguments represent: the ticket name, the ticket price, the zones the ticket is valid for, the seson duration which the ticket is valid, and the occupations for which it is valid.

Feel free to modify the existing codebase (even the tests) in any way you want as long as you maintain the existing functionality.

## Task checklist
* Added a `TicketTypeSeasonalRestricted` class that:
	* has all of the restraints that `TicketTypeSeasonal` has.
	* only is valid for the set of occupations passed to the constructor.
	* can be stored in collections of `TicketType` and be used as a substitute for `TicketType`.
* The code compiles and passes all tests in `Main.java` 
	* Use the **Run Code** button below to test your solution

<br>

Tip: the editor supports a <a href="https://github.com/ajaxorg/ace/wiki/Default-Keyboard-Shortcuts" target="_blank" >set of shortcuts</a>.
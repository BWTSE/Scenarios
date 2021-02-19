# Tickets Task

This is a system that models a public transport ticket system. It finds the tickets that are valid for a specific trip and user at a specific time.

Your task is to add a TicketTypePeriodRestricted class that works like a period ticket but only is valid for certain groups, i.e. students and/or retirees.

The new class should be a subtype of `TicketType` and the prewritten tests assume the following constuctor: `TicketTypePeriodRestricted(String, double, Set<Zone>, long, Set<User.Occupation>)` (where the arguments represent the name of the ticket name, the ticket price, the zones the ticket is valid for, the duration for which the ticket is valid and the occupations for which it is valid).

Feel free to modify the existing codebase (even the tests) in any way you want as long as you maintain the existing functionality.

Tip: the editor supports a <a href="https://github.com/ajaxorg/ace/wiki/Default-Keyboard-Shortcuts" target="_blank" >set of shortcuts</a>.

## Task checklist
* Added a `TicketTypePeriodRestricted` as a subtype of `TicketType`.
* Restricted period tickets are only valid for the set of occupations passed to the constructor.
* Passes all tests in `Main.java`


Use the **Run Code** button below to test your solution.

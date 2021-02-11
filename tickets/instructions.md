# Tickets Task

This is a system that models a public transport ticket system. It finds the tickets that are valid for a specific trip and user at a specific time.

Your task is to add a `TicketTypePeriodRestricted` class that is only valid for certain groups, i.e. student and/or retirees.

The new class should be a subtype of `TicketType` and have the following constructor: TicketTypeRestrictedPeriod(String, double, Set<Zone>, long, Set<User.Occupation>)` (where the arguments represent the name of the ticket name, the ticket price, the zones the ticket is valid for, the duration for which the ticket is valid and the occupations for which it is valid).

Feel free to modify the existing codebase in any way you want as long as you maintain existing functionality.

## Task checklist
* Added a `TicketTypePeriodRestricted` as a subtype of `TicketType`.
* Passes all tests in `Main.java`

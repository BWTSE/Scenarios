# Tickets Task

This is a system that models a public transport ticket system. It finds the tickets that are valid for a specific trip and user at a specific time.

Your task is to add a `RestrictedPeriodTicker` class that is only valid for certain groups, i.e. student and/or retirees.

The new class should implement the interface `Ticket` and have the following constructor: `RestrictedPeriodTicket(String, String, double, List<Zone>, long, Set<User.primaryOccupaton> allowedOccupations)�.

Feel free to modify the existing codebase in any way you want as long as you maintain existing functionality.

## Task checklist
* Added a `RestrictedPeriodTicket` as a subtype of `Ticket`.
* Uncomment the lines in `TicketFinder` that instantiates RestrictedTicket.
* Passes all tests in `Main.java`

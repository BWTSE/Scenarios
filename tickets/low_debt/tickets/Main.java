package tickets;

import java.util.List;

public class Main {
    
    private static final User alice = new User("Alice", "alice@crypto", 32, false, false);
    private static final User bob = new User("Bob", "bob@crypto", 75, false, true);
    private static final User carl = new User("Carl", "carl@crypto", 21, true, false);
    private static TicketFinder ticketFinder = new TicketFinder();

	public static void main(String[] args) {
        List<TicketType> ticketTypeList = ticketFinder.find(carl, Zone.CENTRAL, Zone.CENTRAL, 2000, 5);
        for (TicketType ticketType : ticketTypeList) {
            assert(ticketType.getName() != "7-Day Old People Ticket");
        }

        ticketTypeList = ticketFinder.find(bob, Zone.CENTRAL, Zone.CENTRAL, 2000, 5);
        for (TicketType ticketType : ticketTypeList) {
            assert(ticketType.getName() != "7-Day Old People Ticket");
        }
	}
}

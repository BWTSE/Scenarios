package tickets;

import java.util.List;

public class Main {
    
    private static final User alice = new User("Alice", "alice@crypto", 32, false, false);
    private static final User bob = new User("Bob", "bob@crypto", 75, false, true);
    private static final User carl = new User("Carl", "carl@crypto", 21, true, false);
    private static final TicketFinder ticketFinder = new TicketFinder();

	public static void main(String[] args) {
        List<Ticket> ticketList = ticketFinder.getTicketList();
        System.out.println(ticketList.get(3).getValidZones());
        System.out.println(ticketList.get(3).getValidZones().contains(Zone.SUBURB));

        System.out.print(ticketFinder.find(bob, Zone.CENTRAL, Zone.CENTRAL, 2000, 14));
	}
}

package tickets;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Optional;
import java.util.Set;

public class TicketFinder {

    private final Set<TicketType> ticketTypes;

    public TicketFinder(Collection<TicketType> ticketTypeList) {
        this.ticketTypes = new HashSet<>(ticketTypeList);
    }

    public Set<TicketType> find(User user, Trip trip, double maxPrice) {
        Set<TicketType> results = find(user, trip);

        for (Iterator<TicketType> it = results.iterator(); it.hasNext();) {
            TicketType element = it.next();
            if (element.getPrice() >= maxPrice) {
                it.remove();
            }
        }

        return results;
    }

    public Set<TicketType> find(User user, Trip trip) {
        Set<TicketType> results = new HashSet<>();

        for (tickets.TicketType TicketType : ticketTypes) {
            if (TicketType.isValidFor(trip, user)) {
                results.add(TicketType);
            }
        }

        return results;
    }

    public Optional<Ticket> purchaseTicket(User user, TicketType ticketType, Trip trip) {
        if(find(user, trip).contains(ticketType)) {
            return Optional.of(new Ticket(ticketType, user));
        } else {
            return Optional.empty();
        }
    }

    public Set<TicketType> getTicketTypes() {
        return new HashSet<>(this.ticketTypes);
    }
}

package tickets;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Optional;
import java.util.Set;

public class TicketFinder {

    private final Set<AbstractTicketType> ticketTypes;

    public TicketFinder(Collection<AbstractTicketType> ticketTypeList) {
        this.ticketTypes = new HashSet<>(ticketTypeList);
    }

    public Set<AbstractTicketType> find(User user, Trip trip, double maxPrice) {
        Set<AbstractTicketType> results = find(user, trip);

        for (Iterator<AbstractTicketType> it = results.iterator(); it.hasNext();) {
            AbstractTicketType element = it.next();
            if (element.getPrice() >= maxPrice) {
                it.remove();
            }
        }

        return results;
    }

    public Set<AbstractTicketType> find(User user, Trip trip) {
        Set<AbstractTicketType> results = new HashSet<>();
        for (AbstractTicketType TicketType : ticketTypes) {
            if (TicketType.isValidFor(trip, user)) {
                results.add(TicketType);
            }
        }
        return results;
    }

    public Optional<Ticket> purchaseTicket(User user, AbstractTicketType ticketType, Trip trip) {
        if(find(user, trip).contains(ticketType)) {
            return Optional.of(new Ticket(ticketType, user));
        } else {
            return Optional.empty();
        }
    }

    public Set<AbstractTicketType> getTicketTypes() {
        return new HashSet<>(this.ticketTypes);
    }
}

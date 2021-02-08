package tickets;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Optional;
import java.util.Set;

public class TicketFinder {

    private final Set<TicketType> ttset;

    public TicketFinder(Collection<TicketType> ttset) {
        this.ttset = new HashSet<>(ttset);
    }

    public Set<TicketType> find(User u, Trip t, double maxp) {
        Set<TicketType> r = find(u, t);

        for (Iterator<TicketType> it = r.iterator(); it.hasNext();) {
            TicketType e = it.next();
            if (e.getPrice() >= maxp) {
                it.remove();
            }
        }
        return r;
    }

    public Set<TicketType> find(User u, Trip t) {
        Set<TicketType> r = new HashSet<>();
        for (TicketType TicketType : ttset) {
            if (TicketType.isValidFor(t, u)) {
                r.add(TicketType);
            }
        }
        return r;
    }

    public Optional<Ticket> purchaseTicket(User u, TicketType tt, Trip t) {
        if(find(u, t).contains(tt)) {
            return Optional.of(new Ticket(tt, u));
        } else {
            return Optional.empty();
        }
    }

    public Set<TicketType> getticketTypes() {
        return new HashSet<>(this.ttset);
    }
}

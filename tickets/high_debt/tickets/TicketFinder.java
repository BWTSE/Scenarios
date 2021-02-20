package tickets;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Optional;
import java.util.Set;

public class TicketFinder {

    private final Set<TicketType> ts;

    public TicketFinder(Collection<TicketType> ts) {
        this.ts = new HashSet<>(ts);
    }

    public Set<TicketType> find(User u, Trip t, double p) {
        Set<TicketType> r = find(u, t);

        for (Iterator<TicketType> it = r.iterator(); it.hasNext();) {
            TicketType e = it.next();
            if (e.getPrice() >= p) {
                it.remove();
            }
        }

        return r;
    }

    public Set<TicketType> find(User u, Trip t) {
        Set<TicketType> r = new HashSet<>();

        for (TicketType TicketType : ts) {
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
        return new HashSet<>(this.ts);
    }
}

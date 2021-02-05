package tickets;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class TicketFinder {

    private final List<TicketTypeInterface> ttlist;

    public TicketFinder(Collection<TicketTypeInterface> ttlist) {
        this.ttlist = new LinkedList<>(ttlist);
    }

    public List<TicketTypeInterface> find(
        User u, 
        Trip t,
        double maxp
    ) {
        List<TicketTypeInterface> r = new LinkedList<>();
        for (TicketTypeInterface TicketType : ttlist) {
            if (TicketType.isValidFor(
                    t, 
                    u
                    ) 
                && TicketType.getPrice() <= maxp) {
                r.add(TicketType);
            }
        }
        return r;
    }

    public Optional<Ticket> purchaseTicket(
            User u, 
            TicketTypeInterface tt, 
            Trip t
    ) {
        if(find(
            u, 
            t,
            Double.MAX_VALUE
            ).contains(tt)) {
            return Optional.of(new Ticket(tt, u));
        } else {
            return Optional.empty();
        }
    }

    public List<TicketTypeInterface> getticketTypeList() {
        return new LinkedList<>(this.ttlist);
    }
}

package tickets;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class TicketFinder {

    private final List<TicketType> ttlist;

    public TicketFinder(Collection<TicketType> ttlist) {
        this.ttlist = new LinkedList<>(ttlist);
    }

    public List<TicketType> find(
        User u, 
        Trip t,
        double maxp
    ) {
        List<TicketType> r = new LinkedList<>();
        for (TicketType TicketType : ttlist) {
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
            TicketType tt, 
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

    public List<TicketType> getticketTypeList() {
        return new LinkedList<>(this.ttlist);
    }
}

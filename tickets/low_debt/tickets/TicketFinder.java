package tickets;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class TicketFinder {

    private final List<AbstractTicketType> ticketTypeList;

    public TicketFinder(Collection<AbstractTicketType> ticketTypeList) {
        this.ticketTypeList = new LinkedList<>(ticketTypeList);
    }

    public List<AbstractTicketType> find(
        User user, 
        Trip trip,
        double maxPrice,
        LocalDateTime tripStartHour
    ) {
        List<AbstractTicketType> results = new LinkedList<>();
        for (AbstractTicketType TicketType : ticketTypeList) {
            if (TicketType.isValidFor(
                    trip, 
                    user, 
                    tripStartHour
                    ) 
                && TicketType.getPrice() <= maxPrice) {
                results.add(TicketType);
            }
        }
        return results;
    }

    public Optional<Ticket> purchaseTicket(
            User user, 
            AbstractTicketType ticketType, 
            Trip trip,
            LocalDateTime tripstartHour
    ) {
        if(find(
            user, 
            trip,
            Double.MAX_VALUE, 
            tripstartHour
            ).contains(ticketType)) {
            return Optional.of(new Ticket(ticketType, user));
        } else {
            return Optional.empty();
        }
    }

    public List<AbstractTicketType> getticketTypeList() {
        return new LinkedList<>(this.ticketTypeList);
    }
}

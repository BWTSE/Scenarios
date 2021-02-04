package tickets;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class TicketFinder {

    private final List<AbstractTicketType> ticketTypeList;

    public TicketFinder(List<AbstractTicketType> ticketTypeList) {
        this.ticketTypeList = new LinkedList<>(ticketTypeList);
    }

    public List<AbstractTicketType> find(
        User user, 
        Zone startZone,
        Zone endZone,
        double maxPrice,
        LocalDateTime tripStartHour
    ) {
        List<AbstractTicketType> results = new LinkedList<>();
        for (AbstractTicketType TicketType : ticketTypeList) {
            if (TicketType.isValidFor(
                    startZone, 
                    endZone, 
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
            Zone startZone, 
            Zone endZone, 
            LocalDateTime tripstartHour
    ) {
        if(find(
            user, 
            startZone, 
            endZone, 
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

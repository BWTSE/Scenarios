package tickets;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class Ticket {
    private final AbstractTicketType ticketType;
    private final User user;
    private final LocalDateTime timeOfPurchase;

    public Ticket(AbstractTicketType ticketType, User user) {
        this.ticketType = ticketType;
        this.user = user;
        this.timeOfPurchase = LocalDateTime.now();
    }

    public AbstractTicketType getTicketType() {
        return this.ticketType;
    }

    public User getUser() {
        return this.user;
    }

    public LocalDateTime getTimeOfPurchase() {
        return this.timeOfPurchase;
    }

    public boolean isValidFor(
        Zone startZone, 
        Zone endZone, 
        User user, 
        LocalDateTime 
        tripStartHour
    ) {
        return ticketType.isValidFor(startZone, endZone, user, tripStartHour);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }
        Ticket ticket = (Ticket) o;
        return Objects.equals(this.getTicketType(), ticket.getTicketType());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getTicketType().getName());
    }

    @Override
    public String toString() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");
        return String.format(
            "Ticket: %s Valid for user: %s time of purchase: %s",
            this.getTicketType().getName(),
            this.getUser().getName(),
            dateTimeFormatter.format(this.getTimeOfPurchase())
        );
    }
}
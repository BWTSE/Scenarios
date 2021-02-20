package tickets;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class Ticket {
    private final TicketType tt;
    private final User u;
    private final LocalDateTime t;

    public Ticket(TicketType tt, User u) {
        this.tt = tt;
        this.u = u;
        this.t = LocalDateTime.now();
    }

    public TicketType getTicketType() {
        return this.tt;
    }

    public User getUser() {
        return this.u;
    }

    public LocalDateTime getTimeOfPurchase() {
        return this.t;
    }

    public boolean isValidFor(Trip t, User u) {
        return tt.isValidFor(t, u);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }

        Ticket t2 = (Ticket) o;
        return Objects.equals(this.getTicketType(), t2.getTicketType())
            && Objects.equals(this.getUser(), t2.getUser())
            && Objects.equals(this.getTimeOfPurchase(), t2.getTimeOfPurchase());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getTicketType().getName());
    }

    @Override
    public String toString() {
        DateTimeFormatter f = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");

        return String.format(
            "Ticket: %s Valid for u: %s time of purchase: %s",
            this.getTicketType().getName(),
            this.getUser().getName(),
            f.format(this.getTimeOfPurchase())
        );
    }
}

package tickets;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class TicketFinder {

    private final List<Ticket> ticketList = new LinkedList<Ticket>();

    public TicketFinder() {
        populateTicketList();
    }

    public List<Ticket> find(User user, Zone startZone, Zone endZone, double maxPrice, int tripStartHour) {
        List<Ticket> results = new LinkedList<Ticket>();
        for (Ticket ticket : ticketList) {
            if (ticket.isValidFor(startZone, endZone, user, tripStartHour) && ticket.getPrice() <= maxPrice) {
                results.add(ticket);
            }
        }
        return results;
    }

    public List<Ticket> getTicketList() {
        return this.ticketList;
    }

    private void populateTicketList() {

        ticketList.add(new SingleTicket("Single Central Day",
            "One trip within the CENTRAL region between 5:00 and 22:00", 
            29, 
            Arrays.asList(Zone.CENTRAL), 
            5, 
            22));

        ticketList.add(new SingleTicket("Single Central Night",
            "One trip within the CENTRAL region between 22:00 and 5:00", 
            39, 
            Arrays.asList(Zone.CENTRAL), 
            22, 
            5));

        ticketList.add(new SingleTicket("Single Central + Suburbs Day",
            "One trip within the CENTRAL and SUBURB regions between 4:00 and 00:00", 
            49, 
            Arrays.asList(Zone.CENTRAL, Zone.SUBURB), 
            22, 
            5));

        ticketList.add(new SingleTicket("Single All Regions Day",
            "One trip within all covered regions between 4:00 and 00:00", 
            69, 
            Arrays.asList(Zone.CENTRAL, Zone.SUBURB, Zone.RURAL), 
            22, 
            5));

        ticketList.add(new PeriodTicket("3-Day Period Central",
            "Unlimited travel for 3 days within the central region", 
            149, 
            Arrays.asList(Zone.CENTRAL), 
            259200000L));

        ticketList.add(new PeriodTicket("30-Day Period Central",
            "Unlimited travel for 30 days within the central region", 
            749, 
            Arrays.asList(Zone.CENTRAL), 
            2592000000L));

        ticketList.add(new PeriodTicket("30-Day Golden Ticket",
            "Unlimited travel for 30 days within all regions", 
            1999, 
            Arrays.asList(Zone.CENTRAL, Zone.SUBURB, Zone.RURAL), 
            2592000000L));

        ticketList.add(new RestrictedPeriodTicket("30-Day Student Ticket",
            "Unlimited travel for 30 days within all regions - for students", 
            199, 
            Arrays.asList(Zone.CENTRAL, Zone.SUBURB, Zone.RURAL), 
            2592000000L,
            true, 
            false));

        ticketList.add(new RestrictedPeriodTicket("7-Old People Ticket",
            "7 day travel whithin CETRAL and SUBURB regions for retirees", 
            199, 
            Arrays.asList(Zone.CENTRAL, Zone.SUBURB), 
            2592000000L,
            false, 
            true));
    }
}

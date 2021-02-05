package tickets;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class Main {
    
    private static final User alice = new User("Alice", "alice@crypto", 32, User.PrimaryOccupation.ADULT);
    private static final User bob = new User("Bob", "bob@crypto", 75, User.PrimaryOccupation.RETIREE);
    private static final User carl = new User("Carl", "carl@crypto", 21, User.PrimaryOccupation.STUDENT);



    public static void main(String[] args) {
        TicketFinder ticketFinder = new TicketFinder(buildTicketTypeList());

        LocalDateTime time = LocalDateTime.of(2021, 3, 15, 5, 0);

        List<AbstractTicketType> applicableTickets = ticketFinder.find(
            carl, 
            new Trip(Zone.CENTRAL, Zone.CENTRAL, time), 
            2000);
        Optional<Ticket> ticketOpt;

        for (AbstractTicketType ticketType : applicableTickets) {
            if(ticketType.getName().equals("Single All Regions Day")) {
                ticketOpt = ticketFinder.purchaseTicket(
                    carl, 
                    ticketType, 
                    new Trip(Zone.CENTRAL, Zone.CENTRAL, time));
                if (ticketOpt.isPresent()) {
                    System.out.println(ticketOpt.get().toString());
                }
            }
        }
    }

    // Mocking database access.
    private static List<AbstractTicketType> buildTicketTypeList() {

        List<AbstractTicketType> ticketTypeList = new LinkedList<>();
        
        ticketTypeList.add(new TicketTypeSingle(
            "Single Central Day",
            "One trip within the CENTRAL region between 5:00 and 22:00", 
            29, 
            Collections.singletonList(Zone.CENTRAL), 
            5, 
            22
            )
        );

        ticketTypeList.add(new TicketTypeSingle(
            "Single Central Night",
            "One trip within the CENTRAL region between 22:00 and 5:00", 
            39, 
            Collections.singletonList(Zone.CENTRAL), 
            22, 
            5
            )
        );

        ticketTypeList.add(new TicketTypeSingle(
            "Single Central + Suburbs Day",
            "One trip within the CENTRAL and SUBURB regions between 4:00 and 00:00", 
            49, 
            Arrays.asList(Zone.CENTRAL, Zone.SUBURB), 
            4, 
            0
            )
        );

        ticketTypeList.add(new TicketTypeSingle(
            "Single All Regions Day",
            "One trip within all covered regions between 4:00 and 00:00", 
            69, 
            Arrays.asList(Zone.CENTRAL, Zone.SUBURB, Zone.RURAL), 
            5, 
            22
            )
        );

        ticketTypeList.add(new TicketTypePeriod(
            "3-Day Period Central",
            "Unlimited travel for 3 days within the central region", 
            149, 
            Collections.singletonList(Zone.CENTRAL), 
            259_200_000L
            )
        );

        ticketTypeList.add(new TicketTypePeriod(
            "30-Day Period Central",
            "Unlimited travel for 30 days within the central region", 
            749, 
            Collections.singletonList(Zone.CENTRAL), 
            2_592_000_000L
            )
        );

        ticketTypeList.add(new TicketTypePeriod(
            "30-Day Golden TicketType",
            "Unlimited travel for 30 days within all regions", 
            1999, 
            Arrays.asList(Zone.CENTRAL, Zone.SUBURB, Zone.RURAL), 
            2_592_000_000L
            )
        );
        /*
        ticketTypeList.add(new TicketTypePeriodRestricted(
            "30-Day Student TicketType",
            "Unlimited travel for 30 days within all regions - for students", 
            199, 
            Arrays.asList(Zone.CENTRAL, Zone.SUBURB, Zone.RURAL), 
            2592000000L,
            true, 
            false
            )
        );

        ticketTypeList.add(new TicketTypePeriodRestricted(
            "7-Day Old People TicketType",
            "7 day travel whithin CETRAL and SUBURB regions for retirees", 
            199, 
            Arrays.asList(Zone.CENTRAL, Zone.SUBURB), 
            2592000000L,
            false, 
            true
            )
        );
        */
        return ticketTypeList;
    }
}

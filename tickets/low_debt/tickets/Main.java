package tickets;

import java.time.LocalDateTime;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.Set;

public class Main {
    
    private static final User alice = new User("Alice", "alice@crypto", 32, User.Occupation.ADULT);
    private static final User bob = new User("Bob", "bob@crypto", 75, User.Occupation.RETIREE);
    private static final User carl = new User("Carl", "carl@crypto", 21, User.Occupation.STUDENT);
    
    private static Set<Zone> centrumOnly = EnumSet.of(Zone.CENTRAL);
    private static Set<Zone> centrumPlus = EnumSet.of(Zone.CENTRAL, Zone.SUBURB);
    private static Set<Zone> allZones = EnumSet.of(Zone.CENTRAL, Zone.SUBURB, Zone.RURAL);
    
    private static final AbstractTicketType centralSingleDay =
        new TicketTypeSingle("Single Central Day", 29, centrumOnly, 5, 22);
    private static final AbstractTicketType threeDayCentrum = 
        new TicketTypePeriod("3-Day Period Central", 149, centrumOnly, 259_200_000L);
    private static final AbstractTicketType monthCentrum = 
        new TicketTypePeriod("1-Month Central", 749, centrumOnly, 2_592_000_000L);
    private static final AbstractTicketType centrumPlusSingle = 
        new TicketTypeSingle("Single Central + Suburbs Day", 49, centrumPlus, 4, 0);
    private static final AbstractTicketType retireeSevenDayCPlus =
        new TicketTypePeriodRestricted("7-Day Old People TicketType", 199.0, centrumPlus, 2592000000L, 
        EnumSet.of(User.Occupation.RETIREE));
    private static final AbstractTicketType allSingleDay = 
        new TicketTypeSingle("Single All Regions Day", 69, allZones,5, 22);
    private static final AbstractTicketType goldenTicket =
        new TicketTypePeriod("30-Day Golden Ticket", 1999, allZones, 2_592_000_000L);
    private static final AbstractTicketType monthStudent =
    new TicketTypePeriodRestricted( "30-Day Student TicketType", 199.0, allZones, 2592000000L, 
        EnumSet.of(User.Occupation.STUDENT));

    public static void main(String[] args) {

        final Set<AbstractTicketType> ticketTypeList = new HashSet<>();

        ticketTypeList.add(centralSingleDay);
        ticketTypeList.add(threeDayCentrum);
        ticketTypeList.add(monthCentrum);
        ticketTypeList.add(centrumPlusSingle);
        ticketTypeList.add(allSingleDay);
        ticketTypeList.add(goldenTicket);

        TicketFinder ticketFinder = new TicketFinder(ticketTypeList);

        // Tests
        LocalDateTime time = LocalDateTime.of(2021, 3, 15, 5, 0);

        Set<AbstractTicketType> aliceTickets = 
            ticketFinder.find(alice, new Trip(Zone.CENTRAL, Zone.SUBURB, time), 2000);
        if (aliceTickets.contains(retireeSevenDayCPlus) || aliceTickets.contains(monthStudent)) {
                System.out.println("Alice (adult) found retiree or student ticket");
        }
        for (AbstractTicketType ticket : aliceTickets) {
            if (!ticket.getValidZones().contains(Zone.SUBURB)
                && !ticket.getValidZones().contains(Zone.CENTRAL)); {
                    System.out.println("Alice found ticket that isnt valid for her trip.");
            }
        }
    
        Set<AbstractTicketType> bobTickets = 
            ticketFinder.find(bob, new Trip(Zone.CENTRAL, Zone.CENTRAL, time), 2000);
            if (bobTickets.contains(monthStudent)) {
                System.out.println("Bob (retiree) found student ticket");
        }
        for (AbstractTicketType ticket : bobTickets) {
            if (!ticket.getValidZones().contains(Zone.CENTRAL)) {
                    System.out.println("Bob found ticket that isn't valid for his trip.");
            }
        }

        Set<AbstractTicketType> carlTickets = 
            ticketFinder.find(carl, new Trip(Zone.CENTRAL, Zone.CENTRAL, time), 2000);
            if (carlTickets.contains(retireeSevenDayCPlus)) {
                System.out.println("Carl (student) found retiree ticket");
        }
        for (AbstractTicketType ticket : carlTickets) {
            if (!ticket.getValidZones().contains(Zone.CENTRAL)) {
                    System.out.println("Carl found ticket that isn't valid for his trip.");
            }
        }

        System.out.println("If there were no previous output all tests passed");
    }
}

package tickets;

import java.time.LocalDateTime;
import java.util.Arrays;
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
    
    private static final TicketType centralSingleDay =
        new TicketTypeSingle("Single Central Day", 29, centrumOnly, 5, 22);
    private static final TicketType centralWinter =
        new TicketTypeSeasonal("Central Winter Ticket", 149, centrumOnly, Season.WINTER);
    private static final TicketType centralSummer =
        new TicketTypeSeasonal("Central Summer Ticket", 749, centrumOnly, Season.SUMMER);
    private static final TicketType centrumPlusSingle =
        new TicketTypeSingle("Single Central + Suburbs Day", 49, centrumPlus, 4, 0);
    private static final TicketType retireeWinter =
        new TicketTypeSeasonalRestricted("Retiree Winter Ticket", 199.0, centrumPlus, Season.WINTER, 
        EnumSet.of(User.Occupation.RETIREE));
    private static final TicketType allSingleDay =
        new TicketTypeSingle("Single All Regions Day", 69, allZones,5, 22);
    private static final TicketType studentWinter =
        new TicketTypeSeasonalRestricted( "Student Winter Ticket", 199.0, allZones, Season.WINTER, 
        EnumSet.of(User.Occupation.STUDENT));

    public static void main(String[] args) {

        final Set<TicketType> ticketTypeList = new HashSet<>();

        ticketTypeList.add(centralSingleDay);
        ticketTypeList.add(centralWinter);
        ticketTypeList.add(centralSummer);
        ticketTypeList.add(centrumPlusSingle);
        ticketTypeList.add(allSingleDay);
        ticketTypeList.add(retireeWinter);
        ticketTypeList.add(studentWinter);


        TicketFinder ticketFinder = new TicketFinder(ticketTypeList);

        // Tests
        LocalDateTime time = LocalDateTime.of(2021, 12, 15, 5, 0);

        Set<TicketType> aliceTickets =
            ticketFinder.find(alice, new Trip(Zone.CENTRAL, Zone.SUBURB, time), 2000);

        if (aliceTickets.containsAll(new HashSet<>(Arrays.asList(studentWinter, retireeWinter)))) {
            System.out.println("Alice (adult) found a retiree or student ticket");
        }

        for (TicketType ticket : aliceTickets) {
            if (!ticket.getValidZones().containsAll(EnumSet.of(Zone.SUBURB, Zone.CENTRAL))) {
                System.out.println("Alice found ticket that isnt valid for her trip:");
                System.out.println(ticket.getName());
            }
        }
    
        Set<TicketType> bobTickets =
            ticketFinder.find(bob, new Trip(Zone.CENTRAL, Zone.CENTRAL, time), 2000);
        if (bobTickets.contains(studentWinter)) {
            System.out.println("Bob (retiree) found the student ticket.");
        }
        if (!bobTickets.contains(retireeWinter)) {
            System.out.println("Bob (retiree) didn't find the retiree ticket.");
        }
        for (TicketType ticket : bobTickets) {
            if (!ticket.getValidZones().contains(Zone.CENTRAL)) {
                System.out.println("Bob found ticket that isn't valid for his trip:");
                System.out.println(ticket.getName());
            }
        }

        Set<TicketType> carlTickets =
            ticketFinder.find(carl, new Trip(Zone.CENTRAL, Zone.CENTRAL, time), 2000);
        if (carlTickets.contains(retireeWinter)) {
            System.out.println("Carl (student) found the retiree ticket.");
        }
        if (!carlTickets.contains(studentWinter)) {
            System.out.println("Carl (student) didn't find the student ticket.");
        }

        for (TicketType ticket : carlTickets) {
            if (!ticket.getValidZones().contains(Zone.CENTRAL)) {
                System.out.println("Carl found ticket that isn't valid for his trip:");
                System.out.println(ticket.getName());
            }
        }

        System.out.println("If there were no previous output all tests passed");
    }
}

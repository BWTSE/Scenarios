package tickets;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.Set;

public class Main {
    
    private static final User a = new User("Alice", "alice@crypto", 32, User.Occupation.ADULT);
    private static final User b = new User("Bob", "bob@crypto", 75, User.Occupation.RETIREE);
    private static final User c = new User("Carl", "carl@crypto", 21, User.Occupation.STUDENT);
    

    private static Set<Zone> centrumOnly = EnumSet.of(Zone.CENTRAL);
    private static Set<Zone> centrumPlus = EnumSet.of(Zone.CENTRAL, Zone.SUBURB);
    private static Set<Zone> allZones = EnumSet.of(Zone.CENTRAL, Zone.SUBURB, Zone.RURAL);

    private static final TicketType csd =
        new TicketTypeSingle("Single Central Day", 29, centrumOnly, 5, 22);
    private static final TicketType ctd = 
        new TicketTypePeriod("3-Day Period Central", 149, centrumOnly, 259_200_000L);
    private static final TicketType cm = 
        new TicketTypePeriod("1-Month Central", 749, centrumOnly, 2_592_000_000L);
    private static final TicketType css = 
        new TicketTypeSingle("Single Central + Suburbs Day", 49, centrumPlus, 4, 0);
    private static final TicketType cssdr =
        new TicketTypePeriodRestricted("7-Day Old People TicketType", 199.0, centrumPlus, 2592000000L, 
        EnumSet.of(User.Occupation.RETIREE));
    private static final TicketType asd = 
        new TicketTypeSingle("Single All Regions Day", 69, allZones,5, 22);
    private static final TicketType gt =
        new TicketTypePeriod("30-Day Golden Ticket", 1999, allZones, 2_592_000_000L);
    private static final TicketType ms =
        new TicketTypePeriodRestricted( "30-Day Student TicketType", 199.0, allZones, 2592000000L, 
        EnumSet.of(User.Occupation.STUDENT));

    public static void main(String[] args) {

        final Set<TicketType> ttlst = new HashSet<>();

        ttlst.add(csd);
        ttlst.add(ctd);
        ttlst.add(cm);
        ttlst.add(css);
        ttlst.add(asd);
        ttlst.add(gt);
        ttlst.add(cssdr);
        ttlst.add(ms);

        TicketFinder tf = new TicketFinder(ttlst);

        // Tests
        LocalDateTime t = LocalDateTime.of(2021, 3, 15, 5, 0);

        Set<TicketType> ats = 
            tf.find(a, new Trip(Zone.CENTRAL, Zone.SUBURB, t), 2000);

        if (ats.containsAll(new HashSet<>(Arrays.asList(ms, cssdr)))) {
                System.out.println("Alice (adult) found a retiree or student t");
        }

        for (TicketType tt : ats) {
            if (!tt.getValidZones().containsAll(EnumSet.of(Zone.SUBURB, Zone.CENTRAL))) {
                    System.out.println("Alice found t that isnt valid for her trip:");
                    System.out.println(tt.getName());
            }
        }
    
        Set<TicketType> bts = 
            tf.find(b, new Trip(Zone.CENTRAL, Zone.CENTRAL, t), 2000);
        if (bts.contains(ms)) {
            System.out.println("Bob (retiree) found the student t.");
        }
        if (!bts.contains(cssdr)) {
            System.out.println("Bob (retiree) didn't find the retiree t.");
        }
        for (TicketType tt : bts) {
            if (!tt.getValidZones().contains(Zone.CENTRAL)) {
                 System.out.println("Bob found t that isn't valid for his trip:");
                 System.out.println(tt.getName());
            }
        }

        Set<TicketType> cts = 
            tf.find(c, new Trip(Zone.CENTRAL, Zone.CENTRAL, t), 2000);
        if (cts.contains(cssdr)) {
            System.out.println("Carl (student) found the retiree t.");
        }
        if (!cts.contains(ms)) {
            System.out.println("Carl (student) didn't find the student t.");
        }

        for (TicketType tt : cts) {
            if (!tt.getValidZones().contains(Zone.CENTRAL)) {
                System.out.println("Carl found t that isn't valid for his trip:");
                System.out.println(tt.getName());
            }
        }

        System.out.println("If there were no previous output all tests passed");
    }
}

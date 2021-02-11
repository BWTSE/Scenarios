package tickets;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.Set;

public class Main {
    
    private static final User u1 = new User("Alice", "alice@crypto", 32, User.Occupation.ADULT);
    private static final User u2 = new User("Bob", "bob@crypto", 75, User.Occupation.RETIREE);
    private static final User u3 = new User("Carl", "carl@crypto", 21, User.Occupation.STUDENT);
    

    private static Set<Zone> z1 = EnumSet.of(Zone.CENTRAL);
    private static Set<Zone> z2 = EnumSet.of(Zone.CENTRAL, Zone.SUBURB);
    private static Set<Zone> z3 = EnumSet.of(Zone.CENTRAL, Zone.SUBURB, Zone.RURAL);

    private static final TicketType t1 =
        new TicketTypeSingle("Single Central Day", 29, z1, 5, 22);
    private static final TicketType t2 =
        new TicketTypePeriod("3-Day Period Central", 149, z1, 259_200_000L);
    private static final TicketType t3 =
        new TicketTypePeriod("1-Month Central", 749, z1, 2_592_000_000L);
    private static final TicketType t4 =
        new TicketTypeSingle("Single Central + Suburbs Day", 49, z2, 4, 0);
    private static final TicketType t5 =
        new TicketTypePeriodRestricted("7-Day Old People TicketType", 199.0, z2, 2592000000L,
        EnumSet.of(User.Occupation.RETIREE));
    private static final TicketType t6 =
        new TicketTypeSingle("Single All Regions Day", 69, z3,5, 22);
    private static final TicketType t7 =
        new TicketTypePeriod("30-Day Golden Ticket", 1999, z3, 2_592_000_000L);
    private static final TicketType t8 =
        new TicketTypePeriodRestricted( "30-Day Student TicketType", 199.0, z3, 2592000000L,
        EnumSet.of(User.Occupation.STUDENT));

    public static void main(String[] args) {

        final Set<TicketType> ttlst = new HashSet<>();

        ttlst.add(t1);
        ttlst.add(t2);
        ttlst.add(t3);
        ttlst.add(t3);
        ttlst.add(t4);
        ttlst.add(t5);
        ttlst.add(t6);
        ttlst.add(t7);

        TicketFinder tf = new TicketFinder(ttlst);

        // Tests
        LocalDateTime t = LocalDateTime.of(2021, 3, 15, 5, 0);

        Set<TicketType> ats = 
            tf.find(u1, new Trip(Zone.CENTRAL, Zone.SUBURB, t), 2000);

        if (ats.containsAll(new HashSet<>(Arrays.asList(t5, t8)))) {
                System.out.println("Alice (adult) found a retiree or student ticket");
        }

        for (TicketType tt : ats) {
            if (!tt.getValidZones().containsAll(EnumSet.of(Zone.SUBURB, Zone.CENTRAL))) {
                    System.out.println("Alice found ticket that isnt valid for her trip:");
                    System.out.println(tt.getName());
            }
        }
    
        Set<TicketType> bts = 
            tf.find(u2, new Trip(Zone.CENTRAL, Zone.CENTRAL, t), 2000);
        if (bts.contains(t8)) {
            System.out.println("Bob (retiree) found the student ticket.");
        }
        if (!bts.contains(t5)) {
            System.out.println("Bob (retiree) didn't find the retiree ticket.");
        }
        for (TicketType tt : bts) {
            if (!tt.getValidZones().contains(Zone.CENTRAL)) {
                 System.out.println("Bob found ticket that isn't valid for his trip:");
                 System.out.println(tt.getName());
            }
        }

        Set<TicketType> cts = 
            tf.find(u3, new Trip(Zone.CENTRAL, Zone.CENTRAL, t), 2000);
        if (cts.contains(t5)) {
            System.out.println("Carl (student) found the retiree ticket.");
        }
        if (!cts.contains(t8)) {
            System.out.println("Carl (student) didn't find the student ticket.");
        }

        for (TicketType tt : cts) {
            if (!tt.getValidZones().contains(Zone.CENTRAL)) {
                System.out.println("Carl found ticket that isn't valid for his trip:");
                System.out.println(tt.getName());
            }
        }

        System.out.println("If there were no previous output all tests passed");
    }
}

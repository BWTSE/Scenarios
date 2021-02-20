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
        new TicketTypeSeasonal("Central Winter Ticket", 149, z1, Season.WINTER);
    private static final TicketType t3 =
        new TicketTypeSeasonal("Central Summer Ticket", 749, z1, Season.SUMMER);
    private static final TicketType t4 =
        new TicketTypeSingle("Single Central + Suburbs Day", 49, z2, 4, 0);
    private static final TicketType t5 =
        new TicketTypeSeasonalRestricted("Retiree Winter Ticket", 199.0, z2, Season.WINTER, 
        EnumSet.of(User.Occupation.RETIREE));
    private static final TicketType t6 =
        new TicketTypeSingle("Single All Regions Day", 69, z3,5, 22);
    private static final TicketType t7 =
        new TicketTypeSeasonalRestricted( "Student Winter Ticket", 199.0, z3, Season.WINTER, 
        EnumSet.of(User.Occupation.STUDENT));

    public static void main(String[] args) {

        final Set<TicketType> ttlst = new HashSet<>();

        ttlst.add(t1);
        ttlst.add(t2);
        ttlst.add(t3);
        ttlst.add(t4);
        ttlst.add(t6);
        ttlst.add(t5);
        ttlst.add(t7);


        TicketFinder tf = new TicketFinder(ttlst);

        // Tests
        LocalDateTime t = LocalDateTime.of(2021, 12, 15, 5, 0);

        Set<TicketType> u1ts =
            tf.find(u1, new Trip(Zone.CENTRAL, Zone.SUBURB, t), 2000);

        if (u1ts.containsAll(new HashSet<>(Arrays.asList(t7, t5)))) {
            System.out.println("Alice (adult) found a retiree or student ticket");
        }

        for (TicketType tic : u1ts) {
            if (!tic.getValidZones().containsAll(EnumSet.of(Zone.SUBURB, Zone.CENTRAL))) {
                System.out.println("Alice found ticket that isnt valid for her trip:");
                System.out.println(tic.getName());
            }
        }
    
        Set<TicketType> u2ts =
            tf.find(u2, new Trip(Zone.CENTRAL, Zone.CENTRAL, t), 2000);
        if (u2ts.contains(t7)) {
            System.out.println("Bob (retiree) found the student ticket.");
        }
        if (!u2ts.contains(t5)) {
            System.out.println("Bob (retiree) didn't find the retiree ticket.");
        }
        for (TicketType tic : u2ts) {
            if (!tic.getValidZones().contains(Zone.CENTRAL)) {
                System.out.println("Bob found ticket that isn't valid for his trip:");
                System.out.println(tic.getName());
            }
        }

        Set<TicketType> u3ts =
            tf.find(u3, new Trip(Zone.CENTRAL, Zone.CENTRAL, t), 2000);
        if (u3ts.contains(t5)) {
            System.out.println("Carl (student) found the retiree ticket.");
        }
        if (!u3ts.contains(t7)) {
            System.out.println("Carl (student) didn't find the student ticket.");
        }

        for (TicketType tic : u3ts) {
            if (!tic.getValidZones().contains(Zone.CENTRAL)) {
                System.out.println("Carl found ticket that isn't valid for his trip:");
                System.out.println(tic.getName());
            }
        }

        System.out.println("If there were no previous output all tests passed");
    }
}

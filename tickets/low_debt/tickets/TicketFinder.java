package tickets;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class TicketFinder {

    private final List<TicketType> TicketTypeList = new LinkedList<TicketType>();

    public TicketFinder() {
        populateTicketTypeList();
    }

    public List<TicketType> find(User user, Zone startZone, Zone endZone, double maxPrice, int tripStartHour) {
        List<TicketType> results = new LinkedList<TicketType>();
        for (TicketType TicketType : TicketTypeList) {
            if (TicketType.isValidFor(startZone, endZone, user, tripStartHour) && TicketType.getPrice() <= maxPrice) {
                results.add(TicketType);
            }
        }
        return results;
    }

    public List<TicketType> getTicketTypeList() {
        return this.TicketTypeList;
    }

    private void populateTicketTypeList() {

        TicketTypeList.add(new TicketTypeSingle("Single Central Day",
            "One trip within the CENTRAL region between 5:00 and 22:00", 
            29, 
            Arrays.asList(Zone.CENTRAL), 
            5, 
            22));

        TicketTypeList.add(new TicketTypeSingle("Single Central Night",
            "One trip within the CENTRAL region between 22:00 and 5:00", 
            39, 
            Arrays.asList(Zone.CENTRAL), 
            22, 
            5));

        TicketTypeList.add(new TicketTypeSingle("Single Central + Suburbs Day",
            "One trip within the CENTRAL and SUBURB regions between 4:00 and 00:00", 
            49, 
            Arrays.asList(Zone.CENTRAL, Zone.SUBURB), 
            4, 
            0));

        TicketTypeList.add(new TicketTypeSingle("Single All Regions Day",
            "One trip within all covered regions between 4:00 and 00:00", 
            69, 
            Arrays.asList(Zone.CENTRAL, Zone.SUBURB, Zone.RURAL), 
            5, 
            22));

        TicketTypeList.add(new TicketTypePeriod("3-Day Period Central",
            "Unlimited travel for 3 days within the central region", 
            149, 
            Arrays.asList(Zone.CENTRAL), 
            259200000L));

        TicketTypeList.add(new TicketTypePeriod("30-Day Period Central",
            "Unlimited travel for 30 days within the central region", 
            749, 
            Arrays.asList(Zone.CENTRAL), 
            2592000000L));

        TicketTypeList.add(new TicketTypePeriod("30-Day Golden TicketType",
            "Unlimited travel for 30 days within all regions", 
            1999, 
            Arrays.asList(Zone.CENTRAL, Zone.SUBURB, Zone.RURAL), 
            2592000000L));

        TicketTypeList.add(new TicketTypePeriodRestricted("30-Day Student TicketType",
            "Unlimited travel for 30 days within all regions - for students", 
            199, 
            Arrays.asList(Zone.CENTRAL, Zone.SUBURB, Zone.RURAL), 
            2592000000L,
            true, 
            false));

        TicketTypeList.add(new TicketTypePeriodRestricted("7-Day Old People TicketType",
            "7 day travel whithin CETRAL and SUBURB regions for retirees", 
            199, 
            Arrays.asList(Zone.CENTRAL, Zone.SUBURB), 
            2592000000L,
            false, 
            true));
    }
}

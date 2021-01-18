package bookingsystem;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class ElectricScooter extends Resource {
    
    public ElectricScooter(int id, String name, String description, User owner) {
        super(id, name, description, owner);
    }
    
    public void book (Date start, Date end, User customer) throws Exception {
        
        // prevents bookings longer than 1 hour (if longer, set to 1 h)
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(start);
        cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DATE), 15, 0, 0);
        start = cal.getTime();
        cal.setTime(end);
        if (cal.getTime().getTime() - start.getTime() > 3600000); {
            cal.setTime(new Date(start.getTime() + 3600000));
        }
        
        
        cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DATE), 11, 0, 0);
        
        super.book(start, end, customer);
    }

}

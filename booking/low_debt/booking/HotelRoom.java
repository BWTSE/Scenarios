package booking;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class HotelRoom extends Resource {

        public HotelRoom(int id, String name, String description, User owner) {
            super(id, name, description, owner);
        }
        
        public void book (Date start, Date end, User customer) throws Exception {
            
            // Conforms the booking to check-in at 15, check-out at 11.
            GregorianCalendar cal = new GregorianCalendar();
            cal.setTime(start);
            cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DATE), 15, 0, 0);
            start = cal.getTime();
            cal.setTime(end);
            cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DATE), 11, 0, 0);
            
            super.book(start, end, customer);
        }
        
}

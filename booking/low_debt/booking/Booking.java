package bookingsystem;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class Booking {
	
	Date start;
	Date end;
	User customer;
	int id;
	
	public Booking(Date start, Date end, User customer, int id) {
		if (start.getTime() > end.getTime())
			throw new IllegalArgumentException("Bookings must start before they end");
		this.start = start;
		this.end = end;
		this.customer = customer;
		this.id = id;
	}

	  public Date getStart() {
	      return start;
	  }
	  
	  public Date getEnd() {
	      return end;
	  }
	  
	  public User getCustomer() {
	      return customer;
	  }
	  
	  public int getId() {
	      return id;
	  }
	  
	  @Override
	  public String toString() {
		  Calendar cal = new GregorianCalendar();
		  cal.setTime(start);
		  String startstr = " " + cal.get(Calendar.YEAR) + "/" + (cal.get(Calendar.MONTH) + 1) + "/" + cal.get(Calendar.DATE) + " " + cal.get(Calendar.HOUR_OF_DAY) + ":" + cal.get(Calendar.MINUTE);
		  cal.setTime(end);
		  String endstr = " " + cal.get(Calendar.YEAR) + "/" + (cal.get(Calendar.MONTH) + 1) + "/" + cal.get(Calendar.DATE) + " " + cal.get(Calendar.HOUR_OF_DAY) + ":" + cal.get(Calendar.MINUTE);
		  StringBuilder str = new StringBuilder();
		  str.append("\n[" + startstr + " until "  + endstr + "]  booked by: " + customer + "booking Id = " + id); 
		  return str.toString();
	  }

}

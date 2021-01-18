package bookingsystem;

import java.util.ArrayList;
import java.util.Date;

public abstract class Resource {
	
	int id;
	String name;
	String description;
	User owner;
	int bookingCounter;
	
	protected ArrayList<Booking> bookings = new ArrayList<Booking>();
	
	public Resource(int id, String name, String description, User owner) {
	    this.id = id;
	    this.name = name;
	    this.description = description;
	    this.owner = owner;
	    bookingCounter = 0;
	}
	
	
	public void book (Date start, Date end, User customer) throws Exception {
		
        if (bookings.size() == 0) {
        	bookings.add(new Booking(start, end, customer, bookingCounter));
        	bookingCounter ++;
        }
        else {
        	for (Booking booking: bookings) {
        		if (start.getTime() > booking.getStart().getTime() && start.getTime() < booking.getEnd().getTime() 
            			|| end.getTime() > booking.getStart().getTime() && end.getTime() < booking.getEnd().getTime()) {
            		throw new Exception("Booking Unavailable");
        		}
        	}
        	bookings.add(new Booking(start, end, customer, bookingCounter));
        	bookingCounter ++;
        }
    }
	
	public void unbook (int bookingId) {
	    bookings.removeIf(booking -> booking.getId() == bookingId);
	}
	
	public int getId() {
		return id;
	}
	  
	  public String getName() {
	      return name;
	  }

	  public void setName(String name) {
	      this.name = name;
	  }

	  public String getDescription() {
	      return description;
	  }

	  public void setDescription(String description) {
	      this.description = description;
	  }
	  
	  public String toString() {
		  StringBuilder str = new StringBuilder();
		  str.append("\nSchedule for: " + name);
		  for (Booking booking : bookings) {
			  str.append(booking.toString());
		  }
		  return str.toString();
	  }
	
}


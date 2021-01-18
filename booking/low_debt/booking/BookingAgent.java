package bookingsystem;

import java.util.ArrayList;
import java.util.Date;

public class BookingAgent {
	private ArrayList<Resource> resources;
	
	public BookingAgent() {
	    resources = new ArrayList<Resource>();
	}
	
	public void addResource(Resource resource) {
		resources.add(resource);
	}
	
	public void book(int id, Date start, Date end, User customer) {
	    for (Resource resource : resources) {
	        if (resource.id == id) {
	            try {
                    resource.book(start, end, customer);
                } catch (Exception e) {
                    System.out.print(e.getMessage());
                }
	        }
	    }
	}
	
	public void unbook(int resourceId, int bookingId) {
	    for (Resource resource : resources) {
	        if (resource.getId() == resourceId) {
	            resource.unbook(bookingId);
	        }
	    }
	}
	
	public String getSchedule(int id) throws Exception {
		for (Resource res: resources) {
			if (res.getId() == id) {
				return res.toString();
			}
		}
		throw new Exception("No such resource");
	}
}

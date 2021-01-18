package bookingsystem;

import java.util.Date;

public class Main {
    static BookingAgent bookingAgent = new BookingAgent();
    static Database db;
    
    public static void main(String[] args) {
        db = new Database();
        bookingAgent.addResource(new HotelRoom(0001, "The Great Northern Room 1", "Great Place", db.getUser(1)));
        bookingAgent.addResource(new HotelRoom(0002, "The Stanley Hotel", "Great Place", db.getUser(2)));
        Date start = new Date();
        bookingAgent.book(0001, start, new Date(start.getTime() + 86400000), db.getUser(4));
        try {
            System.out.println(bookingAgent.getSchedule(0001));
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        // testingstuff
        start = new Date();
           
        try {
            System.out.println(bookingAgent.getSchedule(0001));
        } catch (Exception e) {
            e.printStackTrace();
        }
        bookingAgent.unbook(0001, 0);
        try {
            System.out.println(bookingAgent.getSchedule(0001));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

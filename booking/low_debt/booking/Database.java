package booking;

import java.util.ArrayList;

public final class Database {
    private final ArrayList<User> userList = new ArrayList<User>();
    private final ArrayList<Resource> resourceList = new ArrayList<Resource>();
    
    public Database() {
        userList.add(new User(1, "Ben Horne", "benhorne@tgn.com"));
        userList.add(new User(2, "Jack Torrance", "jacktorrance@stanley.com"));
        userList.add(new User(3, "Norman Bates", "norman@batesmotel.com"));
        userList.add(new User(4, "Joan Chen", "joanchen@packardsaw.com"));
        
        resourceList.add(new HotelRoom(0001, "The Great Northern Room 1", "Great Place", getUser(1)));
        resourceList.add(new HotelRoom (0002, "The Stanley Hotel", "Great Place", getUser(2)));
        
        
    }
    
    
    
    public User getUser(int id) {
        User user = null;
        for (User u : userList) {
            if (u.getId() == id) {
                user = u;
            }
        }
        return user;
    }
}

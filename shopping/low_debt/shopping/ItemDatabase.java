package salestracker;

import java.util.ArrayList;

public class ItemDatabase {
	
    ArrayList<Item> items;
    
    public ItemDatabase() {
        items = new ArrayList<Item>();
        items.add(new Item(1001, "Milk 1l", 12.95));
        items.add(new Item(1002, "Eggs 12p", 34.99));
        items.add(new Item(1003, "Baguette", 7.95));
        items.add(new Item(1004, "Butter 250g", 39.99));
        items.add(new Item(1005, "Falu Råg-rut", 19.95));
        items.add(new Item(1005, "Apples /kg", 24.95));
        items.add(new Item(1005, "Potatoes /kg", 9.99));
    }
    
	public Item lookup(int barcode) throws Exception {
	    for (Item item : items) {
	        if (item.getBarcode() == barcode) {
	            return item;
	        }
	    }
        throw new Exception("No such item");
	}
	
	public Item getDiscount(Item item, double quantity) throws Exception {
		switch (item.getBarcode()) {
		case 1001:
			if (quantity >= 3)
				return new Item(3001, "Milk: Buy 3 pay for for 2 ", ((int)quantity / 3) * item.getPrice() * -1);
			else
				throw new Exception("No discount applicable");
		case 1002:
			throw new Exception("No discount applicable");
		case 1003:
			return new Item(3003, "Baguettes: Special offer 5 off", quantity * -5 );
		case 1004:
			throw new Exception("No discount applicable");
		case 1005:
			throw new Exception("No discount applicable");
		case 1006:
			throw new Exception("No discount applicable");
		case 1007:
			throw new Exception("No discount applicable");
		default: 
			throw new Exception("No such item");
	}
	}
}

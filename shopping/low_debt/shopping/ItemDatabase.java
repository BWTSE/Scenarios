package shopping;

import java.util.ArrayList;

public class ItemDatabase {
	
	ArrayList<Item> items = new ArrayList<>();
	ArrayList<Discount> discountList = new ArrayList<>();
    
    public ItemDatabase() {
        items.add(new Item(1001, "Milk 1l", 12.95));
        items.add(new Item(1002, "Eggs 12p", 34.99));
        items.add(new Item(1003, "Baguette", 7.95));
        items.add(new Item(1004, "Butter 250g", 39.99));
        items.add(new Item(1005, "Falu Råg-rut", 19.95));
        items.add(new Item(1006, "Apples /kg", 24.95));
		items.add(new Item(1007, "Potatoes /kg", 9.99));
		
		try {
			discountList.add(new Discount(3001, "Milk: Buy 3 pay for for 2 ", lookup(1001), 12.95, (Double quantity) -> (double)quantity.intValue() / 3));
			discountList.add(new Discount(3003, "Baguettes: Special offer 5 off",  lookup(1003), 5, (Double quantity) -> quantity));
		} catch (NoSuchEntryException e) {
			System.out.println(e);
		}
    }
    
	public Item lookup(int barcode) throws NoSuchEntryException {
	    for (Item item : items) {
	        if (item.getBarcode() == barcode) {
	            return item;
	        }
	    }
        throw new NoSuchEntryException();
	}
	
	public Discount getDiscount(Item item) throws NoSuchEntryException {
		for (Discount discount : discountList) {
			if(discount.getItem().equals(item)) {
				return discount;
			}
		}
		throw new NoSuchEntryException();
	}
}

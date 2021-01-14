package salestracker;

import java.util.ArrayList;

public class Tracker {
	
	private ArrayList<Item> il;
	private ArrayList<Item> dl;
	private ItemDatabase idb;
	
	public Tracker() {
		il = new ArrayList<Item>();
		dl = new ArrayList<Item>();
		idb = new ItemDatabase();
	}
	
	public void addItem(int barcode) {
		try {
			Item item = idb.lookup(barcode);
			if (il.contains(item))	
				il.get(il.indexOf(item)).incq();
			else
				il.add(item);
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void addItem(int barcode, double quantity) {
		try {
			Item item = idb.lookup(barcode);
			item.setq(quantity);
			if (il.contains(item))	
				il.get(il.indexOf(item)).setq(item.getq() + quantity);
			else
				il.add(item);
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void removeItem(int barcode) {
		try {
			Item item = idb.lookup(barcode);
			if (il.contains(item))	
				il.remove(item);
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void removeItem(int barcode, double quantity) {
		try {
			Item item = idb.lookup(barcode);
			if (il.contains(item))	
				il.get(il.indexOf(item)).setq(quantity);
			if (il.get(il.indexOf(item)).getq() <= 0)
				il.remove(item);
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	
	public void applyDiscounts() {
		
		ArrayList<Item> dl = new ArrayList<Item>();
		for (Item item : il) {
			try {
				Item discount = idb.getDiscount(item);
				dl.add(discount);
			}
			catch (Exception e) {
			}
		}
		this.dl = dl;
	}
	
	
	// Testing method
	public void printReceipt () {

		double total = 0;
		System.out.println("RECEIPT");
		System.out.println("Items:");
		for (Item item : il) {
			System.out.println(item.getn() + "     " + item.getq() + "     " + item.getq() * item.getp());
			total += item.getq() * item.getp();
		   }
		for (Item item : dl) {
			System.out.println(item.getn() + "     " + item.getq() + "     " + item.getq() * item.getp());
			total += item.getq() * item.getp();
		   }
		System.out.println("Total: " + total + ":-");
		System.out.println("Thank you for shopping at Robotresearcher");
		
		
	}
	
}

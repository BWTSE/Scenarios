package salestracker;

import java.util.ArrayList;

public class Tracker {
	
	private ArrayList<Item> itemList;
	private ArrayList<Item> discountList;
	private ItemDatabase itemdb;
	
	public Tracker() {
		itemList = new ArrayList<Item>();
		discountList = new ArrayList<Item>();
		itemdb = new ItemDatabase();
	}
	
	public void addItem(int barcode) {
		try {
			Item item = itemdb.lookup(barcode);
			if (itemList.contains(item))	
				itemList.get(itemList.indexOf(item)).incrementQuantity();
			else
				itemList.add(item);
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void addItem(int barcode, double quantity) {
		try {
			Item item = itemdb.lookup(barcode);
			item.setQuantity(quantity);
			if (itemList.contains(item))	
				itemList.get(itemList.indexOf(item)).setQuantity(item.getQuantity() + quantity);
			else
				itemList.add(item);
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void removeItem(int barcode) {
		try {
			Item item = itemdb.lookup(barcode);
			if (itemList.contains(item))	
				itemList.remove(item);
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void removeItem(int barcode, double quantity) {
		try {
			Item item = itemdb.lookup(barcode);
			if (itemList.contains(item))	
				itemList.get(itemList.indexOf(item)).setQuantity(quantity);
			if (itemList.get(itemList.indexOf(item)).getQuantity() <= 0)
				itemList.remove(item);
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	
	public void applyDiscounts() {
		
		ArrayList<Item> discountList = new ArrayList<Item>();
		for (Item item : itemList) {
			try {
				Item discount = itemdb.getDiscount(item);
				discountList.add(discount);
			}
			catch (Exception e) {
			}
		}
		this.discountList = discountList;
	}
	
	
	// Testing method
	public void printReceipt () {

		double total = 0;
		System.out.println("RECEIPT");
		System.out.println("Items:");
		for (Item item : itemList) {
			System.out.println(item.getName() + "     " + item.getQuantity() + "     " + item.getQuantity() * item.getPrice());
			total += item.getQuantity() * item.getPrice();
		   }
		for (Item item : discountList) {
			System.out.println(item.getName() + "     " + item.getQuantity() + "     " + item.getQuantity() * item.getPrice());
			total += item.getQuantity() * item.getPrice();
		   }
		System.out.println("Total: " + total + ":-");
		System.out.println("Thank you for shopping at Robotresearcher");
		
		
	}
	
}

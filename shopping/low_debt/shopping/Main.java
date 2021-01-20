package shopping;


public class Main {
	
	public static void main(String[] args) {
		
		Tracker tracker = new Tracker();
		
		int[] exampleBasket = new int[]{ 1001, 1001, 1002, 1004,1003,1005, 1003, 1002, 1001, 1005};
		for (int barcode : exampleBasket) {
			tracker.addItem(barcode);
		}
		tracker.addItem(1006, 0.5);
		tracker.addItem(1007, 2.15);

		
		tracker.applyDiscounts();
		tracker.printReceipt();
		
	}
}

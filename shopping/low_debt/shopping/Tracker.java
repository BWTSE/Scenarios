package shopping;

import java.util.ArrayList;

public class Tracker {

    private final ArrayList<ItemQuantity> itemList = new ArrayList<>();
    private final ArrayList<DiscountQuantity> discountList = new ArrayList<>();
    private final ItemDatabase itemdb;

    public Tracker(ItemDatabase itemdb) {
        this.itemdb = itemdb;
    }

    public void addItem(int barcode, double quantity) {
        try {
            ItemQuantity itemQuantity = new ItemQuantity(itemdb.lookup(barcode), quantity);
            if (itemList.contains(itemQuantity)) { 
                ItemQuantity ogItemQuantity = itemList.get(itemList.indexOf(itemQuantity));
                double newQuantity = ogItemQuantity.getQuantity() + quantity;
                ogItemQuantity.setQuantity(newQuantity);
            } else {
                itemList.add(itemQuantity);
            }
        } catch (NoSuchEntryException e) {
        }
    }

    public void addItem(int barcode) {
        addItem(barcode, 1);
    }

    public void removeItem(int barcode, double quantity) {
        try {
            ItemQuantity itemQuantity = new ItemQuantity(itemdb.lookup(barcode));
            if (itemList.contains(itemQuantity)) {
                ItemQuantity ogItemQuantity = itemList.get(itemList.indexOf(itemQuantity));
                ogItemQuantity.setQuantity(itemQuantity.getQuantity() - quantity);
            }
            if (itemList.get(itemList.indexOf(itemQuantity)).getQuantity() <= 0) {
                itemList.remove(itemQuantity);
            }
        } catch (NoSuchEntryException e) {
        }
    }

    public void removeItem(int barcode) {
        removeItem(barcode, 1);
    }

    public void applyDiscounts() {
        ArrayList<DiscountQuantity> discounts = new ArrayList<>();
        for (ItemQuantity itemQuantity : itemList) {
            try {
                Discount discount = itemdb.getDiscount(itemQuantity.getItem());
                discounts.add(
                    new DiscountQuantity(
                        discount, discount.getFormula().apply(itemQuantity.getQuantity())));
            } catch (NoSuchEntryException e) {
            }
        }
        discountList.clear();
        this.discountList.addAll(discounts);
    }

    
    // Testing method
    public void printReceipt () {
        System.out.println(discountList);
        double total = 0;
        System.out.println("RECEIPT");
        System.out.println("Items:");
        for (ItemQuantity itemQuantity : itemList) {
            System.out.println(itemQuantity.getName() + "     " + itemQuantity.getQuantity() + "     " + itemQuantity.getQuantity() * itemQuantity.getPrice());
            total += itemQuantity.getQuantity() * itemQuantity.getPrice();
        }
        

        for (DiscountQuantity discountQuantity : discountList) {
            System.out.println(discountQuantity.getName() + "     " + discountQuantity.getQuantity() + "     " + discountQuantity.getTotalValue());
            total += discountQuantity.getValue();
        }
        
        System.out.println("Total: " + total + ":-");
        System.out.println("Thank you for shopping at Robotresearcher");
    }
    
}

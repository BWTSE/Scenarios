package shopping;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;


public class Tracker {

    private final Map<Integer, ItemQuantity> items = new HashMap<>();
    private final List<DiscountQuantity> discounts = new ArrayList<>();
    private final ItemDatabase itemdb;

    public Tracker(ItemDatabase itemdb) {
        this.itemdb = itemdb;
    }

    public Map<Integer, ItemQuantity> getItems() {
        return Map.copyOf(items);
    }

    public List<DiscountQuantity> getDiscounts() {
        return List.copyOf(discounts);
    }

    public void addItem(int barcode, double quantity) {

        Optional<Item> item = itemdb.lookup(barcode);

        if (item.isPresent()) {
            ItemQuantity ogitem = items.get(barcode);
            if (ogitem != null) {
                ogitem.addQuantity(quantity);
            } else {
                items.put(barcode, new ItemQuantity(item.get(), quantity));
            }
        }
        discounts.clear();
    }

    public void addItem(int barcode) {
        addItem(barcode, 1);
        discounts.clear();
    }

    public void removeItem(int barcode, double quantity) {
        Optional<Item> item = itemdb.lookup(barcode);
        ItemQuantity itemQuantity = items.get(barcode);
        if (item.isPresent() && itemQuantity != null) {
            if (itemQuantity.getQuantity() > quantity) {
                itemQuantity.addQuantity(-quantity);
            } else {
                items.remove(barcode);
            }

        }
        discounts.clear();
    }

    public void removeItem(int barcode) {
        removeItem(barcode, 1);
        discounts.clear();
    }

    public void applyDiscounts() {
        ArrayList<DiscountQuantity> validDiscounts = new ArrayList<>();
        for (Entry<Integer, ItemQuantity> entry : items.entrySet()) {
            ItemQuantity itemQuantity = entry.getValue();
            Optional<Discount> discount = itemdb.getDiscount(itemQuantity.getItem());
            if (discount.isPresent()) {
                validDiscounts.add(
                    new DiscountQuantity(
                        discount.get(), 
                        discount.get().getFormula().apply(itemQuantity.getQuantity())));
            }

        }
        discounts.clear();
        this.discounts.addAll(validDiscounts);
    }  

    public void printReceipt () {
        double total = 0;
        System.out.println("RECEIPT");
        System.out.println("Items:");
        for (Entry<Integer, ItemQuantity> entry: items.entrySet()) {
            ItemQuantity itemQuantity = entry.getValue();
            System.out.println(itemQuantity.getName() + "     " + itemQuantity.getQuantity() + "     " + itemQuantity.getQuantity() * itemQuantity.getPrice());
            total += itemQuantity.getQuantity() * itemQuantity.getPrice();
        }
        
        for (DiscountQuantity discountQuantity : discounts) {
            System.out.println(discountQuantity.getName() + "     " + discountQuantity.getQuantity() + "     " + discountQuantity.getTotalValue());
            total += discountQuantity.getValue();
        }

        System.out.println("Total: " + total + ":-");
        System.out.println("Thank you for shopping at Robotresearcher");


    }
}

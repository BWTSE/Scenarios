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
}

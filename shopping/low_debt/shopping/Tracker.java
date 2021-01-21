package shopping;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Tracker {

    private final List<ItemQuantity> itemList = new ArrayList<>();
    private final List<DiscountQuantity> discountList = new ArrayList<>();
    private final ItemDatabase itemdb;

    public Tracker(ItemDatabase itemdb) {
        this.itemdb = itemdb;
    }

    public List<ItemQuantity> getItemList() {
        return List.copyOf(itemList);
    }

    public List<DiscountQuantity> getDiscountList() {
        return List.copyOf(discountList);
    }

    public void addItem(int barcode, double quantity) {

        Optional<Item> item = itemdb.lookup(barcode);

        if (item.isPresent()) {
            ItemQuantity itemQuantity = new ItemQuantity(item.get(), quantity);
            if (itemList.contains(itemQuantity)) { 
                ItemQuantity ogItemQuantity = itemList.get(itemList.indexOf(itemQuantity));
                double newQuantity = ogItemQuantity.getQuantity() + quantity;
                ogItemQuantity.setQuantity(newQuantity);
            } else {
                itemList.add(itemQuantity);
            }
        }
    }

    public void addItem(int barcode) {
        addItem(barcode, 1);
    }

    public void removeItem(int barcode, double quantity) {
        Optional<Item> item = itemdb.lookup(barcode);
        if(item.isPresent()) {
            ItemQuantity itemQuantity = new ItemQuantity(item.get());
            if (itemList.contains(itemQuantity)) {
                ItemQuantity ogItemQuantity = itemList.get(itemList.indexOf(itemQuantity));
                ogItemQuantity.setQuantity(itemQuantity.getQuantity() - quantity);
            }
            if (itemList.get(itemList.indexOf(itemQuantity)).getQuantity() <= 0) {
                itemList.remove(itemQuantity);
            }
        }
    }

    public void removeItem(int barcode) {
        removeItem(barcode, 1);
    }

    public void applyDiscounts() {
        ArrayList<DiscountQuantity> discounts = new ArrayList<>();
        for (ItemQuantity itemQuantity : itemList) {
            Optional<Discount> discount = itemdb.getDiscount(itemQuantity.getItem());
            if (discount.isPresent()) {
                discounts.add(
                    new DiscountQuantity(
                        discount.get(), 
                        discount.get().getFormula().apply(itemQuantity.getQuantity())));
            }

        }
        discountList.clear();
        this.discountList.addAll(discounts);
    }  
}

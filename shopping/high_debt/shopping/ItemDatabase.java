package shopping;

import java.util.ArrayList;
import java.util.Optional;
import java.util.function.UnaryOperator;

public class ItemDatabase {

    private final ArrayList<Item> items = new ArrayList<>();
    private final ArrayList<Discount> discountList = new ArrayList<>();

    public ItemDatabase() {
        items.add(new Item(1001, "Milk 1l", 12.95));
        items.add(new Item(1002, "Eggs 12p", 34.99));
        items.add(new Item(1003, "Baguette", 7.95));
        items.add(new Item(1004, "Butter 250g", 39.99));
        items.add(new Item(1005, "Falu Råg-rut", 19.95));
        items.add(new Item(1006, "Apples /kg", 24.95));
        items.add(new Item(1007, "Potatoes /kg", 9.99));


        Optional<Item> item = lookup(1001);
        
        if (item.isPresent()) {
            discountList.add(
                new Discount(
                    3001, 
                    "Milk: Buy 3 pay for for 2 ", 
                    item.get(), 
                    12.95,
                    (Double quantity) -> (double) quantity.intValue() / 3));
        }
        
        item = lookup(1003);
        if (item.isPresent()) {
            discountList.add(
                new Discount(3003, 
                    "Baguettes: Special offer 5 off", 
                    item.get(), 
                    5, 
                    UnaryOperator.identity())); 
        }
    }

    public final Optional<Item> lookup(int barcode) {
        for (Item item : items) {
            if (item.getBarcode() == barcode) {
                return Optional.of(item);
            } 
        }
        return Optional.empty();
    }

    public Optional<Discount> getDiscount(Item item) {
        for (Discount discount : discountList) {
            if (discount.getItem().equals(item)) {
                return Optional.of(discount);
            }
        }
        return Optional.empty();
    }
}

package shopping;

import java.util.ArrayList;
import java.util.Optional;
import java.util.function.UnaryOperator;

public class ItemDatabase {

    private final ArrayList<Item> is = new ArrayList<>();
    private final ArrayList<Discount> ds = new ArrayList<>();

    public ItemDatabase() {
        is.add(new Item(1001, "Milk 1l", 12.95));
        is.add(new Item(1002, "Eggs 12p", 34.99));
        is.add(new Item(1003, "Baguette", 7.95));
        is.add(new Item(1004, "Butter 250g", 39.99));
        is.add(new Item(1005, "Falu Råg-rut", 19.95));
        is.add(new Item(1006, "Apples /kg", 24.95));
        is.add(new Item(1007, "Potatoes /kg", 9.99));


        Optional<Item> i = lookup(1001);
        
        if (i.isPresent()) {
            ds.add(
                new Discount(
                    3001, 
                    "Milk: Buy 3 pay for for 2 ", 
                    i.get(), 
                    12.95,
                    (Double q) -> (double) q.intValue() / 3));
        }
        
        i = lookup(1003);
        if (i.isPresent()) {
            ds.add(
                new Discount(3003, 
                    "Baguettes: Special offer 5 off", 
                    i.get(), 
                    5, 
                    UnaryOperator.identity())); 
        }
    }

    public final Optional<Item> lookup(int bc) {
        for (Item i : is) {
            if (i.getBarcode() == bc) {
                return Optional.of(i);
            } 
        }
        return Optional.empty();
    }

    public Optional<Discount> getDiscount(Item i) {
        for (Discount d : ds) {
            if (d.getItem().equals(i)) {
                return Optional.of(d);
            }
        }
        return Optional.empty();
    }
}

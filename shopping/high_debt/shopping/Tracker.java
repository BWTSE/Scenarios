package shopping;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;


public class Tracker {

    private final Map<Integer, ItemQuantity> is = new HashMap<>();
    private final List<DiscountQuantity> ds = new ArrayList<>();
    private final ItemDatabase idb;

    public Tracker(ItemDatabase idb) {
        this.idb = idb;
    }

    public Map<Integer, ItemQuantity> getItems() {
        return Map.copyOf(is);
    }

    public List<DiscountQuantity> getDiscounts() {
        return List.copyOf(ds);
    }

    public void addItem(int bc, double q) {

        Optional<Item> i = idb.lookup(bc);

        if (i.isPresent()) {
            ItemQuantity oi = is.get(bc);
            if (oi != null) {
                oi.addQuantity(q);
            } else {
                is.put(bc, new ItemQuantity(i.get(), q));
            }
        }
        ds.clear();
    }

    public void addItem(int bc) {
        addItem(bc, 1);
        ds.clear();
    }

    public void removeItem(int bc, double q) {
        Optional<Item> i = idb.lookup(bc);
        ItemQuantity iq = is.get(bc);
        if (i.isPresent() && iq != null) {
            if (iq.getQuantity() > q) {
                iq.addQuantity(-q);
            } else {
                is.remove(bc);
            }

        }
        ds.clear();
    }

    public void removeItem(int bc) {
        removeItem(bc, 1);
        ds.clear();
    }

    public void applyDiscounts() {
        ArrayList<DiscountQuantity> vds = new ArrayList<>();
        for (Entry<Integer, ItemQuantity> entry : is.entrySet()) {
            ItemQuantity iq = entry.getValue();
            Optional<Discount> d = idb.getDiscount(iq.getItem());
            if (d.isPresent()) {
                vds.add(
                    new DiscountQuantity(
                        d.get(), 
                        d.get().getFormula().apply(iq.getQuantity())));
            }

        }
        ds.clear();
        this.ds.addAll(vds);
    }  
}

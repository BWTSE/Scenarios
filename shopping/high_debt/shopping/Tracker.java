package shopping;

import java.util.ArrayList;

public class Tracker {

    private final ArrayList<ItemQuantity> ilist = new ArrayList<>();
    private final ArrayList<DiscountQuantity> dlist = new ArrayList<>();
    private final ItemDatabase idb = new ItemDatabase();

    public void addItem(int bc, double q) {
        try {
            ItemQuantity iq = new ItemQuantity(idb.lookup(bc), q);
            if (ilist.contains(iq))    
                ilist.get(ilist.indexOf(iq)).incrementQuantity();
            else
                ilist.add(iq);
        }
        catch (NoSuchEntryException e) {
            System.out.println(e);
        }
    }

    public void addItem(int bc) {
        addItem(bc, 1);
    }

    public void removeItem(int bc, double q) {
        try {
            ItemQuantity iq = new ItemQuantity(idb.lookup(bc));
            if (ilist.contains(iq)) {
                ilist.get(ilist.indexOf(iq)).setQuantity(iq.getQuantity() - q);
            }
            if (ilist.get(ilist.indexOf(iq)).getQuantity() <= 0) {
                ilist.remove(iq);
            }
        }
        catch (NoSuchEntryException e) {
            System.out.println(e);
        }
    }

    public void removeItem(int bc) {
        removeItem(bc, 1);
    }

    public void applyDiscounts() {
        ArrayList<DiscountQuantity> ds = new ArrayList<>();
        for (ItemQuantity iq : ilist) {
            try {
                Discount d = idb.getDiscount(iq.getItem());
                ds.add(new DiscountQuantity(d, d.getFormula().apply(iq.getQuantity())));
            }
            catch (Exception e) {
            }
        }
        dlist.clear();
        this.dlist.addAll(ds);
    }


    // Testing method
    public void printReceipt () {
        System.out.println(dlist);
        double total = 0;
        System.out.println("RECEIPT");
        System.out.println("Items:");
        for (ItemQuantity iq : ilist) {
            System.out.println(iq.getName() + "     " + iq.getQuantity() + "     " + iq.getQuantity() * iq.getPrice());
            total += iq.getQuantity() * iq.getPrice();
        }
        

        for (DiscountQuantity dQuantity : dlist) {
            System.out.println(dQuantity.getName() + "     " + dQuantity.getQuantity() + "     " + dQuantity.getTotalValue());
            total += dQuantity.getValue();
        }
        
        System.out.println("Total: " + total + ":-");
        System.out.println("Thank you for shopping at Robotresearcher");


    }
    
    private class ItemQuantity{
        private Item item;
        private double q;
        
        public ItemQuantity(Item item, double q) {
            this.item = item;
            this.q = q;
        }
        
        public ItemQuantity(Item item) {
            this.item = item;
            this.q = 1;
        }
        
        public Item getItem() {
            return item;
        }
        
        public double getQuantity() {
            return q;
        }

        public void incrementQuantity() {
            q += 1;
        }

        public void setQuantity(double newq) {
            q = newq;
        }
        
        public String getName() {
            return item.getName();
        }
        

        public int getBarcode() {
            return item.getBarcode();
        }
        
        
        public double getPrice() {
            return item.getPrice();
        }
        
        @Override
        public boolean equals(Object o) { 

            if (o == this) { 
                return true; 
            } 

            if (!(o instanceof ItemQuantity)) { 
                return false; 
            } 

            ItemQuantity i = (ItemQuantity) o; 

            return item.getBarcode() == i.getBarcode();
        }

        @Override
        public int hashCode() {
            return getBarcode();
        }
    }

    private class DiscountQuantity{
        private Discount d;
        private double q;
        
        public DiscountQuantity(Discount d, double q) {
            this.d = d;
            this.q = q;
        }

        public double getValue() {
            return d.getValue();
        }

        public double getQuantity() {
            return q;
        }
        
        public double getTotalValue() {
            return q * getValue();
        }

        public String getName() {
            return d.getName();
        }

        public int getBarcode() {
            return d.getBarcode();
        } 
        
        @Override
        public boolean equals(Object o) { 

            if (o == this) { 
                return true; 
            } 

            if (!(o instanceof DiscountQuantity)) { 
                return false; 
            } 

            DiscountQuantity d = (DiscountQuantity) o; 

            return d.getBarcode() == d.getBarcode();
        }

        @Override
        public int hashCode() {
            return getBarcode();
        }
    }
    

}

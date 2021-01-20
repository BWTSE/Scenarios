package shopping;

import java.util.ArrayList;

public class Tracker {

    private final ArrayList<ItemQuantity> itemList = new ArrayList<>();
    private final ArrayList<DiscountQuantity> discountList = new ArrayList<>();
    private final ItemDatabase itemdb = new ItemDatabase();

    public void addItem(int barcode, double quantity) {
        try {
            ItemQuantity itemQuantity = new ItemQuantity(itemdb.lookup(barcode), quantity);
            if (itemList.contains(itemQuantity))    
                itemList.get(itemList.indexOf(itemQuantity)).incrementQuantity();
            else
                itemList.add(itemQuantity);
        }
        catch (NoSuchEntryException e) {
            System.out.println(e);
        }
    }

    public void addItem(int barcode) {
        addItem(barcode, 1);
    }

    public void removeItem(int barcode, double quantity) {
        try {
            ItemQuantity itemQuantity = new ItemQuantity(itemdb.lookup(barcode));
            if (itemList.contains(itemQuantity)) {
                itemList.get(itemList.indexOf(itemQuantity)).setQuantity(itemQuantity.getQuantity() - quantity);
            }
            if (itemList.get(itemList.indexOf(itemQuantity)).getQuantity() <= 0) {
                itemList.remove(itemQuantity);
            }
        }
        catch (NoSuchEntryException e) {
            System.out.println(e);
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
                discounts.add(new DiscountQuantity(discount, discount.getFormula().apply(itemQuantity.getQuantity())));
            }
            catch (Exception e) {
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
    
    private class ItemQuantity{
        private Item item;
        private double quantity;
        
        public ItemQuantity(Item item, double quantity) {
            this.item = item;
            this.quantity = quantity;
        }
        
        public ItemQuantity(Item item) {
            this.item = item;
            this.quantity = 1;
        }
        
        public Item getItem() {
            return item;
        }
        
        public double getQuantity() {
            return quantity;
        }

        public void incrementQuantity() {
            quantity += 1;
        }

        public void setQuantity(double newquantity) {
            quantity = newquantity;
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
        private Discount discount;
        private double quantity;
        
        public DiscountQuantity(Discount discount, double quantity) {
            this.discount = discount;
            this.quantity = quantity;
        }

        public double getValue() {
            return discount.getValue();
        }

        public double getQuantity() {
            return quantity;
        }
        
        public double getTotalValue() {
            return quantity * getValue();
        }

        public String getName() {
            return discount.getName();
        }

        public int getBarcode() {
            return discount.getBarcode();
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

            return discount.getBarcode() == d.getBarcode();
        }

        @Override
        public int hashCode() {
            return getBarcode();
        }
    }
    

}

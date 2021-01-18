package salestracker;

import java.util.ArrayList;

public class Tracker {

    private final ArrayList<ItemQuantity> itemList;
    private ArrayList<ItemQuantity> discountList;
    private final ItemDatabase itemdb;

    public Tracker() {
        itemList = new ArrayList<ItemQuantity>();
        discountList = new ArrayList<ItemQuantity>();
        itemdb = new ItemDatabase();
    }

    public void addItem(int barcode) {
        try {
            ItemQuantity itemQuantity = new ItemQuantity(itemdb.lookup(barcode));
            if (itemList.contains(itemQuantity))    
                itemList.get(itemList.indexOf(itemQuantity)).incrementQuantity();
            else
                itemList.add(itemQuantity);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void addItem(int barcode, double quantity) {
        try {
            ItemQuantity itemQuantity = new ItemQuantity(itemdb.lookup(barcode), quantity);
            if (itemList.contains(itemQuantity))    
                itemList.get(itemList.indexOf(itemQuantity)).incrementQuantity();
            else
                itemList.add(itemQuantity);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void removeItem(int barcode) {
        try {
            Item item = itemdb.lookup(barcode);
            if (itemList.contains(item))    
                itemList.remove(item);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void removeItem(int barcode, double quantity) {
        try {
            Item item = itemdb.lookup(barcode);
            if (itemList.contains(item))    
                itemList.get(itemList.indexOf(item)).setQuantity(quantity);
            if (itemList.get(itemList.indexOf(item)).getQuantity() <= 0)
                itemList.remove(item);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


    public void applyDiscounts() {

        ArrayList<ItemQuantity> discountList = new ArrayList<ItemQuantity>();
        for (ItemQuantity itemQuantity : itemList) {
            try {
                ItemQuantity discount = new ItemQuantity(itemdb.getDiscount(itemQuantity.getItem(), itemQuantity.getQuantity()));
                discountList.add(discount);
            }
            catch (Exception e) {
            }
        }
        this.discountList = discountList;
    }


    // Testing method
    public void printReceipt () {

        double total = 0;
        System.out.println("RECEIPT");
        System.out.println("Items:");
        for (ItemQuantity itemQuantity : itemList) {
            System.out.println(itemQuantity.getName() + "     " + itemQuantity.getQuantity() + "     " + itemQuantity.getQuantity() * itemQuantity.getPrice());
            total += itemQuantity.getQuantity() * itemQuantity.getPrice();
        }
        for (ItemQuantity itemQuantity : discountList) {
            System.out.println(itemQuantity.getName() + "     " + itemQuantity.getQuantity() + "     " + itemQuantity.getQuantity() * itemQuantity.getPrice());
            total += itemQuantity.getQuantity() * itemQuantity.getPrice();
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
    

}

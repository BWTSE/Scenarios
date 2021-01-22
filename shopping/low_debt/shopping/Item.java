package shopping;

import java.util.Locale;

public class Item {

    private final int barcode;
    private final String name;
    private final double price;

    public Item(int barcode, String name, double price) {
        this.barcode = barcode;
        this.name = name;
        this.price = price;
    }


    public int getBarcode() {
        return barcode;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }
    
    public boolean equals(Object o) { 

        if (o == this) { 
            return true; 
        } 

        if ( o == null || this.getClass() != o.getClass() ) { 
            return false; 
        } 

        Item item = (Item) o; 

        return barcode == item.getBarcode();
    }

    @Override
    public int hashCode() {
        return barcode;
    }
    
    @Override
    public String toString() {
        return String.format(Locale.ENGLISH, "Item: %s barcode: %d price: (%,.02f)", name, barcode, price);
    }
}

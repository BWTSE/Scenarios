package shopping;

import java.util.Locale;

public class Item {

    private final int bc;
    private final String n;
    private final double p;

    public Item(int bc, String n, double p) {
        this.bc = bc;
        this.n = n;
        this.p = p;
    }


    public int getBarcode() {
        return bc;
    }

    public String getName() {
        return n;
    }

    public double getPrice() {
        return p;
    }
    
    public boolean equals(Object o) { 

        if (o == this) { 
            return true; 
        } 

        if ( o == null || getClass() != o.getClass() ) { 
            return false; 
        } 

        Item item = (Item) o; 

        return bc == item.getBarcode();
    }

    @Override
    public int hashCode() {
        return bc;
    }
    
    @Override
    public String toString() {
        return String.format(Locale.ENGLISH, "Item: %s barcode: %d price: (%,.02f)", n, bc, p);
    }
}

package shopping;

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

        if ( o == null || this.getClass() != o.getClass() ) { 
            return false; 
        } 

        Item item = (Item) o; 

        return bc == item.getBarcode();
    }

    @Override
    public int hashCode() {
        return this.bc;
    }
    
    @Override
    public String toString() {
        return String.format("Item: %s barcode: %d price: %f", n, bc, p);
    }
}

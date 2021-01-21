package shopping;

public class ItemQuantity{
    private Item i;
    private double q;
    
    public ItemQuantity(Item i, double q) {
        this.i = i;
        this.q = q;
    }
    
    public ItemQuantity(Item i) {
        this.i = i;
        this.q = 1;
    }
    
    public Item getItem() {
        return i;
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
        return i.getName();
    }


    public int getBarcode() {
        return i.getBarcode();
    }


    public double getPrice() {
        return i.getPrice();
    }

    @Override
    public boolean equals(Object o) { 

        if (o == this) { 
            return true; 
        } 

        if ( o == null || this.getClass() != o.getClass() ) { 
            return false; 
        } 

        ItemQuantity iq = (ItemQuantity) o; 

        return i.getBarcode() == iq.getBarcode();
    }

    @Override
    public int hashCode() {
        return getBarcode();
    }
}

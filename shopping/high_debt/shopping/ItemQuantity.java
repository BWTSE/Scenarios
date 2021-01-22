package shopping;

public class ItemQuantity{
    private final Item i;
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

    public void addQuantity(double addq) {
        q += addq;
    }

    public void removeQuantity(double redq) {
        q -= redq;
    }

    public String getName() {
        return getItem().getName();
    }

    public int getBarcode() {
        return getItem().getBarcode();
    }

    public double getPrice() {
        return getItem().getPrice();
    }

    @Override
    public boolean equals(Object o) { 

        if (o == this) { 
            return true; 
        } 

        if ( o == null || getClass() != o.getClass() ) { 
            return false; 
        } 

        ItemQuantity iq = (ItemQuantity) o; 

        return getItem().getBarcode() == iq.getBarcode() 
            && Double.compare(q, iq.getQuantity()) == 0;
    }

    @Override
    public int hashCode() {
        return getBarcode();
    }
}

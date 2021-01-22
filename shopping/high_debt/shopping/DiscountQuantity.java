package shopping;

public class DiscountQuantity{
    private final Discount d;
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

        if ( o == null || this.getClass() != o.getClass() ) { 
            return false; 
        } 

        DiscountQuantity dq = (DiscountQuantity) o; 

        return d.getBarcode() == dq.getBarcode()
            && Double.compare(q, dq.getQuantity()) == 0;
    }

    @Override
     public int hashCode() {
        return getBarcode();
    }
}

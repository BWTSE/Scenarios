package shopping;

public class DiscountQuantity{
    private final Discount d;
    private final double q;
        
    public DiscountQuantity(Discount d, double q) {
        this.d = d;
        this.q = q;
    }

    public Discount getDiscount() {
        return d;
    }

    public double getValue() {
        return getDiscount().getValue();
    }

    public double getQuantity() {
        return q;
    }
        
    public double getTotalValue() {
        return q * getValue();
    }

    public String getName() {
        return getDiscount().getName();
    }

    public int getBarcode() {
        return getDiscount().getBarcode();
    } 
        
    @Override
    public boolean equals(Object o) { 
        if (o == this) { 
            return true; 
        } 

        if ( o == null || getClass() != o.getClass() ) { 
            return false; 
        } 

        DiscountQuantity dq = (DiscountQuantity) o; 

        return getDiscount().getBarcode() == dq.getBarcode()
            && Double.compare(q, dq.getQuantity()) == 0;
    }

    @Override
     public int hashCode() {
        return getBarcode();
    }
}

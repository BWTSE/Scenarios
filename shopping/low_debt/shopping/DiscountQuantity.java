package shopping;

public class DiscountQuantity{
    private final Discount discount;
    private final double quantity;
        
    public DiscountQuantity(Discount discount, double quantity) {
        this.discount = discount;
        this.quantity = quantity;
    }

    public Discount getDiscount() {
        return discount;
    }

    public double getValue() {
        return getDiscount().getValue();
    }

    public double getQuantity() {
        return quantity;
    }
        
    public double getTotalValue() {
        return quantity * getValue();
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

        DiscountQuantity discountQuantity = (DiscountQuantity) o; 

        return getDiscount().getBarcode() == discountQuantity.getBarcode()
            && Double.compare(quantity, discountQuantity.getQuantity()) == 0;
    }

    @Override
     public int hashCode() {
        return getBarcode();
    }
}

package shopping;

public class DiscountQuantity{
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

        if ( o == null || this.getClass() != o.getClass() ) { 
            return false; 
        } 

        DiscountQuantity discountQuantity = (DiscountQuantity) o; 

        return discount.getBarcode() == discountQuantity.getBarcode();
    }

    @Override
     public int hashCode() {
        return getBarcode();
    }
}
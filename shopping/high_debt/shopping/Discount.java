package shopping;

import java.util.function.UnaryOperator;

public class Discount {
    
    private final int barcode;
    private final String name;
    private final Item item;
    private final UnaryOperator<Double> discountFormula;
    private final double value;

    public Discount (
            int barcode, 
            String name, 
            Item item, 
            double value, 
            UnaryOperator<Double> discountFormula) {

        this.barcode = barcode;
        this.name = name;
        this. item = item;
        this.value = value;
        this.discountFormula = discountFormula;
    }

    public String getName() {
        return name;
    }

    public int getBarcode() {
        return barcode;
    }

    public double getValue() {
        return value;
    }

    public Item getItem() {
        return item;
    }

    public UnaryOperator<Double> getFormula() {
        return discountFormula;
    }

    public boolean equals(Object o) { 

        if (o == this) { 
            return true; 
        } 

        if ( o == null || this.getClass() != o.getClass() ) { 
            return false; 
        } 

        Discount discount = (Discount) o; 

        return barcode == discount.getBarcode();
    }

    @Override
    public int hashCode() {
        return this.barcode;
    }
    
    @Override
    public String toString() {
        return "Discount: " + name + ", barcode: " + barcode + ", value: " + value;
    }

}

package shopping;

import java.util.Locale;
import java.util.function.UnaryOperator;

public class Discount {
    
    private final int bc;
    private final String n;
    private final Item i;
    private final UnaryOperator<Double> df;
    private final double v;

    public Discount (
            int bc, 
            String n, 
            Item i, 
            double v, 
            UnaryOperator<Double> df) {

        this.bc = bc;
        this.n = n;
        this. i = i;
        this.v = v;
        this.df = df;
    }

    public String getName() {
        return n;
    }

    public int getBarcode() {
        return bc;
    }

    public double getValue() {
        return v;
    }

    public Item getItem() {
        return i;
    }

    public UnaryOperator<Double> getFormula() {
        return df;
    }

    public boolean equals(Object o) { 

        if (o == this) { 
            return true; 
        } 

        if ( o == null || getClass() != o.getClass() ) { 
            return false; 
        } 

        Discount d = (Discount) o; 

        return bc == d.getBarcode();
    }

    @Override
    public int hashCode() {
        return bc;
    }
    
    @Override
    public String toString() {
        return String.format(Locale.ENGLISH, "Discount: %s barcode: %d value: (%,.02f)", n, bc, v);
    }

}

package shopping;

public class NoDiscountApplicableException extends Exception{

    private final int itemBarcode;

    public NoDiscountApplicableException(int itemBarcode) {
        this.itemBarcode = itemBarcode;
    }

    @Override
    public String toString() {
        return "No discount applicable for item: " + itemBarcode;
    }
    
}

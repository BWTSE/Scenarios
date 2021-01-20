package shopping;

public class NoDiscountApplicableException extends Exception{

    @Override
    public String toString() {
        return "No discount applicable.";
    }

    private static final long serialVersionUID = 1L;
    
}

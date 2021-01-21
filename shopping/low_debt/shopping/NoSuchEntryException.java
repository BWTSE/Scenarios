package shopping;

public class NoSuchEntryException extends Exception{

    private final int itemBarCode;

    public NoSuchEntryException(int barCode) {
        this.itemBarCode = barCode;
    }

    @Override
    public String toString() {
        return "No such entry: " + itemBarCode;
    }
}

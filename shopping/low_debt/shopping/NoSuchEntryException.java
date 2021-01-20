package shopping;

public class NoSuchEntryException extends Exception{

    @Override
    public String toString() {
        return "No such entry";
    }

    private static final long serialVersionUID = 1L;
    
}

package shopping;

public class ItemQuantity{
    private Item item;
    private double quantity;
    
    public ItemQuantity(Item item, double quantity) {
        this.item = item;
        this.quantity = quantity;
    }
    
    public ItemQuantity(Item item) {
        this.item = item;
        this.quantity = 1;
    }
    
    public Item getItem() {
        return item;
    }
    
    public double getQuantity() {
        return quantity;
    }

    public void incrementQuantity() {
        quantity += 1;
    }

    public void setQuantity(double newQuantity) {
        quantity = newQuantity;
    }

    public void addQuantity(double additionalQuantity) {
        quantity += additionalQuantity;
    }

    public void removeQuantity(double reductionQuantity) {
        quantity -= reductionQuantity;
    }

    public String getName() {
        return item.getName();
    }

    public int getBarcode() {
        return item.getBarcode();
    }

    public double getPrice() {
        return item.getPrice();
    }

    @Override
    public boolean equals(Object o) { 

        if (o == this) { 
            return true; 
        } 

        if ( o == null || this.getClass() != o.getClass() ) { 
            return false; 
        } 

        ItemQuantity itemQuantity = (ItemQuantity) o; 

        return item.getBarcode() == itemQuantity.getBarcode() 
            && Double.compare(quantity, itemQuantity.getQuantity()) == 0;
    }

    @Override
    public int hashCode() {
        return getBarcode();
    }
}

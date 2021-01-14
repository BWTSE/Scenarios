package salestracker;

public class Item {

    private int barcode;
    private String name;
    private double price;
    private double quantity;

    public Item(int barcode, String name, double price) {
        this.barcode = barcode;
        this.name = name;
        this.price = price;
        this.quantity = 1;
    }


    public int getBarcode() {
        return barcode;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public double getQuantity() {
        return quantity;
    }

    public void incrementQuantity() {
        quantity += 1;
    }

    public void setQuantity(double newquantity) {
        quantity = newquantity;
    }


    @Override
    public boolean equals(Object o) { 

        if (o == this) { 
            return true; 
        } 

        if (!(o instanceof Item)) { 
            return false; 
        } 

        Item i = (Item) o; 

        return name == i.getName();
    }

    @Override
    public int hashCode() {
        return this.barcode;
    }
}

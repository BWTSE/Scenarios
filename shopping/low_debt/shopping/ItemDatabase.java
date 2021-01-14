package salestracker;

public class ItemDatabase {

    public Item lookup(int barcode) throws Exception {
        switch (barcode) {
        case 1001:
            return new Item(1001, "Milk 1l", 12.95);
        case 1002:
            return new Item(1002, "Eggs 12p", 34.99);
        case 1003:
            return new Item(1003, "Baguette", 7.95);
        case 1004:
            return new Item(1004, "Butter 250g", 39.99);
        case 1005:
            return new Item(1005, "Falu Råg-rut", 19.95);
        case 1006:
            return new Item(1005, "Apples /kg", 24.95);
        case 1007:
            return new Item(1005, "Potatoes /kg", 9.99);
        }
        throw new Exception("No such item");
    }

    public Item getDiscount(Item item) throws Exception {
        switch (item.getBarcode()) {
        case 1001:
            if (item.getQuantity() >= 3)
                return new Item(3001, "Milk: Buy 3 pay for for 2 ", ((int)item.getQuantity() / 3) * item.getPrice() * -1);
            else
                throw new Exception("No discount applicable");
        case 1002:
            throw new Exception("No discount applicable");
        case 1003:
            return new Item(3003, "Baguettes: Special offer 5 off", item.getQuantity() * -5 );
        case 1004:
            throw new Exception("No discount applicable");
        case 1005:
            throw new Exception("No discount applicable");
        case 1006:
            throw new Exception("No discount applicable");
        case 1007:
            throw new Exception("No discount applicable");
        default: 
            throw new Exception("No such item");
        }
    }
}

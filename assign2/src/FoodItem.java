import java.util.Scanner;

/**
 * Food item object to be stored in database
 *
 * @author Van Nam Doan
 * @version 1.1
 */
public class FoodItem implements Comparable<FoodItem> {
    /**
     * code of item (unique)
     */
    private int itemCode;
    /**
     * name of item
     */
    private String itemName;
    /**
     * price of item
     */
    private float itemPrice;
    /**
     * quantity of item in stock
     */
    private int itemQuantityInStock;
    /**
     * cost of item
     */
    private float itemCost;

    /**
     * default constructor
     */
    FoodItem() {
    }

    /**
     * display general food item information
     *
     * @return string message
     */
    @Override
    public String toString() {
        return "Item: " + itemCode + " " + itemName + " " + itemQuantityInStock + " price: $" + itemPrice + " cost: $" + itemCost;
    }

    /**
     * update item quantity in stock
     *
     * @param amount amount of update, positive if buying, negative if selling
     * @return operation status: true if successful
     */
    boolean updateItem(int amount) {
        if (amount < 0) {
            if (amount * (-1) > itemQuantityInStock) {
                System.err.println("Insufficient stock in inventory...");
                return false;
            }
            itemQuantityInStock += amount;
        } else {
            itemQuantityInStock += amount;
        }
        return true;
    }

//    /**
//     * check whether food item in parameter has the same item code as this food item object
//     *
//     * @param item food item for checking
//     * @return true if item code is the same
//     */
//    boolean isEqual(FoodItem item) {
//        return item.itemCode == this.itemCode;
//    }

    /**
     * initializing a new food item
     *
     * @param scanner  Scanner object for inputting information
     * @param fromFile reading from file or not
     * @return operation status: true if successful
     */
    boolean addItem(Scanner scanner, boolean fromFile) {
        try {
            itemName = scanner.nextLine();
            itemQuantityInStock = Integer.parseInt(scanner.nextLine());
            itemCost = Float.parseFloat(scanner.nextLine());
            itemPrice = Float.parseFloat(scanner.nextLine());
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    /**
     * let user input item code and check the status of that input
     *
     * @param scanner  Scanner object for inputting item code
     * @param fromFile is the user reading code from file or inputting manually
     * @return return false if item code does not exists, true if it does
     */
    boolean inputCode(Scanner scanner, boolean fromFile) {
        boolean valid = false;
        if (fromFile) {// if read from file, do not loop
            this.itemCode = Integer.parseInt(scanner.nextLine());
            return Inventory.alreadyExists(this, 0, null) != -1; // return false if cannot find item code
        }
        while (!valid) {
            valid = true;
            try {
                this.itemCode = Integer.parseInt(scanner.nextLine());
                if (Inventory.alreadyExists(this, 0, null) == -1) {
                    return false; // return false if cannot find item code
                }
            } catch (Exception e) {
                valid = false;
                System.err.println("Invalid input");
                System.out.println("Enter valid item code:");
            }
        }
        return true;
    }

    /**
     * preparing string for saving product information into file
     *
     * @return string prepared
     */
    String outputItem() {
        String temp = "";
        temp += itemCode + "\n";
        temp += itemName + "\n";
        temp += itemQuantityInStock + "\n";
        temp += itemCost + "\n";
        temp += itemPrice + "\n";
        return temp;
    }

    /**
     * comparator implementation for sorting (and future usage)
     *
     * @param o FoodItem object being compared to
     * @return positive if item code bigger, 0 if equal, negative if smaller
     */
    @Override
    public int compareTo(FoodItem o) {
        return Integer.compare(this.itemCode, o.itemCode);
    }
}

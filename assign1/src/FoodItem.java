import java.util.Scanner;

/**
 * Food item object to be stored in database
 *
 * @author Van Nam Doan
 * @version 1.0
 */
public class FoodItem {
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

    /**
     * check whether food item in parameter has the same item code as this food item object
     *
     * @param item food item for checking
     * @return true if item code is the same
     */
    boolean isEqual(FoodItem item) {
        return item.itemCode == this.itemCode;
    }

    /**
     * initializing a new food item
     *
     * @param scanner Scanner object for inputting information
     * @return operation status: true if successful
     */
    boolean addItem(Scanner scanner) {
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
     * @param scanner Scanner object for inputting item code
     * @return return false if item code already exists, true if not
     */
    boolean inputCode(Scanner scanner) {
        boolean valid = false;
        while (!valid) {
            valid = true;
            try {
                this.itemCode = Integer.parseInt(scanner.nextLine());
                if (Inventory.alreadyExists(this) == -1) {
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
}

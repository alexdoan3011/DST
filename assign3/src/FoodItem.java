import java.util.Scanner;

/**
 * Food item object to be stored in database
 *
 * @author Van Nam Doan 040943291
 * @version 1.2
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
        return "Item: " + itemCode + " " + itemName + " " + " price: $" + itemPrice + " cost: $" + itemCost;
    }


    /**
     * initializing a new food item
     *
     * @param scanner Scanner object for inputting information
     * @return operation status: true if successful
     */
    boolean addItem(Scanner scanner) {
        try {
            this.itemName = scanner.nextLine();
            this.itemCost = Float.parseFloat(scanner.nextLine());
            this.itemPrice = Float.parseFloat(scanner.nextLine());
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    /**
     * let user input item code and check the status of that input
     *
     * @param scanner Scanner object for inputting item code
     * @return return false if item code does not exists, true if it does
     */
    boolean inputCode(Scanner scanner) {
        this.itemCode = Integer.parseInt(scanner.nextLine());
        return true;
    }

    /**
     * comparator implementation for sorting (and future usage)
     *
     * @param o FoodItem object being compared to
     * @return positive if date is later than the date being compared to, negative if date is earlier, 0 if the dates are the same
     */
    @Override
    public int compareTo(FoodItem o) {
        return Integer.compare(this.itemCode, o.itemCode);
    }
}

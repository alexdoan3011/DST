import java.util.Scanner;

/**
 * Food item type vegetable
 *
 * @author Van Nam Doan 040943291
 * @version 1.2
 */
public class Vegetable extends FoodItem {
    /**
     * name of the producer of this item
     */
    private String farmName;

    /**
     * default constructor
     */
    Vegetable() {
    }


    /**
     * display vegetable food item information
     *
     * @return string message
     */
    @Override
    public String toString() {
        return super.toString() + " farm supplier: " + farmName;
    }

    /**
     * add new vegetable food item
     *
     * @param scanner Scanner object for inputting farm supplier name parameter
     * @return operation status: true if successful
     */
    @Override
    boolean addItem(Scanner scanner) {
        if (!super.addItem(scanner)) {
            return false;
        }
        System.out.print("Enter the name of the farm supplier: ");
        scanner = new Scanner(System.in);
        farmName = scanner.nextLine();
        return true;
    }
}

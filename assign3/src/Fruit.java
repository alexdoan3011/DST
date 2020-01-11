import java.util.Scanner;

/**
 * Food item type fruit
 *
 * @author Van Nam Doan 040943291
 * @version 1.2
 */
public class Fruit extends FoodItem {
    /**
     * name of the producer of this item
     */
    private String orchardName;

    /**
     * default constructor
     */
    Fruit() {
    }

    /**
     * display fruit food item information
     *
     * @return string message
     */
    @Override
    public String toString() {
        return super.toString() + " orchard supplier: " + orchardName;
    }

    /**
     * add new fruit food item
     *
     * @param scanner Scanner object for inputting orchard supplier name parameter
     * @return operation status: true if successful
     */
    @Override
    boolean addItem(Scanner scanner) {
        if (!super.addItem(scanner)) {
            return false;
        }
        System.out.print("Enter the name of the orchard supplier: ");
        scanner = new Scanner(System.in);
        orchardName = scanner.nextLine();
        return true;
    }
}

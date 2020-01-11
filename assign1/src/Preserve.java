import java.util.Scanner;

/**
 * Food item type preserve
 *
 * @author Van Nam Doan
 * @version 1.0
 */
public class Preserve extends FoodItem {
    /**
     * size of container for this preserve item (unique to this type of item)
     */
    private int jarSize;

    /**
     * default constructor
     */
    Preserve() {
    }

    /**
     * display preserve food item information
     *
     * @return string message
     */
    @Override
    public String toString() {
        return super.toString() + " size: " + jarSize + "ml";
    }

    /**
     * add new preserve food item
     *
     * @param scanner Scanner object for inputting jar size parameter
     * @return operation status: true if successful
     */
    @Override
    boolean addItem(Scanner scanner) {
        if (!super.addItem(scanner)) {
            return false;
        }
        System.out.print("Enter the size of the jar in millilitres: ");
        scanner = new Scanner(System.in);
        try {
            jarSize = Integer.parseInt(scanner.nextLine());
            if (jarSize <= 0) {
                System.err.println("Invalid input");
                return false;
            }
        } catch (Exception e) {
            System.err.println("Invalid input");
            return false;
        }
        return true;
    }
}

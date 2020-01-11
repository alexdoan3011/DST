import java.util.Scanner;

/**
 * Food item type preserve
 *
 * @author Van Nam Doan
 * @version 1.1
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
     * @param scanner      Scanner object for inputting jar size parameter
     * @param readFromFile reading from file or not
     * @return operation status: true if successful
     */
    @Override
    boolean addItem(Scanner scanner, boolean readFromFile) {
        if (!super.addItem(scanner, readFromFile)) {
            return false;
        }
        if (!readFromFile) {
            System.out.print("Enter the size of the jar in millilitres: ");
            scanner = new Scanner(System.in);
        }
        try {
            jarSize = Integer.parseInt(scanner.nextLine());
            if (jarSize <= 0) {
                if (!readFromFile) {
                    System.err.println("Invalid input");
                }
                return false;
            }
        } catch (Exception e) {
            if (!readFromFile) {
                System.err.println("Invalid input");
            }
            return false;
        }
        return true;
    }

    /**
     * preparing string for saving product information into file
     *
     * @return string prepared
     */
    @Override
    String outputItem() {
        String temp = "";
        temp += "p\n";
        temp += super.outputItem();
        temp += jarSize;
        return temp;
    }
}

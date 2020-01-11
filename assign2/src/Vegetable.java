import java.util.Scanner;

/**
 * Food item type vegetable
 *
 * @author Van Nam Doan
 * @version 1.1
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
     * @param scanner      Scanner object for inputting farm supplier name parameter
     * @param readFromFile reading from file or not
     * @return operation status: true if successful
     */
    @Override
    boolean addItem(Scanner scanner, boolean readFromFile) {
        if (!super.addItem(scanner, readFromFile)) {
            return false;
        }
        if (!readFromFile) {
            System.out.print("Enter the name of the farm supplier: ");
            scanner = new Scanner(System.in);
        }
        farmName = scanner.nextLine();
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
        temp += "v\n";
        temp += super.outputItem();
        temp += farmName;
        return temp;
    }
}

import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * For running the program and display main menu
 *
 * @author Van Nam Doan
 * @version 1.1
 */
public class Assign2 {
    /**
     * main method
     *
     * @param args cmdline parameters
     */
    public static void main(String[] args) {
        Inventory inventory = new Inventory();
        boolean quit = false;
        while (!quit) {
            Scanner scanner = new Scanner(System.in).useDelimiter(Pattern.compile("[\\r\\n]+"));
            Assign2.displayMenu();
            switch (scanner.nextLine()) {
                case "\\p":// hidden option: purge inventory. For debugging purpose
                    Inventory.addItem(null, false);
                    System.out.println("Inventory purged");
                    break;
                case "1":
                    scanner.reset();
                    if (!Inventory.addItem(scanner, false)) {
                        System.err.println("Error adding product");
                        continue;
                    }
                    break;
                case "2":
                    scanner.reset();
                    System.out.println(inventory);
                    break;
                case "3":
                    if (!inventory.updateQuantity(scanner, true)) {
                        System.err.println("Error...could not buy item");
                    }
                    break;
                case "4":
                    scanner.reset();
                    if (!inventory.updateQuantity(scanner, false)) {
                        System.err.println("Error...could not sell item");
                    }
                    break;
                case "5":
                    scanner.reset();
                    System.out.print("Enter the code for the item: ");
                    inventory.searchForItem(scanner);
                    break;
                case "6":
                    scanner.reset();
                    inventory.saveToFile(scanner);
                    break;
                case "7":
                    scanner.reset();
                    inventory.readFromFile(scanner);
                    break;
                case "8":
                    System.out.println("Exiting...");
                    quit = true;
                    break;
                default:
                    scanner.reset();
                    System.err.println("Incorrect value entered");
                    break;
            }
        }
    }

    /**
     * display the main menu
     */
    private static void displayMenu() {
        System.out.print("Please select one of the following:\n" +
                "1: Add Item to Inventory\n" +
                "2: Display Current Inventory\n" +
                "3: Buy Item(s)\n" +
                "4: Sell Item(s)\n" +
                "5: Search for Item\n" +
                "6: Save Inventory to File\n" +
                "7: Read Inventory from File\n" +
                "8: To Exit\n" +
                "> ");
    }
}

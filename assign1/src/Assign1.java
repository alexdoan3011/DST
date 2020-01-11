import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * For running the program and display main menu
 *
 * @author Van Nam Doan
 * @version 1.0
 */
public class Assign1 {
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
            Assign1.displayMenu();
            switch (scanner.nextLine()) {
                case "1":
                    scanner.reset();
                    if (!Inventory.addItem(scanner)) {
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
                    System.out.println("Quitting program...");
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
                "5: To Exit\n" +
                "> ");
    }
}

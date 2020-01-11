import java.time.LocalDate;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * For running the program and display main menu
 *
 * @author Van Nam Doan 040943291
 * @version 1.2
 */
public class Assign3 {
    /**
     * main method
     *
     * @param args cmdline parameters
     */
    public static void main(String[] args) {
        Inventory inventory = new Inventory();
        LocalDate today = LocalDate.now();
        boolean quit = false;
        while (!quit) {
            Scanner scanner = new Scanner(System.in).useDelimiter(Pattern.compile("[\\r\\n]+"));
            Assign3.displayMenu();
            switch (scanner.nextLine()) {
                case "0":
                    System.out.println(today);
                    break;
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
                    scanner.reset();
                    System.out.print("Enter the code for the item: ");
                    inventory.searchForItem(scanner);
                    break;
                case "6":
                    inventory.removeExpiredItems(today);
                    break;
                case "7":
                    inventory.printExpirySummary(scanner);
                    break;
                case "8":
                    System.out.println("Change today's date to (yyyy-mm-dd):");
                    String dateString = scanner.nextLine();
                    try {
                        today = LocalDate.parse(dateString);
                    } catch (Exception e) {
                        System.err.print("Invalid date. ");
                        System.err.println(e.toString().split(": ")[e.toString().split(": ").length - 1]);// print the last statement in the stacktrace aka lazy error message
                        break;
                    }
                    System.out.println("Today's date successfully changed to " + today);
                    break;
                case "9":
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
                "6: Remove Expired Items\n" +
                "7: Print Expiry\n" +
                "8: Change Today's Date\n" +
                "9: To Exit\n" +
                "> ");
    }
}

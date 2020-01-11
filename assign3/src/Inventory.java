import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * For storing inventory item entries and adding new entries
 *
 * @author Van Nam Doan 040943291
 * @version 1.2
 */
public class Inventory {
    /**
     * array of food item entries (database)
     */
    private static ArrayList<InventoryItem> inventory = new ArrayList<>();

    Inventory() {
    }

    /**
     * check the entire inventory if id of the food item in parameter already exist in the array, return location
     * implement recursive binary search
     *
     * @param item food item for checking
     * @param low  the left limit of current searching range
     * @param high the right limit of current searching range
     * @return -1 if cannot find item, or index of item found
     */
    static int alreadyExists(InventoryItem item, Integer low, Integer high) {
        if (inventory.size() == 0) {
            return -1;
        }
        if (high == null) {
            high = inventory.size() - 1;
        }
        int mid;
        if (low.compareTo(high) > 0) {
            return -1;// return -1 if item not found
        }
        mid = (low + high) / 2;
        if (item.compareTo(inventory.get(mid)) == 0) {
            return mid;
        } else if (item.compareTo(inventory.get(mid)) < 0) {
            high = mid - 1;
        } else {
            low = mid + 1;
        }
        return alreadyExists(item, low, high);
    }

    /**
     * adding a new food item into inventory and initializing it
     *
     * @param scanner Scanner object for inputting information
     * @return operation status: true if successful
     */
    static boolean addItem(Scanner scanner) {
        String input = "";
        int choice;
        scanner.reset();
        System.out.print("Do you wish to add a fruit(f), vegetable(v) or a preserve(p)? ");
        switch (scanner.nextLine().toLowerCase()) {
            case "f":
                choice = 1;
                break;
            case "v":
                choice = 2;
                break;
            case "p":
                choice = 3;
                break;
            default:
                System.err.println("Invalid input");
                return false;
        }
        InventoryItem product = new InventoryItem(choice);
        System.out.print("Enter the code for the item: ");
        if (product.inputCode(scanner)) {
            System.err.println("Item code already exists");
            return false;
        }
        System.out.print("Enter the name for the item: ");
        input += scanner.nextLine() + "\n";
        System.out.print("Enter the cost of the item: ");
        if (scanner.hasNextFloat()) {
            float temp = Float.parseFloat(scanner.nextLine());
            if (temp < 0) {
                System.err.println("Invalid input");
                return false;
            }
            input += temp + "\n";
        } else {
            System.err.println("Invalid input");
            return false;
        }
        System.out.print("Enter the sales price of the item: ");
        if (scanner.hasNextFloat()) {
            float temp = Float.parseFloat(scanner.nextLine());
            if (temp < 0) {
                System.err.println("Invalid input");
                return false;
            }
            input += temp + "\n";
        } else {
            System.err.println("Invalid input");
            return false;
        }
        System.out.print("Enter the quantity for the item: ");
        if (scanner.hasNextInt()) {
            int temp = Integer.parseInt(scanner.nextLine());
            if (temp < 0) {
                System.err.println("Invalid input");
                return false;
            }
            input += temp + "\n";
        } else {
            System.err.println("Invalid input");
            return false;
        }
        String dateString;
        while (true) {
            System.out.print("Enter the expiry date of the item (yyyy-mm-dd or none): ");
            dateString = scanner.nextLine();
            if (!dateString.equals("none")) {
                try {
                    LocalDate.parse(dateString);
                } catch (Exception e) {
                    System.err.print("Invalid date. ");
                    System.err.println(e.toString().split(": ")[e.toString().split(": ").length - 1]);// print the last statement in the stacktrace aka lazy error message
                    continue;
                }
                break;
            } else {
                break;
            }
        }
        input += dateString + "\n";
        scanner = new Scanner(input);
        if (!product.addItem(scanner)) {
            return false;
        }
        // look for the right location to add in the new item
        if (inventory.size() == 0) {
            inventory.add(product);
        } else {
            for (int i = 0; i < inventory.size(); i++) {
                if (product.compareTo(inventory.get(0)) < 0) {
                    inventory.add(0, product);
                    break;
                }
                if (product.compareTo(inventory.get(i)) > 0 && (i + 1 == inventory.size() || product.compareTo(inventory.get(i + 1)) < 0)) {
                    inventory.add(i + 1, product);
                    break;
                }
            }
        }
        return true;
    }

    /**
     * remove all items that are expired
     *
     * @param today LocalDate object representing today date by the system or changed by the user
     */
    void removeExpiredItems(LocalDate today) {
        for (InventoryItem inventoryItem : inventory) {
            inventoryItem.removeExpiredItems(today);
        }
    }

    /**
     * update the quantity of a product via buy or sell operations
     *
     * @param scanner   Scanner object for inputting information
     * @param buyOrSell whether the operation is buying (adding)
     * @return operation status: true if successful
     */
    boolean updateQuantity(Scanner scanner, boolean buyOrSell) {
        String operation;
        int sign;
        if (buyOrSell) {
            operation = "buy";
            sign = 1;
        } else {
            operation = "sell";
            sign = -1;
        }
        System.out.println("Enter valid item code: ");
        try {
            InventoryItem product = new InventoryItem(0);
            if (product.inputCode(scanner)) {
                if (alreadyExists(product, 0, inventory.size() - 1) != -1) {
                    System.out.println("Enter valid quantity to " + operation + ": ");
                    int amount = Integer.parseInt(scanner.nextLine());
                    if (amount < 0) {
                        System.err.println("Invalid quantity...");
                        return false;
                    }
                    if (!inventory.get(alreadyExists(product, 0, inventory.size() - 1)).updateQuantity(scanner, sign * amount)) {
                        return false;
                    }
                }
            } else {
                System.err.println("Code not found in inventory...");
                return false;
            }
        } catch (Exception e) {
            System.err.println("Invalid quantity...");
            return false;
        }
        return true;
    }

    /**
     * search for an item using item code and display item to console
     *
     * @param scanner Scanner object to use for input
     */
    void searchForItem(Scanner scanner) {
        try {
            InventoryItem item = new InventoryItem(0);
            if (!item.inputCode(scanner)) {
                System.err.println("Item not found");
            } else {
                int index = Inventory.alreadyExists(item, 0, inventory.size() - 1);
                System.out.println(inventory.get(index));
            }
        } catch (Exception e) {
            System.err.println("Invalid code");
        }
    }

    /**
     * prepare string to print entire inventory to console
     *
     * @return prepared string
     */
    @Override
    public String toString() {
        StringBuilder display = new StringBuilder("Inventory:\n");
        for (int i = 0; i < inventory.size(); i++) {
            display.append(inventory.get(i));
            if (i < inventory.size() - 1) {
                display.append("\n");
            }
        }
        return display.toString();
    }

    /**
     * print expiry information
     *
     * @param scanner Scanner object to use for input
     */
    void printExpirySummary(Scanner scanner) {
        System.out.print("Enter the code for the item: ");
        InventoryItem product = new InventoryItem(0);
        if (product.inputCode(scanner)) {
            int i = alreadyExists(product, 0, inventory.size() - 1);
            if (i != -1) {
                System.out.println(inventory.get(i));
                System.out.println("Expiry Details:");
                inventory.get(i).printExpirySummary();
            }
        } else {
            System.err.println("Code not found in inventory...");
        }
    }
}

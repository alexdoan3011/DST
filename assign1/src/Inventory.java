import java.util.Scanner;

/**
 * For storing food item entries and adding new entries
 *
 * @author Van Nam Doan
 * @version 1.0
 */
public class Inventory {
    /**
     * number of food item entries in database
     */
    private static int numItems;
    /**
     * array of food item entries (database)
     */
    private static FoodItem[] inventory = new FoodItem[20];

    Inventory() {
    }

    /**
     * check the entire inventory if id of the food item in parameter already exist in the database, return location
     *
     * @param item food item for checking
     * @return -1 if cannot find item
     */
    static int alreadyExists(FoodItem item) {
        for (int i = 0; i < numItems; i++) {
            if (inventory[i].isEqual(item)) {
                return i;
            }
        }
        return -1;// return -1 if cannot find item
    }

    /**
     * adding a new food item into inventory and initializing it
     *
     * @param scanner Scanner object for inputting information
     * @return operation status: true if successful
     */
    static boolean addItem(Scanner scanner) {
        if (numItems == 20) {
            System.err.println("Inventory full");
            return false;
        }
        scanner.reset();
        String input = "";
        int choice;
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
        FoodItem product;
        switch (choice) {
            case 1:
                product = new Fruit();
                break;
            case 2:
                product = new Vegetable();
                break;
            case 3:
                product = new Preserve();
                break;
            default:
                return false;
        }
        System.out.print("Enter the code for the item: ");
        if (product.inputCode(scanner)) {
            System.err.println("Item code already exists");
            return false;
        }
        System.out.print("Enter the name for the item: ");
        input += scanner.nextLine() + "\n";
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
        scanner = new Scanner(input);
        if (!product.addItem(scanner)) {
            return false;
        }

        for (int i = 0; i < inventory.length; i++) {
            if (inventory[i] == null) {
                inventory[i] = product;
                numItems++;
                return true;
            }
        }
        numItems++;
        return true;
    }

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
            FoodItem product = new FoodItem();
            if (product.inputCode(scanner)) {
                if (alreadyExists(product) != -1) {
                    System.out.println("Enter valid quantity to " + operation + ": ");
                    int amount = Integer.parseInt(scanner.nextLine());
                    if (amount < 0) {
                        System.err.println("Invalid quantity...");
                        return false;
                    }
                    if (!inventory[alreadyExists(product)].updateItem(sign * amount)) {
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

    @Override
    public String toString() {
        StringBuilder display = new StringBuilder("Inventory:\n");
        for (int i = 0; i < numItems; i++) {
            display.append(inventory[i]);
            if (i < numItems - 1) {
                display.append("\n");
            }
        }
        return display.toString();
    }
}

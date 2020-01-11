import java.time.LocalDate;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * For wrapping initializing and wrapping food item entries with expiry information
 *
 * @author Van Nam Doan 040943291
 * @version 1.2
 */
public class InventoryItem implements Comparable<InventoryItem> {
    private FoodItem item;
    /**
     * quantity of item in stock
     */
    private int itemQuantityInStock;

    /**
     * data structure to contain expiry information
     */
    private Queue<LocalDate> expiries = new LinkedList<>();

    /**
     * constructor that create appropriate food item
     *
     * @param type specify the food item type to create
     */
    InventoryItem(int type) {
        switch (type) {
            case 0:
                item = new FoodItem();
                break;
            case 1:
                item = new Fruit();
                break;
            case 2:
                item = new Vegetable();
                break;
            case 3:
                item = new Preserve();
                break;
        }
    }

    /**
     * display general food item information
     *
     * @return string message
     */
    @Override
    public String toString() {
        return item + " qty: " + itemQuantityInStock;
    }

    /**
     * update item quantity in stock
     *
     * @param scanner Scanner object for inputting information
     * @param amount  amount of update, positive if buying, negative if selling
     * @return operation status: true if successful
     */
    boolean updateQuantity(Scanner scanner, int amount) {
        if (amount < 0) {
            if (-amount > itemQuantityInStock) {
                System.err.println("Insufficient stock in inventory...");
                return false;
            }
            itemQuantityInStock += amount;
            for (int i = 0; i < -amount; i++) {
                expiries.poll();
            }
        } else {
            itemQuantityInStock += amount;
            LocalDate expiryDate = null;
            boolean valid = false;
            while (!valid) {
                valid = true;
                try {
                    System.out.print("Enter the expiry date of the item (yyyy-mm-dd or none): ");
                    String temp = scanner.nextLine();
                    if (!temp.equals("none")) {
                        expiryDate = LocalDate.parse(temp);
                    } else {
                        expiryDate = LocalDate.MAX;
                    }
                } catch (Exception e) {
                    valid = false;
                }
            }
            for (int i = 0; i < amount; i++) {
                expiries.add(expiryDate);
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
        int counter = 0;
        while (expiries.peek() != null) {
            if (expiries.peek().compareTo(today) < 0) {
                expiries.poll();
                itemQuantityInStock--;
                counter++;
            } else {
                break;
            }
        }
        if (counter > 0) {
            System.out.print("An amount of " + counter + " of this following item ha");
            if (counter == 1)
                System.out.print("s");
            else
                System.out.print("ve");
            System.out.print(" been removed due to expiry: ");
            System.out.println(item);
        }
    }

    /**
     * print expiry information
     */
    void printExpirySummary() {
        int counter = 0;
        Queue<LocalDate> tempQueue = new LinkedList<>(expiries);
        LocalDate temp = tempQueue.peek();
        while (true) {
            assert temp != null;
            if (tempQueue.peek() != null && tempQueue.peek().compareTo(temp) == 0) {
                if (counter == 0) {
                    System.out.print(temp);
                }
                tempQueue.poll();
                counter++;
            } else {
                System.out.println(": " + counter);
                counter = 0;
                temp = tempQueue.peek();
                if (tempQueue.peek() == null) {
                    break;
                }
            }
        }
    }

    /**
     * initializing a new food item
     *
     * @param scanner Scanner object for inputting information
     * @return operation status: true if successful
     */
    boolean addItem(Scanner scanner) {
        try {
            item.addItem(scanner);
            this.itemQuantityInStock = Integer.parseInt(scanner.nextLine());
            String temp = scanner.nextLine();
            LocalDate expiryDate;
            if (!temp.equals("none")) {
                expiryDate = LocalDate.parse(temp);
            } else {
                expiryDate = LocalDate.MAX;
            }
            for (int i = 0; i < itemQuantityInStock; i++) {
                expiries.add(expiryDate);
            }
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    /**
     * comparator implementation for sorting (and future usage)
     *
     * @param o FoodItem object being compared to
     * @return positive if date is later than the date being compared to, negative if date is earlier, 0 if the dates are the same
     */
    @Override
    public int compareTo(InventoryItem o) {
        return this.item.compareTo(o.item);
    }

    /**
     * let user input item code and check the status of that input
     *
     * @param scanner Scanner object for inputting item code
     * @return return false if item code does not exists, true if it does
     */
    boolean inputCode(Scanner scanner) {
        boolean valid = false;
        while (!valid) {
            valid = true;
            try {
                this.item.inputCode(scanner);
                if (Inventory.alreadyExists(this, 0, null) == -1) {
                    return false; // return false if cannot find item code
                }
            } catch (Exception e) {
                valid = false;
                System.err.println("Invalid input");
                System.out.println("Enter valid item code:");
            }
        }
        return true;
    }
}

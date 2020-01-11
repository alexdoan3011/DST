import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * For storing food item entries and adding new entries
 *
 * @author Van Nam Doan
 * @version 1.1
 */
public class Inventory {
    /**
     * number of food item entries in database
     */
    private static int numItems;
    /**
     * array of food item entries (database)
     */
    private static ArrayList<FoodItem> inventory = new ArrayList<>();
    /**
     * File names reserved by Windows file system
     */
    private final String PATTERN = "\\A(?!(?:COM[0-9]|CON|LPT[0-9]|NUL|PRN|AUX|com[0-9]|con|lpt[0-9]|nul|prn|aux)|[\\s\\.])[^\\\\\\/:*\"?<>|]{1,254}\\z";

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
    static int alreadyExists(FoodItem item, Integer low, Integer high) {
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
     * @param scanner  Scanner object for inputting information
     * @param fromFile reading from file or not. Prevent printing if reading from file
     * @return operation status: true if successful
     */
    static boolean addItem(Scanner scanner, boolean fromFile) {
        if (scanner == null) {// hidden option: purge inventory. For debugging purpose
            inventory.clear();
            numItems = 0;
            return false;
        }
        String input = "";
        int choice;
        if (!fromFile) {
            scanner.reset();
            System.out.print("Do you wish to add a fruit(f), vegetable(v) or a preserve(p)? ");
        }
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
        if (!fromFile) {
            System.out.print("Enter the code for the item: ");
            if (product.inputCode(scanner, false)) {
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
        }
        if (fromFile) {
            if (product.inputCode(scanner, true)) { // if product already exists in inventory or exist in the temporary storage
                System.err.println("Duplicate item code found");
                return false;
            }
        }
        if (!product.addItem(scanner, fromFile)) {
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
        numItems++;
        return true;
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
            FoodItem product = new FoodItem();
            if (product.inputCode(scanner, false)) {
                if (alreadyExists(product, 0, inventory.size() - 1) != -1) {
                    System.out.println("Enter valid quantity to " + operation + ": ");
                    int amount = Integer.parseInt(scanner.nextLine());
                    if (amount < 0) {
                        System.err.println("Invalid quantity...");
                        return false;
                    }
                    if (!inventory.get(alreadyExists(product, 0, inventory.size() - 1)).updateItem(sign * amount)) {
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
     * save inventory into a file
     *
     * @param scanner Scanner object passed in for input
     */
    void saveToFile(Scanner scanner) {
        System.out.print("Specify a filename to save: ");
        String fileName = scanner.nextLine();
        if (checkFileName(fileName)) {
            ArrayList<String> temp = new ArrayList<>();
            for (int i = 0; i < numItems; i++) {
                temp.add(inventory.get(i).outputItem());
            }
            Path file = Paths.get(fileName);
            try {
                Files.write(file, temp, StandardCharsets.UTF_8);
            } catch (Exception e) {
                System.err.println("File writing error");
            }
        } else {
            System.err.println("File writing error");
        }
    }

    /**
     * Read from file
     *
     * @param scanner Scanner object to use for input
     */
    void readFromFile(Scanner scanner) {
        int itemBefore = inventory.size();
        System.out.println("Name of the file to read from:");
        String fileName = scanner.nextLine();
        if (checkFileName(fileName)) {
            File file = new File(fileName);
            try {
                scanner = new Scanner(file);
                boolean error = false;
                while (scanner.hasNext()) {
                    if (!addItem(scanner, true)) {
                        error = true;
                        break;
                    }
                }
                if (error) {
                    throw new IOException();
                }
            } catch (Exception e) {
                System.err.println("File reading error");
            }
            int itemAfter = inventory.size();
            if (itemAfter - itemBefore == 0) {
                System.out.print("No");
            } else {
                System.out.print(itemAfter - itemBefore);
            }
            System.out.println(" item added");
        } else {
            System.err.println("File import cancelled, no change has been made");
        }
    }

    /**
     * check if file name is legal on Windows system
     *
     * @param fileName the file name inputted
     * @return legal or not
     */
    private boolean checkFileName(String fileName) {
        if (fileName.matches(PATTERN)) {
            return true;
        } else {
            System.err.println("File name does not match Windows file naming convention");
            return false;
        }
    }

    /**
     * search for an item using item code and display item to console
     *
     * @param scanner Scanner object to use for input
     */
    void searchForItem(Scanner scanner) {
        try {
            FoodItem foodItem = new FoodItem();
            if (!foodItem.inputCode(scanner, false)) {
                System.err.println("Item not found");
            } else {
                int index = Inventory.alreadyExists(foodItem, 0, inventory.size() - 1);
                System.out.println(inventory.get(index));
            }
        } catch (Exception e) {
            System.err.println("Invalid code");
        }
    }

//    /**
//     * recursive quick sort implementation on inventory
//     *
//     * @param low  the left limit of current sorting range
//     * @param high the right limit of current sorting range
//     */
//    private static void sort(int low, int high) {
//        if (low < high) {
//            FoodItem pivot = inventory.get(high);
//            int i = (low - 1);  // index of smaller element
//            for (int j = low; j <= high - 1; j++) {// find any element on the right side of the pivot that is smaller than pivot and swap them to the left
//                if (inventory.get(j).compareTo(pivot) < 0) {// If current element is smaller than the pivot
//                    i++;// get the new index of current element
//                    //swap the current element into its new index
//                    FoodItem temp = inventory.get(i);
//                    inventory.set(i, inventory.get(j));
//                    inventory.set(j, temp);
//                }
//            }
//            // swap pivot into the index to the right of the last smaller element
//            FoodItem temp = inventory.get(i + 1);
//            inventory.set(i + 1, inventory.get(high));
//            inventory.set(high, temp);
//            int pivotIndex = (i + 1);
//            sort(low, pivotIndex - 1); // sort before pivot
//            sort(pivotIndex + 1, high); // sort after pivot
//        }
//    }

    /**
     * prepare string to print entire inventory to console
     *
     * @return prepared string
     */
    @Override
    public String toString() {
        StringBuilder display = new StringBuilder("Inventory:\n");
        for (int i = 0; i < numItems; i++) {
            display.append(inventory.get(i));
            if (i < numItems - 1) {
                display.append("\n");
            }
        }
        return display.toString();
    }
}

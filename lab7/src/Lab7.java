import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * Tests the BinaryTree and Node class.
 *
 * @author Linda Crane
 * @author Melissa Sienkiewicz
 */
public class Lab7 {

    /**
     * Main method
     *
     * @param args - Passed in parameters from the command line.
     */
    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);
        keyboard.useDelimiter(Pattern.compile("[\\n\\r]+"));
        BinaryTree tree = new BinaryTree();

        int choice = 0;
        while (choice != 4) {
            displayMenu();
            if (keyboard.hasNext(Pattern.compile("[1-4]"))) {
                choice = keyboard.nextInt();
                switch (choice) {
                    case 1: // Add Value to Tree
                        boolean valid = false;
                        while (!valid) {
                            System.out.print("Enter an integer to add to the tree: ");
                            if (keyboard.hasNextInt()) {
                                valid = true;
                                int toAdd = keyboard.nextInt();
                                tree.insertInTree(toAdd);
                            } else {
                                System.out.println("Invalid integer");
                                valid = false;
                                keyboard.next();
                            }
                        }
                        break;
                    case 2: // Display Tree (In-Order Traversal)
                        System.out.println("Tree Traversal");
                        tree.displayInOrder();
                        System.out.println();
                        break;
                    case 3:
                        // display height
                        System.out.println("Tree Height");
                        System.out.println(tree.displayHeight(null, 0));
                        break;
                    case 4:
                        System.out.println("Exiting...");
                        break;
                    default:
                        break;
                }
            } else {
                System.out.println("Invalid choice");
                choice = 0;
                keyboard.next();
            }
        }
        keyboard.close();
    }

    /**
     * Displays menu to screen
     */
    private static void displayMenu() {
        System.out.println("1: Add Value to Tree");
        System.out.println("2: Display Tree (In-Order Traversal)");
        System.out.println("3: Display Height");
        System.out.println("4: To Exit");
        System.out.print("> ");
    }

}

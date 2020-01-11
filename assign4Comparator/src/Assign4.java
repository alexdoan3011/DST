import java.util.Scanner;

/**
 * run the program and display the main menu loop
 */
public class Assign4 {
    /**
     * main method
     *
     * @param args no cmdline parameter available for this program
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String choice;
        do {
            System.out.println();
            choice = displayMenu(scanner);
            switch (choice) {
                case "1":
                    Manipulator.displayTree();
                    break;
                case "2":
                    Manipulator.addAndSearchContact(scanner, true, false);
                    break;
                case "3":
                    Manipulator.readFromFile(scanner);
                    break;
                case "4":
                    Manipulator.saveToFile(scanner);
                    break;
                case "5":
                    Manipulator.addAndSearchContact(scanner, true, true);
                    break;
                case "6":
                    Manipulator.displayResponsibilities();
                    break;
            }
        } while (!choice.equals("7"));
        System.out.println("Terminating process...");
    }

    /**
     * display the menu, check the input, display error message and loop the menu if invalid input
     *
     * @param scanner Scanner object used to prompt for option input
     * @return String object representing user input
     */
    private static String displayMenu(Scanner scanner) {
        String tmp;
        while (true) {
            System.out.print("Please select one of the following:\n" +
                    "1: Display the Phone Tree\n" +
                    "2: Add one Contact to the List\n" +
                    "3: Add Contacts from a File\n" +
                    "4: Save Contacts to a File\n" +
                    "5: Determine if a Contact is in the List\n" +
                    "6: List out who calls whom\n" +
                    "7: To Exit\n");
            tmp = scanner.nextLine();
            if (!tmp.matches("[1234567]")) {
                System.err.println("Invalid input");
                continue;
            }
            break;
        }
        return tmp;
    }
}

import java.util.ArrayList;
import java.util.Scanner;

public class Lab8 {
    /**
     * program runs here
     *
     * @param args no argument modification available for this program
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String choice;
        ArrayList<String> hashTable = new ArrayList<>(100);
        for (int i = 0; i < 100; i++) {
            hashTable.add(i, "");
        }
        do {
            choice = displayMenu(scanner);
            switch (choice) {
                case "1":
                    while (true) {
                        System.out.print("Enter strings to add or leave empty so stop adding:\n> ");
                        String tmp = scanner.nextLine();
                        if (tmp.equals("")) {
                            break;
                        }
                        addString(hashTable, tmp);
                    }
                    break;
                case "2":
                    System.out.print("Enter a string to search:\n> ");
                    String tmpString = scanner.nextLine();
                    int tmpIndex = hashStringSearch(hashTable, tmpString);
                    while (true) {
                        if (tmpIndex == -1) {
                            System.out.println("String not found");
                            break;
                        } else {
                            if (hashTable.get(tmpIndex).equals(tmpString)) {
                                System.out.println("String found at index " + tmpIndex);
                            }
                        }
                        tmpIndex++;
                        if (tmpIndex > 99) {
                            break;
                        }
                    }
                    break;
            }
        } while (!choice.matches("0"));
        System.out.println("Terminating process...");
    }

    /**
     * display menu and error check user input. Loop when invalid input
     *
     * @param scanner Scanner object for user console input
     * @return String object representing user input 0/1/2
     */
    private static String displayMenu(Scanner scanner) {
        String choice;
        while (true) {
            System.out.println("*******************************************************************************");
            System.out.print("Choose an option:\n0. Exit\n1. Add strings\n2. Search for a string\n> ");
            choice = scanner.nextLine();
            if (!choice.matches("[012]")) {// input error checking
                System.err.println("Invalid input");
                continue;
            }
            break;
        }
        return choice;
    }

    /**
     * generate hash index
     *
     * @param string string to generate hash index from
     * @return hash index
     */
    private static int hash(String string) {
        int hashIndex = 0;
        for (int i = 0; i < (string.length() > 1 ? 2 : 1); i++) {// convert the first (and the second character if available) into int and add them together to create a hash code
            hashIndex += string.charAt(i);
        }
        return hashIndex % 100;// ensure that the code is only 2 digits long. Eliminate the most significant digits if there are more than 2 digits
    }

    /**
     * add a String object to appropriate hashed index in an Arraylist of Strings
     *
     * @param hashTable ArrayList of String to add to
     * @param string    String object to add to Arraylist
     */
    private static void addString(ArrayList<String> hashTable, String string) {
        int hashIndex = hash(string);
        while (!hashTable.get(hashIndex).equals("")) {
            hashIndex++;
            if (hashIndex > 99) {
                System.err.println("String cannot be added");
                return;
            }
        }
        System.out.println("String added at " + hashIndex);
        hashTable.set(hashIndex, string);
    }

    /**
     * search implementation for searching for a string on a hashTable
     *
     * @param hashTable hashTable for search
     * @param string    String object to look for
     * @return index of the string if found, -1 if not
     */
    private static int hashStringSearch(ArrayList<String> hashTable, String string) {
        int index = hash(string);
        while (!hashTable.get(index).equals(string)) {
            index++;
            if (index > 99) {
                index = -1;
                break;
            }
        }
        return index;
    }
}

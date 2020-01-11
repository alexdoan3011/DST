import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Assignment 4, 30-Nov-19
 * contains various methods for manipulating, displaying and writing the DataStructure
 *
 * @author Van Nam Doan 040943291
 * @version 1.0
 */
class Manipulator {
    /**
     * File names reserved by Windows file system
     */
    private final static String FILE_NAME_PATTERN = "\\A(?!(?:COM[0-9]|CON|LPT[0-9]|NUL|PRN|AUX|com[0-9]|con|lpt[0-9]|nul|prn|aux)|[\\s.])[^\\\\/:*\"?<>|]{1,254}\\z";
    /**
     * Phone number patterns that are accepted by this program
     */
    private final static String PHONE_NUM_PATTERN = "(^(\\d[-\\s])?\\(?\\d{3}(-|\\)\\s)\\d{3}[\\s-]\\d{4}$)|(\\d?\\d{10})";
    /**
     * DataStructure to be manipulated
     */
    private static DataStructure<Data> dataStructure = new DataStructure<>();

    /**
     * 3 functions as follow:
     * - (searchMode == false) add a node by prompting user for input. Check input for correctly formatted phone number
     * - (searchMode == false) add multiple nodes by reading from a Scanner object
     * - (searchMode == true) search for a node's existence by prompting user for input
     *
     * @param scanner    Scanner object for prompting user for input, or for reading a file
     * @param IO         true if reading from user input, false if reading from file. Used for displaying input prompt
     * @param searchMode true if in search mode. Method will not add a node in search mode
     * @return true if node successfully added (searchMode == false). True if node found (searchMode == true)
     */
    static boolean addAndSearchContact(Scanner scanner, boolean IO, boolean searchMode) {
        String[] tmp = new String[2]; // this array will store name of contact as the first element and the phone number as the second
        if (IO) {
            System.out.print("Enter name of contact: ");
        }
        tmp[0] = scanner.nextLine();
        if (tmp[0].equals("")) {
            System.err.println("Name of contact cannot be empty");
            return false;
        }
        do {
            if (IO) {
                System.out.print("Enter phone number for contact: ");
            }
            tmp[1] = scanner.nextLine();
            if (!tmp[1].matches(PHONE_NUM_PATTERN)) {// check if valid phone number format
                if (IO && !searchMode) {
                    System.err.println("Invalid phone number format");
                    continue;
                } else if (IO) {// if invalid phone number format in search mode, don't loop input. Throw back to menu instead
                    System.err.println("Invalid phone number format");
                    return false;
                } else {// if invalid phone number format
                    return false;
                }
            }
            break;
        } while (true);
        Data objectToBeStored = new Data(tmp[0], phoneNumberNormalize(tmp[1]));
        DataNode<Data> node = new DataNode<>(objectToBeStored);// create a new DataNode with normalized phone number
        if (searchMode) {
            if (dataStructure.addAndSearchNode(node, true)) {
                System.out.println("Contact found in List");
            } else {
                System.out.println("Contact not in List");
            }
        } else {
            if (!dataStructure.addAndSearchNode(node, false)) {
                System.err.println("Duplicate - not adding " + tmp[0] + ": " + tmp[1]);
            }
        }
        return true;
    }

    /**
     * display all contacts
     */
    static void displayTree() {
        System.out.println("Phone list");
        System.out.println(dataStructure.toString());
    }

    /**
     * display the responsibilities of contacts. Parents will call children
     */
    static void displayResponsibilities() {
        System.out.println("Here are everyone's responsibilities:");
        System.out.println(dataStructure.displayData("calls", "no contact"));
    }

    /**
     * remove any non-digits from a phone number string, leaving only digits
     *
     * @param phoneNumber phone number String to be normalized
     * @return digit-only phone number
     */
    private static String phoneNumberNormalize(String phoneNumber) {
        StringBuilder normalizedPhoneNum = new StringBuilder();
        for (int i = 0; i < phoneNumber.length(); i++) {
            if (Character.isDigit(phoneNumber.charAt(i))) { // take all digits out of the string being fed in then append it to the StringBuilder object
                normalizedPhoneNum.append(phoneNumber.charAt(i));
            }
        }
        if (normalizedPhoneNum.toString().length() < 11) {
            normalizedPhoneNum.insert(0, '1'); // if the length of normalized phone number is less than 11 (10), put a 1 in front
        }
        return normalizedPhoneNum.toString();
    }

    /**
     * prompt user for a legal file name then save the nodes into that file
     *
     * @param scanner Scanner object used to prompt for file name
     */
    static void saveToFile(Scanner scanner) {
        String fileName;
        do {
            System.out.print("Enter name of file to write to: ");
            fileName = scanner.nextLine();
            if (!checkFileName(fileName)) {
                continue;
            }
            break;
        } while (true);
        if (!dataStructure.toFile(fileName)) {
            System.err.println("File writing error");
        }
    }

    /**
     * prompt user for a file name then read the nodes from that file, "binary reshuffle" that input, then add the nodes
     *
     * @param scanner Scanner object used to prompt for file name
     */
    static void readFromFile(Scanner scanner) {
        String fileName;
        ArrayList<String> tmp = new ArrayList<>();
        StringBuilder tmpString = new StringBuilder();
        System.out.print("Enter name of file to read from: ");
        fileName = scanner.nextLine();
        File file = new File(fileName);
        Scanner fscanner;
        try {
            fscanner = new Scanner(file);
        } catch (IOException e) {
            System.err.println("File not found");
            return;
        }
        while (fscanner.hasNext()) {
            tmp.add(fscanner.nextLine() + "\n" + fscanner.nextLine());
        }
        binaryShuffle(tmp, 0, tmp.size() - 1);
        for (String e : tmp) {
            tmpString.append(e).append("\n");
        }
        fscanner = new Scanner(tmpString.toString());
        while (fscanner.hasNext()) {
            if (!addAndSearchContact(fscanner, false, false)) {
                System.err.println("Phone number not formatted properly in file. File reading terminated");
                return;
            }
        }
    }

    /**
     * this recursive method reshuffles an alphabetically ordered input file into a flow more suited for binary tree
     *
     * @param tmp  ArrayList object containing ordered string that needs reshuffling
     * @param low  lower reshuffle range
     * @param high higher reshuffle range
     * @return true when done
     */
    private static boolean binaryShuffle(ArrayList<String> tmp, int low, int high) {
        if (low > high) {
            return true;
        }
        int mid = (low + high) / 2;
        tmp.add(tmp.get(mid));
        tmp.remove(tmp.get(mid));
        return binaryShuffle(tmp, 0, mid - 1) && binaryShuffle(tmp, mid, high - 1);
    }

    /**
     * check if file name is legal on Windows system
     *
     * @param fileName the file name inputted
     * @return true if legal
     */
    private static boolean checkFileName(String fileName) {
        if (fileName.matches(FILE_NAME_PATTERN)) {
            return true;
        } else {
            System.err.println("File name does not match Windows file naming convention");
            return false;
        }
    }
}

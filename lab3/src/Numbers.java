import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class contains the dynamically allocated array and it's processing
 * Student Name: Doan Van Nam
 * Student Number: 040943291
 * Course: CST8130 - Data Structures
 *
 * @author Doan Van Nam
 */
public class Numbers {
    /**
     * File names reserved by Windows file system
     */
    private final String PATTERN = "\\A(?!(?:COM[0-9]|CON|LPT[0-9]|NUL|PRN|AUX|com[0-9]|con|lpt[0-9]|nul|prn|aux)|[\\s\\.])[^\\\\\\/:*\"?<>|]{1,254}\\z";
    /**
     * Stores Float values.
     */
    private Float[] numbers;
    /**
     * Store the number of items currently in the array
     */
    private int numItems = 0;

    /**
     * Default Constructor
     */
    Numbers() {
        numbers = new Float[100];
    }

    /**
     * Constructor that initializes the numbers array to a specified size.
     *
     * @param size - Max size of the numbers array
     */
    Numbers(int size) {
        numbers = new Float[size];
    }

    void sortArray() {
        int n = numItems;
        for (int i = 1; i < n; ++i) {
            float key = numbers[i];
            int j = i - 1;
            /* Move elements of arr[0..i-1], that are
               greater than key, to one position ahead
               of their current position */
            while (j >= 0 && numbers[j] > key) {
                numbers[j + 1] = numbers[j];
                j = j - 1;
            }
            numbers[j + 1] = key;
        }
        // I tried to come up with the algorithm on my own but failed
        // copied from GeeksForGeeks
    }

    /**
     * Adds a value in the array
     *
     * @param keyboard     Scanner object to use for input
     * @param readFromFile reading from a file or from user input
     */
    void initValuesinArray(Scanner keyboard, boolean readFromFile) {
        if (numItems < numbers.length) {
            if (!readFromFile) {
                System.out.print("Enter value: ");
                try {
                    String temp = keyboard.nextLine();
                    numbers[numItems] = Float.parseFloat(temp);
                } catch (Exception e) {
                    keyboard.reset();
                    System.err.println("Invalid input, input is not a float value");
                    return;
                }
                numItems++;
            } else {
                System.out.println("Name of the file to read from:");
                String fileName = keyboard.nextLine();
                ArrayList<Float> tempNumberStorage = new ArrayList<>();
                if (checkFileName(fileName)) {
                    File file = new File(fileName);
                    try {
                        keyboard = new Scanner(file);
                        while (keyboard.hasNextLine()) {
                            try {
                                tempNumberStorage.add(Float.parseFloat(keyboard.nextLine()));
                            } catch (Exception e) {
                                keyboard.reset();
                                System.err.println("Invalid input, file formatting error");
                                System.err.println("File import cancelled, no change has been made");
                                return;
                            }
                        }
                        if (tempNumberStorage.get(0) % 1 != 0 || tempNumberStorage.get(0) - tempNumberStorage.size() + 1 != 0) {
                            keyboard.reset();
                            System.err.println("Invalid input, file formatting error");
                            System.err.println("File import cancelled, no change has been made");
                            return;
                        }
                        if (numbers.length - numItems < tempNumberStorage.get(0)) {
                            keyboard.reset();
                            System.err.println("Insufficient empty memory locations: " + (numbers.length - numItems) + "/" + Math.round(tempNumberStorage.get(0)));
                            System.err.println("File import cancelled, no change has been made");
                            return;
                        }
                        for (int i = 0; i < tempNumberStorage.size() - 1; i++) {
                            numbers[i + numItems] = tempNumberStorage.get(i + 1);
                        }
                        numItems += tempNumberStorage.get(0);
                        System.out.println("Import successful");
                    } catch (Exception e) {
                        System.err.println("File reading error");
                        System.err.println("File import cancelled, no change has been made");
                    }
                } else {
                    System.err.println("File import cancelled, no change has been made");
                }
            }
        } else {
            System.err.println("Array full");
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
     * save array into a file
     *
     * @param keyboard Scanner object passed in for input
     */
    void saveFile(Scanner keyboard) {
        System.out.println("Name of the file to write to:");
        String fileName = keyboard.nextLine();
        if (checkFileName(fileName)) {
            ArrayList<String> temp = new ArrayList<>();
            temp.add(Integer.toString(numItems));
            for (int i = 0; i < numItems; i++) {
                temp.add(Float.toString(numbers[i]));
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
     * add float value from input into file
     *
     * @param keyboard Scanner object passed in for input
     */
    void addValues(Scanner keyboard) {
        if (numItems < numbers.length) {
            int amount;
            System.out.print("How many values do you wish to add? ");
            try {
                String temp = keyboard.nextLine();
                amount = Integer.parseInt(temp);
            } catch (Exception e) {
                keyboard.reset();
                System.err.println("Invalid input");
                return;
            }
            if (numItems + amount > numbers.length) {
                System.err.println("Not enough empty locations in array");
            } else {
                for (int i = 0; i < amount; i++) {
                    System.out.print("Enter value: ");
                    try {
                        String temp = keyboard.nextLine();
                        numbers[numItems] = Float.parseFloat(temp);
                    } catch (Exception e) {
                        keyboard.reset();
                        System.err.println("Invalid input, input is not a float value");
                        return;
                    }
                    numItems++;
                }
            }
        } else {
            System.err.println("Array full");
        }
    }

    /**
     * Calculates the average of all the values in the numbers array.
     *
     * @return float value that represents the average
     */
    public float calcAverage() {
        if (numItems == 0) {
            return 0;
        } else {
            Float temp = (float) 0.0;
            for (int i = 0; i < numItems; i++) {
                temp += numbers[i];
            }
            return (temp / numItems);
        }
    }

    /**
     * for formatting display array
     *
     * @return formatted display array
     */
    @Override
    public String toString() {
        StringBuilder out = new StringBuilder();
        out.append("Numbers are:\n");
        for (int i = 0; i < numItems; i++) {
            out.append(numbers[i]).append("\n");
        }
        return out.toString();
    }

}

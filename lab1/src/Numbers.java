import java.util.Scanner;
import java.util.regex.Pattern;

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
     * Stores Float values.
     */
    private Float[] numbers;

    /**
     * Store the number of items currently in the array.
     */
    private int numItems = 0;

    /**
     * Default Constructor
     */
    public Numbers() {
        numbers = new Float[100];
    }

    /**
     * Constructor that initializes the numbers array to a specified size.
     *
     * @param size - Max size of the numbers array
     */
    public Numbers(int size) {
        numbers = new Float[size];
    }

    /**
     * Adds a value in the array
     *
     * @param keyboard - Scanner object to use for input
     */
    public void initValuesinArray(Scanner keyboard) {
        keyboard.useDelimiter(Pattern.compile("[\\r\\n]+"));
        if (numItems < numbers.length) {
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

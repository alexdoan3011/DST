/**
 * This class contains the dynamically allocated array and it's processing
 * Student Name: Doan Van Nam
 * Student Number: 040943291
 * Course: CST8130 - Data Structures
 *
 * @author Doan Van Nam
 */

import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * this class contains the main method to run the program and method to display selection menu
 */
public class Lab3 {

    /**
     * Runs the program
     *
     * @param args does not have any function
     */
    public static void main(String[] args) {
        Numbers array = new Numbers();// initializing a Numbers object
        int choice;// storing user choice input
        boolean quit = false;// program termination control
        while (!quit) {// loop while the user does not select quit
            Scanner input = new Scanner(System.in).useDelimiter(Pattern.compile("[\\r\\n]+"));
            choice = displayMainMenu();
            switch (choice) {
                case 1: {
                    array = new Numbers();// resetting Numbers object
                    break;
                }
                case 2: {
                    System.out.print("Enter new size of array: ");
                    int size = 0;
                    try {
                        String temp = input.nextLine();
                        size = Integer.parseInt(temp);
                        if (size > 0) {
                            array = new Numbers(size);
                        } else {
                            System.err.println("Size of array has to be bigger than 0");
                        }
                    } catch (Exception e) {
                        System.err.println("Invalid input");
                        input.reset();
                    }
                    break;
                }
                case 3: {
                    array.initValuesinArray(input, false);
                    break;
                }
                case 4: {
                    System.out.println(array);
                    break;
                }
                case 5: {
                    System.out.println("Average is: " + array.calcAverage());
                    break;
                }
                case 6: {
                    array.addValues(input);
                    break;
                }
                case 7: {
                    array.initValuesinArray(input, true);
                    break;
                }
                case 8: {
                    array.saveFile(input);
                    break;
                }
                case 9: {
                    array.sortArray();
                    break;
                }
                case 10: {
                    quit = true;
                    System.out.println("Exiting...");
                }
            }
        }
    }

    /**
     * display operation selection menu
     *
     * @return operation choice
     */
    private static int displayMainMenu() {
        Scanner input = new Scanner(System.in);
        int choice = 0;
        boolean valid = false;
        while (!valid) {
            valid = true;
            try {
                System.out.println("Please select one of the following:\n" +
                        "1: Initialize a default array\n" +
                        "2: To specify the max size of the array\n" +
                        "3: Add value to the array\n" +
                        "4: Display values in the array\n" +
                        "5: Display the average of the values\n" +
                        "6: Enter multiple values\n" +
                        "7: Read values from file\n" +
                        "8: Save values to file\n" +
                        "9: Sort the array\n" +
                        "10: To exit");
                System.out.print("> ");
                String temp = input.nextLine();
                choice = Integer.parseInt(temp);
                if (choice > 10 || choice < 1) {
                    System.err.println("Invalid input");
                }
            } catch (Exception e) {
                System.err.println("Invalid input");
                valid = false;
                input.reset();
            }
        }
        return choice;
    }
}

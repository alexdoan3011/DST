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

public class Lab1 {

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
                    } catch (Exception e) {
                        System.err.println("Invalid input");
                        input.reset();
                    }
                    if (size > 0) {
                        array = new Numbers(size);
                    } else {
                        System.err.println("Size of array has to be bigger than 0");
                    }
                    break;
                }
                case 3: {
                    array.initValuesinArray(input);
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
                        "6: To Exit");
                System.out.print("> ");
                String temp = input.nextLine();
                choice = Integer.parseInt(temp);
                if (choice > 6 || choice < 1) {
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

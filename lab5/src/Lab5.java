import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Lab5 {
    private static ArrayList<Integer> numList = new ArrayList<Integer>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in).useDelimiter(Pattern.compile("[\\r\\n]+"));
        int choice = -1;
        while (choice != 4) {
            try {
                choice = menu(scanner);
                switch (choice) {
                    case 1:
                        System.out.println("Please specify an Integer");
                        numList.add(Integer.parseInt(scanner.nextLine()));
                        Collections.sort(numList);
                        break;
                    case 2:
                        System.out.println("Please specify an Integer: ");
                        int index = binarySearch(numList, 0, numList.size() - 1, Integer.parseInt(scanner.nextLine()));
                        System.out.print("Index of Integer is: ");
                        if (index == -1) {
                            System.out.println("Not Found");
                        } else {
                            System.out.println(index);
                        }
                        break;
                    case 3:
                        System.out.println(numList.toString());
                        break;
                    case 5:
                        addRandomInteger();
                        break;
                    case 4:
                        continue;
                    default:
                        throw new java.lang.NumberFormatException();
                }
            } catch (Exception e) {
                System.err.println("Invalid input");
            }
        }
    }

    private static int menu(Scanner scanner) {
        int choice = -1;
        System.out.println("Please Enter:\n" +
                "1. Add Item\n" +
                "2. Search for Item\n" +
                "3. Display List\n" +
                "4. Exit");
        System.out.println("5. Add 10 random numbers");
        choice = Integer.parseInt(scanner.nextLine());
        return choice;
    }

    static int binarySearch(ArrayList<Integer> numList, int low, int high, int x) {
        if (high >= low) {
            int mid = low + (high - low) / 2;
            if (numList.get(mid) == x) {
                return mid;
            }
            if (numList.get(mid) > x) {
                return binarySearch(numList, low, mid - 1, x);
            }
            return binarySearch(numList, mid + 1, high, x);
        }

        return -1;
    }

    private static void addRandomInteger() {
        for (int i = 0; i < 11; i++) {
            numList.add(new Random().nextInt(200) - 99);
        }
        Collections.sort(numList);
        System.out.println(numList);
    }
}


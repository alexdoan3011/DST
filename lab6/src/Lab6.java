import java.util.LinkedList;
import java.util.Scanner;

public class Lab6 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = "";
        while (true) {
            System.out.print("Enter an expression (or exit to terminate): ");
            input = scanner.nextLine();
            if (input.equals("exit")) {
                System.out.println();
                System.out.println("Terminating....");
                break;
            }
            LinkedList<Character> linkedList = new LinkedList<>();
            boolean mismatch = false;
            for (int i = 0; i < input.length(); i++) {
                if (input.charAt(i) == '{' || input.charAt(i) == '(') {// add opening brackets to the list
                    linkedList.addFirst(input.charAt(i));
                }
                // and remove opening brackets if a matching closing bracket is found
                else if (input.charAt(i) == '}' || input.charAt(i) == ')') {
                    if (linkedList.isEmpty()) {// if list is already empty despite closing brackets still presenting, we have more closing brackets than needed
                        mismatch = true;
                        break;
                    }
                    // if the closing bracket does not match the opening one
                    if (input.charAt(i) == '}') {
                        if (!linkedList.removeFirst().equals('{')) {
                            mismatch = true;
                            break;
                        }
                    }
                    if (input.charAt(i) == ')') {
                        if (!linkedList.removeFirst().equals('(')) {
                            mismatch = true;
                            break;
                        }
                    }
                }
            }
            if (!linkedList.isEmpty()) {// check if there is anything remaining after the loop. If there is, all brackets are not closed
                mismatch = true;
            }
            System.out.print("The expression is ");
            if (mismatch) {
                System.out.print("NOT ");
            }
            System.out.println("balanced");
        }
        scanner.close();
    }
}

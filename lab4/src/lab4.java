import java.util.Scanner;

public class lab4 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        boolean exit = false;
        while (!exit) {
            System.out.print("Enter a word or type \"\\e\" to quit\n > ");
            String word = input.nextLine();
            if (word.equals("\\e")) {
                System.out.println("Terminating application...");
                exit = true;
                continue;
            }
            System.out.print("The word " + word + " IS");
            if (!isPalindrome(word)) {
                System.out.print(" NOT");
            }
            System.out.println(" a palindrome");
        }
    }

    private static boolean isPalindrome(String input) {
        if (input.length() < 2) {
            return true;
        }
        if (input.charAt(0) == input.charAt(input.length() - 1)) {
            return isPalindrome(input.substring(1, input.length() - 1));
        }
        return false;
    }
}

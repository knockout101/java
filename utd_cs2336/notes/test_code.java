import java.util.Scanner;

public class test_code {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        
        System.out.print("Please enter a string: ");
        
        String userInput = input.nextLine();

        System.out.println("String: " + userInput);
        System.out.println("Length: " + userInput.length());
        System.out.println("First: " + userInput.charAt(0));
        System.out.println("Last: " + userInput.charAt(userInput.length() - 1));
    }
}

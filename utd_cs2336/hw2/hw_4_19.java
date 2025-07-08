import java.util.Scanner;

public class hw_4_19 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.print("Enter the first 9 digits of an ISBN as integer: ");
        String number = input.nextLine();

        if (number.length() != 9) {
        System.out.println("You need to enter exactly 9 digits");
        System.exit(1);
        }
        // WRITE YOUR CODE HERE
        // int checksum equal to 0
        int checksum = 0;

        // loop 9 times calling Integer.parseInt() on each character from the temp_integer string
        for(int i = 0; i < 9; i++) {
        // Sum up the integer value of each character multiplied by its index + 1
            checksum += Integer.parseInt(String.valueOf(number.charAt(i))) * (i + 1);
        }

        // do checksum is equal to the checksum modulus 11
        checksum %= 11;
        // if checksum is equal to 10 concatenate "X" to temp_integer and display it to the console
        if(checksum == 10)
            number += "X";
        // else make checksum a new String, concatenate it to temp_integer, and display it to the console 
        else
            number += "" + checksum;
        // display the 10 digit ISBN
        System.out.println("The ISBN is: " + number);
    }
}
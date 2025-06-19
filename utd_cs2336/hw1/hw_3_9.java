import java.util.Scanner;

public class hw_3_9 {
    public static void main(String[] args) {
        // prompt user for the first 9 digits of an ISBN as an integer
        System.out.print("Enter the first 9 digits of an ISBN as integer: ");

        // String temp_integer equal to return value of .nextLine()
        Scanner input = new Scanner(System.in);
        int temp_integer = input.nextInt();

        // int checksum equal to 0
        int checksum = 0;

        // temp integer for holding the remainder
        int temp = 0;

        // calculate the new temp value by modulus operation and multiple by the digit positon
        temp = temp_integer % 10;
        checksum += temp * 1;

        temp = temp_integer % 10;
        checksum += temp * 2;

        temp = temp_integer % 10;
        checksum += temp * 3;

        temp = temp_integer % 10;
        checksum += temp * 4;

        temp = temp_integer % 10;
        checksum += temp * 5;

        temp = temp_integer % 10;
        checksum += temp * 6;

        temp = temp_integer % 10;
        checksum += temp * 7;

        temp = temp_integer % 10;
        checksum += temp * 8;

        temp = temp_integer % 10;
        checksum += temp * 9;

        // do checksum is equal to the checksum modulus 11
        checksum %= 11;

        // if checksum is equal to 10 concatenate "X" to temp_integer and display it to the console
        if(checksum == 10) {
            System.out.println("The ISBN-10 number is " + temp_integer + "X");
            return;
        }

        // else make checksum a new String, concatenate it to temp_integer, and display it to the console 
        System.out.println("The ISBN-10 number is " + temp_integer + String.valueOf(checksum));
    }
}
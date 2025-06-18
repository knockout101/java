import java.util.Scanner;

public class hw_3_9 {
    public static void main(String[] args) {
        // prompt user for the first 9 digits of an ISBN as an integer
        System.out.print("Enter the first 9 digits of an ISBN as integer: ");

        // String temp_integer equal to return value of .nextLine()
        Scanner input = new Scanner(System.in);
        String temp_integer = input.nextLine();

        // if the String.length() is not equal to 9, exit program with the error message "Incorrect number of digits"
        if(temp_integer.length() != 9) {
            System.out.println("Incorrect number of digits");
            return;
        }

        // int checksum equal to 0
        int checksum = 0;

        // call Integer.parseInt() on temp_interger.charAt(index) and 
        // multiple by (index + 1) and add to checksum 9 times
        checksum += Integer.parseInt(String.valueOf(temp_integer.charAt(0))) * 1;
        checksum += Integer.parseInt(String.valueOf(temp_integer.charAt(1))) * 2;
        checksum += Integer.parseInt(String.valueOf(temp_integer.charAt(2))) * 3;
        checksum += Integer.parseInt(String.valueOf(temp_integer.charAt(3))) * 4;
        checksum += Integer.parseInt(String.valueOf(temp_integer.charAt(4))) * 5;
        checksum += Integer.parseInt(String.valueOf(temp_integer.charAt(5))) * 6;
        checksum += Integer.parseInt(String.valueOf(temp_integer.charAt(6))) * 7;
        checksum += Integer.parseInt(String.valueOf(temp_integer.charAt(7))) * 8;
        checksum += Integer.parseInt(String.valueOf(temp_integer.charAt(8))) * 9;

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
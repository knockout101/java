import java.util.Scanner;

public class hw_6_32 {
    public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    
    System.out.print("Enter a credit card number as a long integer: ");
    long number = input.nextLong();
    
    if (isValid(number))
    System.out.println(number + " is valid");
    else
    System.out.println(number + " is invalid"); 
    }

    // WRITE YOUR CODE HERE

    // define the following methods

    /** Return true if the card number is valid */
    public static boolean isValid(long number) {
        // int sum equal to 0
        int sum = 0;
        // Using sumOfDoubleEvenPlace calculate the second step of the luhn's algorithm, adding to "sum"
        sum += sumOfDoubleEvenPlace(number);
        // Using the sumOfOddPlace method to calculate the odd place values and add it to "sum"
        sum += sumOfOddPlace(number);
        // return "sum" is divisible by 10 doing the modulus of "sum" and 10 equal to 0
        return sum % 10 == 0;
    }
    /** Get the result from Step 2 */
    public static int sumOfDoubleEvenPlace(long number) {
        // int temp equal to 0
        int temp = 0;
        // for loop through number starting at int i equals 1, if i is less than getSize(number), for i + 2
        // incrementing across the number's even positions
        for(int i = 1; i < getSize(number); i += 2) {
        // in the loop extract, create int x equal to getPrefix(number, getSize(number) - i) modulus 10, this extracts the current digit 
        int x = (int)(getPrefix(number, getSize(number) - i) % 10);
        // replace x with getDigit(x)
        x = getDigit(x);
        // sum up each digit into temp
        temp += x;
        }
        // after the loop return temp
        return temp;
    }
    /** Return this number if it is a single digit, otherwise,
     * return the sum of the two digits */
    public static int getDigit(int number) {
        // int doubled equals number * 2
        int doubled = number * 2;
        // if doubled is less than 9 return doubled
        if(doubled < 10)
        return doubled;
        // return (doubled / 10) + (doubled % 10)
        return (doubled / 10) + (doubled % 10);
    }
    /** Return sum of odd-place digits in number */
    public static int sumOfOddPlace(long number) {
        // int temp equal to 0
        int temp = 0;
        // for loop through number, using int i equal to 0, if i is less than getSize(number), incrementing by i + 2
        // this catches all odd values 
        for(int i = 0; i < getSize(number); i += 2) {
            // within the loop, create int x equal to getPrefix(number, getSize(number) - i) modulus 10, this extracts the current digit
            int x = (int)(getPrefix(number, getSize(number) - i) % 10);
            // sum the int x into int temp 
            temp += x;
        }
        // after the loop ends, return int x 
        return temp;
    }
    /** Return true if the digit d is a prefix for number */
    public static boolean prefixMatched(long number, int d) {
        // long temp equal to Math.pow(10, getSize(number) - 1) to create a value as large as the card number
        long temp = (long)Math.pow(10, getSize(number) - 1);
        // temp equal to number / temp to extract the last digit and truncate the rest of the number
        temp = number / temp;
        // return temp is equal to int d
        return temp == d;
    }

    /** Return the number of digits in d */
    public static int getSize(long d) {
        // int counter equal to 0
        int counter = 0;
        // do a while loop with the condition that number / 10 is not equal to 0
        while(d / 10 != 0) {
        // within the loop, iteratively increment the counter by 1 and do number /= 10 to continue 
        // to push the number down until its only 1 digit left, which should end the while loop
            counter++;
            d /= 10;
        }
        // return counter + 1, adding the last digit that was not counted
        return counter + 1;
    }
    /** Return the first k number of digits from number. If the
     * number of digits in number is less than k, return number. */
    public static long getPrefix(long number, int k) {

        // int temp equal to getSize(number) - k to get the complementary value of the expected digits
        // which are the digits to remove from number from right to left 
        int temp = getSize(number) - k;
        // if temp is less than or equal to 0 return number 
        if(temp <= 0)
            return number;
        // do for loop starting with int i equal to 0, if i is less than temp, incrementing by 1
        for(int i = 0; i < temp; i++) {
            // in the loop, do number /= 10, temp number of times as each division removes 1 digit
            number /= 10;
        }
        // after the loop ends return number
        return number;
    }
}


import java.util.Scanner;

public class hw_2_13 {
    public static void main(String[] args) {
        // double Sum starting at 0
        double sum = 0;
        
        // Previous value set to 0
        double previous = 0;
        
        // Ask user for monthly savings
        Scanner input = new Scanner(System.in);
        System.out.print("Enter monthly savings amount: ");
        double monthly_savings = input.nextDouble();
        
        // formula: Sum = (Monthly Savings + Sum) * (1 + Interest Rate)
        // Iterate the formula 6 times for the 6 months of savings
        double interest_rate = 0.00417;
        sum = (monthly_savings + sum) * (1 + interest_rate); // 1
        sum = (monthly_savings + sum) * (1 + interest_rate); // 2
        sum = (monthly_savings + sum) * (1 + interest_rate); // 3
        sum = (monthly_savings + sum) * (1 + interest_rate); // 4
        sum = (monthly_savings + sum) * (1 + interest_rate); // 5
        sum = (monthly_savings + sum) * (1 + interest_rate); // 6
        // display the account value after the sixth month on the console
        System.out.printf("After the sixth month, the account is $%.2f\n", sum);
        // end
    }
}
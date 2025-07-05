public class hw_5_22 {
    public static void main(String[] args) {
    java.util.Scanner input = new java.util.Scanner(System.in);

    // Enter loan amount
    System.out.print(
      "Enter loan amount, for example 120000.95: ");
    double loanAmount = input.nextDouble();

    // Enter number of years
    System.out.print(
      "Enter number of years as an integer, for example 5: ");
    int numOfYears = input.nextInt();

    // Enter yearly interest rate
    System.out.print("Enter yearly interest rate, for example 8.25: ");
    double annualInterestRate = input.nextDouble() / 100.0; 

// WRITE YOUR CODE HERE
    // print out balance and 
    
    // double interest, principal, and balance equal to 0
    double interest, principal, balance;
    interest = principal = 0;
    // calculating balance after the loan is affected
    balance = loanAmount * Math.pow(1 + annualInterestRate, numOfYears);

    // Convert interest rate to monthly
    double monthlyInterestRate = (annualInterestRate / 12);

    // double monthly payment calculation
    // monthly payment formula M = (P * r/12 * Math.pow(1 + r/12, 12*t)) / (Math.pow(1 + r/12, 12*t) - 1)
    double monthlyPayment = (loanAmount * monthlyInterestRate * Math.pow(1 + monthlyInterestRate, 12 * numOfYears))
                          / (Math.pow(1 + monthlyInterestRate, 12 * numOfYears) - 1);

    // display the monthly payment and final payout at the end of the loan
    System.out.printf("Loan Amount: $%.2f\n", loanAmount);
    System.out.printf("Number of Years: %d\n", numOfYears);
    System.out.printf("Annual Interest Rate: %.1f%%\n", annualInterestRate * 100.0);
    System.out.printf("Monthly Payment: $%.2f\n", monthlyPayment);
    System.out.printf("Total Payment: $%.2f\n\n", balance);

    // display a header for the table of values generated when iterating, balance, principle, interest and payment #
    System.out.println("Payment #\tInterest\tPrincipal\tBalance");
    // iterate number of years * 12 times 
    for(int i = 0; i < numOfYears * 12; i++) {
      // displaying the payment cycle number 
      // calculating and displaying the new principle based on the interest deduction 
      // and the overall balance per payment cycle 
        interest = monthlyInterestRate * balance;
        principal = monthlyPayment - interest;
        // if the last payment is greater than 0, add it to the final payment cycle expense
        // making the balance 0 after the final payment
        if(i + 1 == numOfYears * 12) {
          monthlyPayment += balance - principal;
        }
        balance = balance - principal;
        // quick fix for negative balance when < 0 (e.g -0.00001111)
        if (balance < 0 && balance > -0.01)
            balance = 0;
        System.out.printf("%d\t\t$%.2f\t\t$%.2f\t\t$%.2f\n", i + 1, interest, principal, balance);
    }
  }
}
import java.util.Scanner;

public class hw_3_1 {
    public static void main(String[] args) {
        // double a, b, c all equal to 0
        double a = 0, b = 0, c = 0;
        
        // prompt user for 3 values separated by spaces ("a b c")
        Scanner input = new Scanner(System.in);
        System.out.print("Enter a, b, c: ");
        
        // Call .nextDouble 3 times filling a, b, and c with their respective inputs
        a = input.nextDouble();
        b = input.nextDouble();
        c = input.nextDouble();
        
        // double discriminant equals Math.pow(b, 2) - (4 * a * c)
        double determinant = Math.pow(b, 2) - (4 * a * c);

        // calculate the roots from the equations r1 = (-b + Math.pow(determinant, 0.5)) / (2 * a) and
                                               // r2 = (-b - Math.pow(determinant, 0.5)) / (2 * a)
        double r1 = (-b + Math.pow(determinant, 0.5)) / (2 * a);
        double r2 = (-b - Math.pow(determinant, 0.5)) / (2 * a);

        // if determinant is greater than 0 display both -r1 and -r2 (negatives because x - a = 0 -> x = a)
        if(determinant > 0) {
            System.out.printf("The equation has two roots: %.2f and %.2f\n", r1, r2);
            return;
        }

        // if determinant is zero display 1 zero (because r1 = r2)
        if(determinant == 0) {
            System.out.printf("The equation has one root: %.2f\n", r1);
            return;
        }

        // Otherwise display "The equation has no real roots"
        System.out.println("The equation has no real roots");
    }
}
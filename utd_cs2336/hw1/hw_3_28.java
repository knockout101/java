import java.util.Scanner;

public class hw_3_28 {
    public static void main(String[] args) {
        // double x1, y1, width1, height1
        // double x2, y2, width2, height2
        double x1, y1, width1, height1;
        double x2, y2, width2, height2;

        // prompt user for rect1 center coordinates (x1, y1) and its length and width (length1, width1)
        // prompt user for rect2 center coordinates (x2, y2) and its legnth and width (length2, width2)
        Scanner input = new Scanner(System.in);
        System.out.print("Enter r1's center x-, y-coordinates, width, height: ");
        x1 = input.nextDouble();
        y1 = input.nextDouble();
        width1 = input.nextDouble();
        height1 = input.nextDouble();

        System.out.print("Enter r2's center x-, y-coordinates, width, height: ");
        x2 = input.nextDouble();
        y2 = input.nextDouble();
        width2 = input.nextDouble();
        height2 = input.nextDouble();

        // int counter equals 0
        int counter = 0;

        // double xvert equals x2 - (width2 / 2) 
        // double yvert equals y2 - (length2 / 2)
        double xvert = x2 - (width2 / 2);
        double yvert = y2 - (height2 / 2);

        // if (xvert, yvert) is within rect1 increment counter
        if(xvert > (x1 - width1 / 2) && xvert < (x1 + width1 / 2))
            if(yvert > (y1 - height1 / 2) && yvert < (y1 + height1 / 2)) {
                counter += 1;
           }    

        // set xvert equal to x2 + (width2 / 2)
        xvert = x2 + width2 / 2;

        // if new (xvert, yvert) is within rect1 increment counter 
        if(xvert > (x1 - width1 / 2) && xvert < (x1 + width1 / 2))
            if(yvert > (y1 - height1 / 2) && yvert < (y1 + height1 / 2)) {
                counter += 1;
           }

        // set yvert equal to y2 + (length2 / 2)
        yvert = y2 + height2 / 2;

        // if new (xvert, yvert) is within rect1 increment counter 
        if(xvert > (x1 - width1 / 2) && xvert < (x1 + width1 / 2))
            if(yvert > (y1 - height1 / 2) && yvert < (y1 + height1 / 2)) {
                counter += 1;
           }

        // set yvert equal to x2 - (width2 / 2)
        xvert = x2 - width2 / 2;

        // if new (xvert, yvert) is within rect1 increment counter 
        if(xvert > (x1 - width1 / 2) && xvert < (x1 + width1 / 2))
            if(yvert > (y1 - height1 / 2) && yvert < (y1 + height1 / 2)) {
                counter += 1;
           }

        // if counter is 4 display that r2 is inside r1
        if(counter == 4)
           System.out.println("r2 is inside r1");

        // else if counter is greater than 0 display that r2 overlaps r1 
        else if(counter > 0)
           System.out.println("r2 overlaps r1");

        // else display r2 does not overlap r1
        else
           System.out.println("r2 does not overlap r1");
    }
}
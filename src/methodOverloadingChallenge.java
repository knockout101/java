public class methodOverloadingChallenge
{
    public static void main(String[] args) 
    {
        int inches = 30;
        double centimeters = convertToCentimeters(inches);
        System.out.println(inches + " inches and " + centimeters + " centimeters");
    }

    public static double convertToCentimeters(int inches)
    {
        return (double) inches * 2.54;
    }

    public static double convertToCentimeters(int feet, int inches)
    {
        inches = inches + feet*12;
        return (double)inches * 2.54;
    }
}
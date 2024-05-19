public class FactorPrinter {
    public static void main(String[] args)
    {
        int num = 341324;
        printFactors(num);
    }
    public static void printFactors(int number)
    {
        for(int i = number; i > 0; i--)
        {
            if(number % i == 0)
                System.out.println(i);
        }
    }
}

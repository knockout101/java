public class SharedDigit {
    public static void main(String[] args)
    {
        int num1 = 54234, num2 = 2324354;
        boolean res = hasSharedDigit(num1, num2);
        System.out.println("Result = " + res);
    }

    public static boolean hasSharedDigit(int num1, int num2)
    {
        char[] num1_c = String.valueOf(num1).toCharArray();
        char[] num2_c = String.valueOf(num2).toCharArray();

        for(int i = 0; i < num1_c.length; i++)
        {
            for(int j = 0; j < num2_c.length; j++)
            {
                if(num1_c[i] == num2_c[j])
                    return true;
            }
        }
        return false;
    }
}

public class LargestPrime
{
    public static void main(String[] args)
    {
        int res = getLargestPrime(53430);
        System.out.println(res);
    }

    public static int getLargestPrime(int number)
    {
        if(number < 0)
            return -1;
        
        int res = 0;
            
        for(int i = 1; i <= number; i++)
        {
            if(number % i == 0)
            {
                for(int j = 0; j < i; j++)
                {
                    if(i % j == 0)
                }
            }   
        }
        return res;
    }
    
    public static boolean checkPrime(int number)
    {
        for(int i = 2; i < number; i++)
        {
            if(number % i == 0)
                return false;
        }
        return true;
    }
}
                                                                                                                                                                                                                                                                                                                                                            
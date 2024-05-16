public class FlourPacker {
    public static void main(String[] args)
    {
        boolean res = canPack(1, 0, 5);

        System.out.println(res);
    }
    public static boolean canPack(int bigCount, int smallCount, int goal)
    {
        if(bigCount < 0 || smallCount < 0 || goal < 0)
            return false;
            
        if(((bigCount*5) + (smallCount)) >= goal)
            {
                int temp = goal;
                while(temp > 0)
                {
                    temp -= 5;
                    if(temp < 1)
                    {
                        if(temp < 0)
                            temp += 5;
                        return temp <= smallCount;
                    }
                }
            }
        return false;
    }
}

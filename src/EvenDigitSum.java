public class EvenDigitSum {
    public static void main(String[] args)
    {
        int number = 2349234;
        int sum = getEvenDigitSum(number);
        System.out.println(sum);
    }
    public static int getEvenDigitSum(int number)
    {
        if(number < 0)
            return -1;
        int sum = 0;
        char[] num_char = Integer.toString(number).toCharArray();
        int arr_len = String.valueOf(number).length();
        int[] num_arr = new int[String.valueOf(number).length()];
        for(int i = 0; i < arr_len; i++)
        {
            int value = Character.getNumericValue(num_char[i]);
            if(value % 2 == 0)
                sum += value;
        }
        return sum;
    }
}

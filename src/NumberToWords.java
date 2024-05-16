public class NumberToWords {
    public static void main(String[] args) 
    {
        int number = 632;

        numberToWords(number);
    }
    public static void numberToWords(int number)
    {
        if(number < 0)
            System.out.println("Invalid Value");
        
        char[] numArr = String.valueOf(number).toCharArray();
        int arrLen = String.valueOf(number).length();

        for(int i = 0; i < arrLen; i++)
        {
            switch(numArr[i])
            { 
                case '1' -> System.out.println("one"); 
                case '2' -> System.out.println("two"); 
                case '3' -> System.out.println("three"); 
                case '4' -> System.out.println("four"); 
                case '5' -> System.out.println("five");
                case '6' -> System.out.println("six");
                case '7' -> System.out.println("seven");
                case '8' -> System.out.println("eight");
                case '9' -> System.out.println("nine");
                case '0' -> System.out.println("zero");
            }
        }
    }
}

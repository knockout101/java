import java.util.Arrays;
import java.util.Random;

public class hw_7_32 {
    /* method that partitions the list using the first element */
    public static int partition(int[] list) {
        // int n equal to list[0]
        // int pivot equal to list[1]
        // int end equal to n
        // int start equal to 1
        int n = list[0];
        int pivot = list[1];
        int end = n;
        int start = 1;

        // int[] tempList equal to Arrays.copyOf(list, n + 1)
        int[] tempList = Arrays.copyOf(list, n + 1);

        // for loop with index starting at 2, index < n + 1 incrementing by 1
        for(int i = 2; i < n + 1; i++){
            // if tempList[index] is less than or equal to pivot
            if(tempList[i] <= pivot)
                // list[start++] equal to tempList[index] 
                list[start++] = tempList[i];
            // else if tempList[index] is greater than pivot 
            else if(tempList[i] > pivot)
                // list[end--] equal to tempList[index]
                list[end--] = tempList[i];
        }
        // after the loop, list[start] equal to pivot
        list[start] = pivot;

        // return start
        return start;
    }
    public static void main(String[] args) {
        Random generator = new Random(System.currentTimeMillis());
        int randomSize = generator.nextInt(10);
        // generate random list
        int[] list = new int[randomSize + 1];
        // put randomSize as the first value of the list
        list[0] = randomSize;

        // Fill randomly sized list with random ints
        // skipping the first element
        for(int i = 1; i < randomSize + 1; i++)
            list[i] = generator.nextInt(21);

        // Print out list before partition
        for(var e: list)    
            System.out.print(" " + e);
        
        System.out.println("");

        int pivotIndex = partition(list);
        
        for(int j = 1; j < randomSize + 1; j++)
            System.out.print(" " + list[j]);
        
        System.out.println("");

        System.out.println("Pivot index: " + (pivotIndex - 1));
        System.out.println("Pivot: " + list[pivotIndex]);
    }
}

import java.util.*;

public class SortsRunner
{
    public static final int RANGE = 100000;
    
    /**
     * Randomly fill up the given array with integers from 0 to 100000
     */
    public static void fillArray(int [] arr)
    {
        Random randGen = new Random();
        for(int i = 0; i < arr.length; i++)
            arr[i] = randGen.nextInt(RANGE);
    }
    
    /**
     * copy the content in second array to first array. We assume 
     * two array has same size
     * @param firstArray the destination of copy action
     * @param secondArray the source of copy action
     */
    public static void copy(int [] firstArray, int [] secondArray)
    {
        for(int i = 0; i < secondArray.length; i++)
            firstArray[i] = secondArray[i];
    }
    /**
     * isSorted
     * @return true if the array is sorted from least to largest
     * or is sorted from largest to least
     */
    public static boolean isSorted(int [] arr)
    {
        if(arr.length <= 1) return true;
        int index = 0;
        while(index < arr.length-1 && arr[index] == arr[index+1])
        {
            index++;
        }
        // bypass first equal elements
        if(index == arr.length - 1) return true; // all elements are the same
        else if(index < arr.length - 1 && arr[index] > arr[index+1]) // possible descend
        {
            for(int i = index+2; i < arr.length; i++)
                if(arr[i] > arr[i-1]) return false;
        }
        else // sort for ascend
        {
             for(int i = index+2; i < arr.length; i++)
                if(arr[i] < arr[i-1]) return false;
        }
        
        return true;
    }
    
    /**
     * test the sort with given array. The test array will not be modified
     * @param arr The array that is used to test the sort. The array will not be modified
     * @name The name of sort method that is to be tested
     */
    public static void testSort(int [] arr, String name)
    {
        long totalTime = 0;
        int [] a = new int[arr.length];
        copy(a, arr); // copy arr to a
        
        // get start time
        long start = Calendar.getInstance().getTimeInMillis();
        switch (name) {
            case "Insert Sort" -> Sorts.insertSort(a);
            case "Select Sort" -> Sorts.selectSort(a);
            case "Quick Sort" -> Sorts.quickSort(a);
            case "Merge Sort" -> Sorts.mergeSort(a);
            case "Heap Sort" -> Sorts.heapSort(a);
            default -> {
            }
        }
            
        long end = Calendar.getInstance().getTimeInMillis();
        if(isSorted(a)){
            totalTime = end - start;
            System.out.println("Execution time for " + name + " is: " + totalTime + " milliseconds");
        }
        else
            System.out.println("The " + name + " did not work properly");
    }
    
    /**
     * display the test menu
     */
    public static void displayMenu()
    {
        System.out.println("***************************");
        System.out.println("*          MENU           *");
        System.out.println("* 1. Fill Array           *");
        System.out.println("* 2. Select Sort          *");
        System.out.println("* 3. Insert Sort          *");
        System.out.println("* 4. Quick Sort           *");
        System.out.println("* 5. Merge Sort           *");
        System.out.println("* 6. Quit                 *");
        System.out.println("* 7. Heap Sort            *");
        System.out.println("***************************");
    }
    
    public static void main(String [] args){
        int choice;
        int [] arr = new int[RANGE];
        
        Scanner input = new Scanner(System.in);
        do{
            displayMenu();
            System.out.println("Enter you choice: ");
            choice = input.nextInt();
            switch (choice) {
                case 1 -> // generate a new random filled array
                        fillArray(arr);
                case 2 -> // select sort
                        testSort(arr, "Select Sort");
                case 3 -> // insert sort
                        testSort(arr, "Insert Sort");
                case 4 -> // quick sort
                        testSort(arr, "Quick Sort");
                case 5 -> // merge sort
                        testSort(arr, "Merge Sort");
                case 6 -> // quit
                        System.out.println("Learn all of these!");
                case 7 -> // heap sort
                        testSort(arr, "Heap Sort");
                default -> {
                } // wrong choice

            }
            
        }while(choice != 6);
    }
   
}

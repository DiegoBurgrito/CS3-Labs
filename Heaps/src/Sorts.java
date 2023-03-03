
/**
 *
 * @author Your name
 */
public class Sorts {

    /**
     * Classical insertion sort algorithm that runs in 0(N^2) unless the array
     * is sorted in which the best case is O(N). Conceptually the algorithm is
     * similar to sorting a hand of cards as the dealer deals each card. Each
     * time you pick up a card you shift all cards over that are less than that
     * card then insert the new card in the appropriate location. Hence the name
     * insertion sort.
     *
     * @param arr of integers
     */
    public static void insertSort(int[] arr) {

        for (int i = 1; i < arr.length; i++) {
            int j = i;
            while (j > 0 && arr[j] < arr[j - 1]) {
                swap(arr, j, j - 1);
                j--;
            }
        }
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    /**
     * For each index, find the smallest element and perform one swap. Runs in
     * 0(N^2) for all cases but performs at most N-1 swaps.
     *
     * @param arr
     */
    public static void selectSort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            int min = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[min]) {
                    min = j;
                }
            }
            swap(arr, i, min);
        }
    }

    /**
     * Divide and Conquer algorithm that picks a pivot and moves all elements
     * that are less than the pivot to the left side and similarly all elements
     * larger than the pivot to the right. Repeat the process on both sides. The
     * algorithm is linearithmic O(nlogn) but can degenerate if a poorly chosen
     * pivot is selected. Choose a randomized pivot to help prevent this. The 
     * algorithm is quite fast as the name suggests, takes advantage of cache hits 
     * but it is not stable(no impact on ints).
     *
     * @param arr
     */
    public static void quickSort(int[] arr) {
        quickSortRecursive(arr, 0, arr.length - 1);
    }

    private static void quickSortRecursive(int[] arr, int left, int right) {
        if (left < right) {
            int pivot = pivot(arr, left, right);
            quickSortRecursive(arr, left, pivot - 1);
            quickSortRecursive(arr, pivot + 1, right);
        }
    }

    private static int pivot(int[] arr, int left, int right) {
        int pivot = arr[right];
        int i = left - 1;
        for (int j = left; j < right; j++) {
            if (arr[j] < pivot) {
                i++;
                swap(arr, i, j);
            }
        }
        swap(arr, i + 1, right);
        return i + 1;
    }

    /**
     * Divide and conquer algorithm that repeatedly divides the array into
     * halves then merges the halves together. The algorithm is stable and runs
     * in O(nlogn) in all cases. It's an out of place algorithm since a temporary
     * array is used in the merge method.
     *
     * @param arr
     */
    public static void mergeSort(int[] arr) {
        mergeSortRecursive(arr, 0, arr.length - 1);
    }

    private static void mergeSortRecursive(int[] arr, int begin, int end) {
        if (begin < end) {
            int middle = (begin + end) / 2;
            mergeSortRecursive(arr, begin, middle);
            mergeSortRecursive(arr, middle + 1, end);
            merge(arr, begin, middle, end);
        }
    }

    // merge two portions that are sorted respectively by creating an auxilliary
    // array as a place holder then copy it back
    private static void merge(int[] arr, int start, int middle, int end) {
        int[] aux = new int[end - start + 1];
        int i = start;
        int j = middle + 1;
        int k = 0;
        while (i <= middle && j <= end) {
            if (arr[i] < arr[j]) {
                aux[k] = arr[i];
                i++;
            } else {
                aux[k] = arr[j];
                j++;
            }
            k++;
        }
        while (i <= middle) {
            aux[k] = arr[i];
            i++;
            k++;
        }
        while (j <= end) {
            aux[k] = arr[j];
            j++;
            k++;
        }
        for (int l = 0; l < aux.length; l++) {
            arr[start + l] = aux[l];
        }
    }


    public static void heapSort(int[] arr) {
        int n = arr.length;

        for (int i = n / 2 - 1; i >= 0; i--)
            heapify(arr, i, n);

        for (int i = n - 1; i >= 0; i--) {
            swap(arr, 0, i);

            heapify(arr, 0, i);
        }
    }

    private static void heapify(int[] arr, int i, int n) {
        int smallest = i; // Initialize smallest as root
        int left = 2 * i + 1; // left = 2*i + 1
        int right = 2 * i + 2; // right = 2*i + 2

        // If left child is smaller than root
        if (left < n && arr[left] < arr[smallest])
            smallest = left;

        // If right child is larger than smaller so far
        if (right < n && arr[right] < arr[smallest])
            smallest = right;

        // If smallest is not root
        if (smallest != i) {
            swap(arr, i, smallest);
            heapify(arr, smallest, n);
        }
    }


}

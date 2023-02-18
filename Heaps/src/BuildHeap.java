import java.io.*;
import java.util.*;

public class BuildHeap {
    private int[] data;
    private List<Swap> swaps;
    private FastScanner in;
    private PrintWriter out;

    public static void main(String[] args) throws IOException {
        new BuildHeap().solve();
    }

    private void readData() throws IOException {
        int n = in.nextInt();
        data = new int[n];
        for (int i = 0; i < n; ++i) {
            data[i] = in.nextInt();
        }
    }

    private void writeResponse() {
        out.println(swaps.size());
        for (Swap swap : swaps) {
            out.println(swap.index1 + " " + swap.index2);
        }
    }

    private void swap(int loc1, int loc2) {
        int temp = data[loc1];
        data[loc1] = data[loc2];
        data[loc2] = temp;
    }

    private void generateSwaps() {
        swaps = new ArrayList<>();
        for (int i = data.length - 1; i >= 0 ; i--) {
            heapify(data, i);
        }
    }

    private void heapify(int[] arr, int i) {
        int smallest = i; // Initialize smallest as root
        int left = 2 * i + 1; // left = 2*i + 1
        int right = 2 * i + 2; // right = 2*i + 2

        // If left child is smaller than root
        if (left < data.length && arr[left] < arr[smallest])
            smallest = left;

        // If right child is larger than smaller so far
        if (right < data.length && arr[right] < arr[smallest])
            smallest = right;

        // If smallest is not root
        if (smallest != i) {
            swaps.add(new Swap(i, smallest));
            swap(i, smallest);
            heapify(arr, smallest);
        }
    }

    /* A utility function to print array of size n */
    static void printArray(int arr[]) {
        for (int j : arr) System.out.print(j + " ");
        System.out.println();
    }

    public void solve() throws IOException {
        in = new FastScanner();
        out = new PrintWriter(new BufferedOutputStream(System.out));
        readData();
        generateSwaps();
        writeResponse();
        out.close();
    }


    static class Swap {
        int index1;
        int index2;

        public Swap(int index1, int index2) {
            this.index1 = index1;
            this.index2 = index2;
        }
    }

    static class FastScanner {
        private BufferedReader reader;
        private StringTokenizer tokenizer;

        public FastScanner() {
            reader = new BufferedReader(new InputStreamReader(System.in));
            tokenizer = null;
        }

        public String next() throws IOException {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                tokenizer = new StringTokenizer(reader.readLine());
            }
            return tokenizer.nextToken();
        }

        public int nextInt() throws IOException {
            return Integer.parseInt(next());
        }
    }
}






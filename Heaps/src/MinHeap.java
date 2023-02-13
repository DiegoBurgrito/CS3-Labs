import java.util.ArrayList;
import java.util.Arrays;

public class MinHeap {
    private int[] data;
    private int size;

    private ArrayList<BuildHeap.Swap> swaps;

    public MinHeap (int capacity) {
        this.data = new int[capacity];
        swaps = new ArrayList<>();
        size = 0;
    }


    public void add(int val) {
        if (size == data.length) {
            data = Arrays.copyOf(data, data.length * 2);
        }
        data[size] = val;
        swapUp(size);
        size++;
    }

    private void swapUp(int bot) {
        while (bot > 0) {
            int parent = (bot - 1) / 2;
            if (data[parent] > data[bot]) {
                swap(parent, bot);
                bot = parent;
            } else {
                break;
            }
        }
    }

    private void swap(int loc1, int loc2) {

        int temp = data[loc1];
        data[loc1] = data[loc2];
        data[loc2] = temp;
    }

}

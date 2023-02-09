import java.awt.image.AreaAveragingScaleFilter;
import java.util.Arrays;

import static java.lang.System.out;


public class Heap {

    private int[] data;
    private int size;
    private int remove;

    public Heap() {
        this(10);
    }

    public Heap(int capacity) {
        data = new int[capacity];
        size = 0;
    }

    public void add(int value) {
        // 1) add (resize if full)
        if(size == data .length) {
           doubleData();
        } else {   // 2) swap up
            data[size] = value;
            swapUp(size);
            size++;
        }

    }

    private void swapUp(int bot) {
        while(bot > 0) {
            int parent = (bot - 1) / 2;
            if (data[parent] < data[bot]){
                swap(parent, bot);
                bot = parent;
            } else {
                return;
            }
        }
    }

    public void remove() {
        data[0] = data[size - 1];
        size--;
        swapDown(0, size - 1);
    }

    private void swapDown(int start, int stop) {
        while (start < stop) {
            int max = data[start];
            int left = start * 2 + 1;
            int right = start * 2 + 2;

            if(left < data.length) {
                if (right < data.length) {
                    if(data[left]> data[right]) {
                        max = left;
                    } else {
                        max = right;
                    }
                } else {
                    max = left;
                }
            } else {
                break;
            }

            if (data[max] > data[start]) {
                swap(start, max);
                start = max;
            } else {
                break;
            }

        }
    }

    // simple helper method that swaps values at indices loc1 and loc2
    private void swap(int loc1, int loc2) {
        int temp = data[loc1];
        data[loc1] = data[loc2];
        data[loc2] = temp;
    }

    private void doubleData() {
        data = Arrays.copyOf(data, data.length * 2);
    }

    // part 2
    public void print() {
        out.println("\n\nPRINTING THE HEAP!\n\n");
        for (int i = 0; i < size; i ++) {
            out.print(data[i] + " ");
        }
        out.println();
    }

    @Override
    public String toString() {
        return "";
    }

    public static void main(String... a) {
        Heap heap = new Heap();

        // test add and remove here
        
        
        
        // uncomment to test in part2 
        // should print like a tree

        heap.add(1);
        heap.add(2);
        heap.add(8);
        heap.add(9);
        heap.add(10);
        heap.add(7);
        heap.add(75);
        heap.add(17);
        heap.add(5);

        heap.print();
        heap.remove();
        heap.print();
        heap.remove();
        heap.print();
        heap.remove();
        heap.print();
        heap.remove();
        heap.print();
        heap.remove();
        heap.print();
        heap.remove();
        heap.print();
        heap.remove();

        heap.print();
        heap.add(25);
        heap.print();
        heap.add(35);
        heap.print();
        heap.remove();
        heap.print();
    }
}

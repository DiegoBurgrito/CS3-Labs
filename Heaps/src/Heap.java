import java.awt.image.AreaAveragingScaleFilter;
import java.util.Arrays;

import static java.lang.System.out;

public class Heap {
    public int[] data;
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
        if (size == data.length) {
            doubleData();
        }
        data[size] = value;
        swapUp(size);
        size++;
    }

    private void swapUp(int bot) {
        while (bot > 0) {
            int parent = (bot - 1) / 2;
            if (data[parent] < data[bot]) {
                swap(parent, bot);
                bot = parent;
            } else {
                break;
            }
        }
    }

    public void remove() {
        data[0] = data[size - 1];
        size--;
        swapDown(0, size - 1);
    }

    private void swapDown(int start, int stop) {
        int max;
        while (start < stop) {
            int left = start * 2 + 1;
            int right = start * 2 + 2;
            if (left <= size) {
                if (right <= size) {
                    if (data[left] > data[right]) {
                        max = left;
                    } else {
                        max = right;
                    }
                } else {
                    max = left;
                }
            } else {
                return;
            }
            if (data[max] > data[start]) {
                swap(start, max);
                start = max;
            } else {
                return;
            }
        }
    }

    private void swap(int loc1, int loc2) {
        int temp = data[loc1];
        data[loc1] = data[loc2];
        data[loc2] = temp;
    }

    private void doubleData() {
        data = Arrays.copyOf(data, data.length * 2);
    }

    public void print() {
        out.println("\n\nPRINTING THE HEAP!\n\n");
        for (int i = 0; i < size; i++) {
            out.print(data[i] + " ");
        }
        out.println();
    }

    @Override
    public String toString() {
        String out = "[";
        for (int i = 0; i < size; i++) {
            out += (data[i] + ", ");
        }
        return out.substring(0, out.length() - 2) + "]";
    }

    public static void main(String... a) {
        Heap heap = new Heap();
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
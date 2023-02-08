import static java.lang.System.out;


public class Heap {

    private int[] data;
    private int size;

    public Heap() {
        this(10);
    }

    public Heap(int capacity) {
        data = new int[capacity];
    }

    public void add(int value) {
        // todo
        // 1) add (resize if full)
        // 2) swap up
    }

    private void swapUp(int bot) {
        //todo
    }

    public void remove() {
        //todo
    }

    private void swapDown(int start, int stop) {
        //todo
    }

    // simple helper method that swaps values at indices loc1 and loc2
    private void swap(int loc1, int loc2) {

    }

    private void doubleData() {

    }

    // part 2
    public void print() {
        out.println("\n\nPRINTING THE HEAP!\n\n");
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
        /*
        
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
        */
    }
}

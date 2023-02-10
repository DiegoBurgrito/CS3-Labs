import java.util.PriorityQueue;
import org.junit.Test;
import static org.junit.Assert.*;

public class HeapTest {

    /**
     * Test of add method, of class Heap.
     */
    @Test
    public void testAdd() {
        int value = 5;
        Heap instance = new Heap();
        instance.add(value);
        assertEquals(value, instance.data[0]);
    }

    @Test
    public void testAdd2() {
        int value = 5;
        Heap instance = new Heap();
        instance.add(value);
        instance.add(value + 7);
        assertArrayEquals(new int[]{12, 5, 0, 0, 0, 0, 0, 0, 0, 0}, instance.data);
    }

    @Test
    public void testAdd3() {
        Heap heap = new Heap();
        heap.add(1);
        heap.add(2);
        heap.add(3);
        heap.add(4);
        heap.add(5);
        heap.add(6);
        assertArrayEquals(new int[]{6, 4, 5, 1, 3, 2, 0, 0, 0, 0}, heap.data);
    }

    @Test
    public void testAdd4() {
        Heap heap = new Heap();
        for (int i = 0; i < 100; i += 3) {
            heap.add(i);
        }
        int[] results = {99, 96, 87, 93, 60, 72, 84, 90, 45, 51, 57, 39, 69, 75, 81, 48, 63, 9, 42, 6, 24, 21, 54, 3, 30, 15, 66, 12, 36, 33, 78, 0, 27, 18, 0, 0, 0, 0, 0, 0};
        //System.out.println(Arrays.toString(heap.data));
        assertArrayEquals(results, heap.data);
    }

    @Test
    public void testAdd5() {
        Heap heap = new Heap();
        PriorityQueue<Integer> pq = new PriorityQueue<>(10, (Integer t, Integer t1) -> -Integer.compare(t, t1));
        for (int i = 0; i < 100; i += 3) {
            for (int j = 5 + i * 2; j < 500; j++) {
                heap.add(i);
                pq.add(i);
            }
        }
        int[] results = new int[20480];
        int i = 0;
        for (Object val : pq.toArray()) {
            results[i++] = (Integer) val;
        }
        assertArrayEquals(results, heap.data);
    }

    /**
     * Test of remove method, of class Heap.
     */
    @Test
    public void testRemove() {
        Heap heap = new Heap();
        PriorityQueue<Integer> pq = new PriorityQueue<>(10, (Integer t, Integer t1) -> -Integer.compare(t, t1));
        for (int i = 9; i > 0; i--) {

            heap.add(i);
            pq.add(i);

        }
        heap.remove();
        pq.remove();

        int i = 0;
        for (Object val : pq.toArray()) {
            assertTrue(heap.data[i++] == (Integer) val);
        }
    }

    @Test
    public void testRemove2() {
        Heap heap = new Heap(100);
        PriorityQueue<Integer> pq = new PriorityQueue<>(100, (Integer t, Integer t1) -> -Integer.compare(t, t1));
        for (int i = 50; i > 0; i--) {

            heap.add(i);
            pq.add(i);

        }
        for (int i = 0; i < 20; i++) {
            heap.remove();
            pq.remove();
        }

        int i = 0;
        for (Object val : pq.toArray()) {
            assertTrue(heap.data[i++] == (Integer) val);
        }
    }

    /**
     * Test of toString method, of class Heap.
     */
    @Test
    public void testToString() {
        Heap heap = new Heap();
        PriorityQueue<Integer> pq = new PriorityQueue<>(10, (Integer t, Integer t1) -> -Integer.compare(t, t1));
        for (int i = 9; i > 0; i--) {
            heap.add(i);
            pq.add(i);

        }
        assertEquals(pq.toString(), heap.toString());
    }
    
 @Test
    public void testToString2() {
        Heap heap = new Heap(149);
        PriorityQueue<Integer> pq = new PriorityQueue<>(149, (Integer t, Integer t1) -> -Integer.compare(t, t1));
        for (int i = 500; i > 0; i--) {
            heap.add(i);
            pq.add(i);

        }
        assertEquals(pq.toString(), heap.toString());
    }
}

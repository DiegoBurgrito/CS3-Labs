import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BinarySearchTreeTest {
    BinarySearchTree test = new BinarySearchTree();
    void add() {
        test.add(90);
        test.add(80);
        test.add(100);
        test.add(70);
        test.add(85);
        test.add(98);
        test.add(120);
    }

    @Test
    void inOrder() {
        add();
        test.inOrder();
        assertEquals("70 80 85 90 98 100 120 ", test.toString());
    }

    @Test
    void preOrder() {
        add();
        test.preOrder();
        assertEquals("90 80 70 85 100 98 120 ", test.toString());
    }
}
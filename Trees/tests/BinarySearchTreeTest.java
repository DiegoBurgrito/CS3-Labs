import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class BinarySearchTreeTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;

    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
        add();
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
        System.setErr(originalErr);
    }
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
        test.inOrder();
        assertEquals("70 80 85 90 98 100 120 \n", outContent.toString());
    }

    @Test
    void preOrder() {
        test.preOrder();
        assertEquals("90 80 70 85 100 98 120 \n", outContent.toString());
    }

    @Test
    void postOrder() {
        test.postOrder();
        assertEquals("70 85 80 98 120 100 90 \n", outContent.toString());
    }

    @Test
    void revOrder() {
        test.revOrder();
        assertEquals("120 100 98 90 85 80 70 \n", outContent.toString());
    }

    @Test
    void levelOrder() {
        test.levelOrder();
        assertEquals("90 80 100 70 85 98 120 \n", outContent.toString());
    }

    @Test
    void zigzagOrder() {
        test.zigzagOrder();
        assertEquals("90 100 80 70 85 98 120 \n", outContent.toString());
    }

    @Test
    void getNumLeaves() {
        assertEquals(4, test.getNumLeaves());
    }

    @Test
    void getNumNodes() {
        assertEquals(7, test.getNumNodes());
    }

    @Test
    void getHeight(){
        assertEquals(2, test.getHeight());
    }

    @Test
    void getWidth(){
        assertEquals(5, test.getWidth());
    }

    @Test
    void contains() {
        assertTrue(test.contains(70));
        assertTrue(test.contains(80));
        assertTrue(test.contains(85));
        assertTrue(test.contains(90));
        assertTrue(test.contains(98));
        assertTrue(test.contains(100));
        assertTrue(test.contains(120));
        assertFalse(test.contains(400));
    }

    @Test
    void getSmallest() {
        assertEquals(70, test.getSmallest());
    }

    @Test
    void getLargest() {
        assertEquals(120, test.getLargest());
    }

    @Test
    void getNumLevels() {
        assertEquals(3, test.getNumLevels());
    }


}
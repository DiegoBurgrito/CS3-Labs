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
}
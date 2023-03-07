import static org.junit.Assert.assertEquals;

import java.io.*;
import java.util.*;

import org.junit.*;
import org.junit.runners.Parameterized;

public class BFSTests extends TestsAbstract {
    public BFSTests(String in, GeographicPoint start, GeographicPoint end) {
        super(in, start, end);
    }

    @Parameterized.Parameters(name = "{0}")
    public static Collection<Object[]> input() {
        return Arrays.asList(new Object[][] {
                { "map11.txt", new GeographicPoint(0, 0), new GeographicPoint(6, 6) },
                { "map22.txt", new GeographicPoint(6, 6), new GeographicPoint(0, 0) },
                { "map33.txt", new GeographicPoint(0, 0), new GeographicPoint(1, 2) },
                { "ucsd1.map", new GeographicPoint(32.8756538, -117.2435715), new GeographicPoint(32.8742087, -117.2381344) },
        });
    }

    @Before
    public void setUp() throws FileNotFoundException {
        super.setUp();
        
        correct = new CorrectAnswer(input + ".answer", true);

        studentPath = studentImpl.bfs(start, end);
    }

    @Test
    public void testVertexCount() {
        assertEquals(correct.vertices, studentImpl.getNumVertices());
    }

    @Test
    public void testEdgeCount() {
        assertEquals(correct.edges, studentImpl.getNumEdges());
    }
}

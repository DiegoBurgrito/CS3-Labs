import java.io.*;
import java.util.*;

import org.junit.*;
import org.junit.runners.Parameterized;

/**
 * This subclass of {@code GraphAdjTestsAbstract} implements text file inputs for the tests implemented
 * in {@code GraphAdjTestsAbstract}. The 
 */
public final class GraphAdjTestsTxt extends GraphAdjTestsAbstract<String> {
    // workingDir is the directory where the input files are located note that the path is relative to the package
    // so if you are running the tests from the package, the path will be correct
    // ONLY USE FOR VSCODE JUNIT CASES
    final String workingDir = Graph.class.getResource("").getPath()  + ".." + File.separator;

    public GraphAdjTestsTxt(String in) {
        super(in);
    }

    @Parameterized.Parameters(name = "{0}")
    public static Collection<String[]> input() {
        return Arrays.asList(new String[][] {
                { "graph1.txt" },
                { "graph2.txt" },
                { "graph3.txt" },
                { "graph4.txt" },
                { "graph5.txt" },
                { "graph6.txt" }
        });
    }

    @Before
    public void setUp() throws FileNotFoundException {
        super.setUp();
        Scanner sc = new Scanner(new File(input));
        int size = sc.nextInt();

        for (int i = 0; i<size; i++) {
            actualList.addVertex();
            actualMatrix.addVertex();
            listTester.addVertex();
            matrixTester.addVertex();
        }

        while (sc.hasNextInt()) {
            int v = sc.nextInt();
            int n = sc.nextInt();
            actualList.addEdge(v, n);
            actualMatrix.addEdge(v, n);
            listTester.addEdge(v, n);
            matrixTester.addEdge(v, n);
        }
    }
}

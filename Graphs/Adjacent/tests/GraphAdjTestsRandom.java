import java.io.*;
import java.util.*;

import org.junit.Before;
import org.junit.runners.Parameterized;

/** 
 * This subclass of {@code GraphAdjTestsAbstract} implements random graph inputs for the tests implemented
 * in {@code GraphAdjTestsAbstract}. The input is created by {@code RandomGraph} class which represent a random graph with
 * {@code numVertices} vertices and {@code numEdges} edges.
 */
public final class GraphAdjTestsRandom extends GraphAdjTestsAbstract<RandomGraph> {
        public GraphAdjTestsRandom(RandomGraph in) {
            super(in);
        }

        @Parameterized.Parameters(name = "{0}")
        public static Collection<RandomGraph[]> input() {
            return Arrays.asList(new RandomGraph[][] {
                    { new RandomGraph(10, 20) },
                    { new RandomGraph(20, 40) },
                    { new RandomGraph(30, 60) },
                    { new RandomGraph(40, 80) },
                    { new RandomGraph(50, 100) },
                    { new RandomGraph(60, 120) }
            });
        }
    
        @Before
        public void setUp() throws FileNotFoundException {
            super.setUp();

            for (int i = 0; i < input.numVertices; i++) {
                actualList.addVertex();
                actualMatrix.addVertex();
                listTester.addVertex();
                matrixTester.addVertex();
            }

            for (Pair edge : input.edges) {
                actualList.addEdge(edge.v, edge.n);
                actualMatrix.addEdge(edge.v, edge.n);
                listTester.addEdge(edge.v, edge.n);
                matrixTester.addEdge(edge.v, edge.n);
            }
        }  
}

/**
 * A random graph with {@code numVertices} vertices and {@code numEdges} edges passed to the constructor.
 */
class RandomGraph {
    int numVertices;
    List<Pair> edges;

    /**
     * Creates a random graph with {@code numVertices} vertices 0, 1, 2, ..., {@code numVertices} - 1 
     * and random {@code numEdges} edges between these vertices.
     * @param numVertices
     * @param numEdges
     */
    public RandomGraph(int numVertices, int numEdges) {
        edges = new ArrayList<>();
        this.numVertices = numVertices;
        for (int i = 0; i < numEdges; i++) {
            int v = (int) (Math.random() * numVertices);
            int n = (int) (Math.random() * numVertices);
            edges.add(new Pair(v, n));
        }
    }

    @Override
    public String toString() {
        return "RandomGraph [numVertices=" + numVertices + ", numEdges=" + edges.size() + "]";
    }
}

/**
 * A pair of vertices in a graph.
 */
class Pair {
    int v;
    int n;

    public Pair(int v, int n) {
        this.v = v;
        this.n = n;
    }

    @Override
    public String toString() {
        return "(" + v + ", " + n + ")";
    }
}
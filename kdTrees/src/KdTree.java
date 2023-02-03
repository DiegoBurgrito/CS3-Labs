
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdDraw;
import java.util.ArrayList;
import java.util.HashSet;

public class KdTree {
    private Node root;

    public KdTree() {
        root = null;
    }

    public boolean isEmpty() {                      // is the set empty? 
        return root == null;
    }

    public int size() {                      // number of points in the set
        return size(root);
    }

    private int size(Node node) {
        if(node == null) return 0;
        return 1 + size(node.left) + size(node.left);
    }

    /**
     * inserts a new node if not in the treeSet already the rectangles will encompass the whole area
     * and the point will lie inside
     *
     * @param p
     */
    public void insert(Point2D p) {
        if(root == null) {
            root = new Node(p);
        }
        if(!contains(p)) {
            root = insert(p, root, false);
        }
    }

    /**
     * helper method that inserts a node recursively and updates the Rectangle with it -
     * alternatively a Rectangle could be passed as a parameter to free up space in the node class
     *
     * @param p
     * @param node
     * @param vertical vertical is current root and true being vertical line
     * @return
     */
    private Node insert(Point2D p, Node node, boolean vertical) {
        if (node == null) {
            return new Node(p);
        }
        if (vertical) return p.y() < node.p.y() ? insert(p, node.left, false) : insert(p, node.right, false);
        return p.x() < node.p.x() ? insert(p, node.left, true) : insert(p, node.right, true);
    }

    /**
     * recursively ascertains if a point is in the tree
     *
     * @param p
     * @return
     */
    public boolean contains(Point2D p) {           // does the set contain point p?
        return contains(p.x(), p.y(), root,false);
    }

    /**
     * helper method to recursively determine if a point is in the tree by checking left or right
     * based on vertical == true for x points and vertical == false for y points
     *
     * @param x
     * @param y
     * @param node
     * @param vertical
     * @return
     */
    private boolean contains(double x, double y, Node node, boolean vertical) {
        if(node == null) return false;
        if(node.p.x() == x && node.p.y() == y) return true;
        if(vertical) {
            return y < node.p.y() ? contains(x, y, node.left, false) : contains(x, y, node.right, false);
        }
        return x < node.p.x() ? contains(x, y, node.left, true) : contains(x, y, node.right, true);
    }

    /**
     * draws all points to standard draw
     */
    public void draw() {
        StdDraw.setPenRadius(0.01);
        StdDraw.setPenColor(StdDraw.BLACK);

    }

    /**
     * method recursively draws nodes and red vertical lines and blue horizontal lines based on
     * vertical
     *
     * @param node
     * @param vertical - true draws vertical red line inside node's rectangle and blue horizontal
     * line inside node's rectangle otherwise
     */
    private void draw(Node node, boolean vertical) {

    }

    /**
     * returns a data structure of all points that are inside the rectangle
     *
     * @param rect
     * @return
     */
    public Iterable<Point2D> range(RectHV rect) {

        return null;
    }

    /**
     * helper method to add points that are inside the given rectangle
     *
     * @param rect
     * @param node
     * @param list
     */
    private void range(RectHV rect, Node node, ArrayList<Point2D> list) {

    }

    /**
     * a nearest neighbor in the set to point p; null if the set is empty
     *
     * @param p
     * @return
     */
    public Point2D nearest(Point2D p) {

        return null;
    }

    /**
     * helper method to recursively calculate closest point in tree to p with pruning
     *
     * @param p
     * @param node
     * @param vertical
     */
    private void nearest(Point2D p, Node node, boolean vertical) {

    }

    /**
     * throws an exception if a null reference is passed
     *
     * @param o
     */
    private void checkIfNull(Object o) {
        if (o == null) {
            throw new java.lang.NullPointerException();
        }
    }

    public static void main(String[] args) {                // unit testing of the methods (optional) 
        String filename = "circle100.txt"; // args[0];
        In in = new In(filename);

        StdDraw.enableDoubleBuffering();

        // initialize the data structures with N points from standard input
        // PointSET brute = new PointSET();
        KdTree kdtree = new KdTree();
        while (!in.isEmpty()) {
            double x = in.readDouble();
            double y = in.readDouble();
            Point2D p = new Point2D(x, y);
            // kdtree.insert(p);
            kdtree.insert(p);
        }
        kdtree.draw();
        StdDraw.show();
    }

    /**
     * inner class that provides the functionality of a Node with references left/right, a point and
     * a RectHV object that is optional
     */
    private class Node {

        Node left, right;
        Point2D p;
        RectHV rect;

        public Node(Point2D p) {
            this.p = p;
        }
    }
}

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdDraw;

import java.util.ArrayList;

public class KdTree {
    private Node root;

    public KdTree() {
        root = null;
    }

    public static void main(String[] args) {                // unit testing of the methods (optional)
        String filename = "circle10.txt"; // args[0];
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

    public boolean isEmpty() {                      // is the set empty?
        return root == null;
    }

    public int size() {                      // number of points in the set
        return size(root);
    }

    private int size(Node node) {
        return node == null ? 0 : 1 + size(node.left) + size(node.left);
    }

    /**
     * inserts a new node if not in the treeSet already the rectangles will encompass the whole area
     * and the point will lie inside
     *
     * @param p
     */
    public void insert(Point2D p) {
        if (root == null) {
            root = new Node(p);
            root.rect = new RectHV(0, 0, 1, 1);
        }
        if (!contains(p)) {
            insert(p, root, true);
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
        if (vertical) {
            if (p.x() < node.p.x()) {
                node.left = insert(p, node.left, false);
                node.left.rect = new RectHV(node.rect.xmin(), node.rect.ymin(), node.p.x(), node.rect.ymax());
            } else {
                node.right = insert(p, node.right, false);
                node.right.rect = new RectHV(node.p.x(), node.rect.ymin(), node.rect.xmax(), node.rect.ymax());
            }
        } else {
            if (p.y() < node.p.y()) {
                node.left = insert(p, node.left, true);
                node.left.rect = new RectHV(node.rect.xmin(), node.rect.ymin(), node.rect.xmax(), node.p.y());
            } else {
                node.right = insert(p, node.right, true);
                node.right.rect = new RectHV(node.rect.xmin(), node.p.y(), node.rect.xmax(), node.rect.ymax());
            }
        }
        return node;
    }

    /**
     * recursively ascertains if a point is in the tree
     *
     * @param p
     * @return
     */
    public boolean contains(Point2D p) {           // does the set contain point p?
        return contains(p.x(), p.y(), root, true);
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
        if (node == null) return false;
        if (node.p.x() == x && node.p.y() == y) return true;
        if (vertical) {
            return x < node.p.x() ? contains(x, y, node.left, false) : contains(x, y, node.right, false);
        }
        return y < node.p.y() ? contains(x, y, node.left, true) : contains(x, y, node.right, true);
    }

    /**
     * draws all points to standard draw
     */

    public void draw() {
        draw(root, true);
    }

    /**
     * method recursively draws nodes and red vertical lines and blue horizontal lines based on
     * vertical
     *
     * @param node
     * @param vertical - true draws vertical red line inside node's rectangle and blue horizontal
     *                 line inside node's rectangle otherwise
     */
    private void draw(Node node, boolean vertical) {
        if (node == null) return;

        StdDraw.setPenRadius(0.01);
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.point(node.p.x(), node.p.y());

        StdDraw.setPenRadius(0.001);
        if (vertical) {
            StdDraw.setPenColor(StdDraw.RED);
            StdDraw.line(node.p.x(), node.rect.ymin(), node.p.x(), node.rect.ymax());
            draw(node.right, false);
            draw(node.left, false);

        } else {
            StdDraw.setPenColor(StdDraw.BLUE);
            StdDraw.line(node.rect.xmin(), node.p.y(), node.rect.xmax(), node.p.y());
            draw(node.right, true);
            draw(node.left, true);
        }

    }

    /**
     * returns a data structure of all points that are inside the rectangle
     *
     * @param rect
     * @return
     */
    public Iterable<Point2D> range(RectHV rect) {
        return isEmpty() ? null : range(rect, root, new ArrayList<Point2D>());
    }

    /**
     * helper method to add points that are inside the given rectangle
     *
     * @param rect
     * @param node
     * @param list
     */
    private Iterable<Point2D> range(RectHV rect, Node node, ArrayList<Point2D> list) {
        if (rect.contains(node.p)) {
            list.add(node.p);
        }
        if (node.right != null && node.right.rect.intersects(rect)) {
            range(rect, node.right, list);
        }
        if (node.left != null && node.left.rect.intersects(rect)) {
            range(rect, node.left, list);
        }
        return list;
    }

    /**
     * a nearest neighbor in the set to point p; null if the set is empty
     *
     * @param p
     * @return
     */
    public Point2D nearest(Point2D p) {
        if (isEmpty()) {
            return null;
        }
        return nearest(p, root, true);
    }

    /**
     * helper method to recursively calculate closest point in tree to p with pruning
     *
     * @param p
     * @param node
     * @param vertical
     */
    private Point2D nearest(Point2D p, Node node, boolean vertical) {
        if (node == null) {
            return null;
        }
        Point2D closest = node.p;
        if (vertical) {
            if (p.x() < node.p.x()) {
                //recurse left tree first then right tree
                closest = searchTrees(p, closest, node.left, node.right, false);
            } else {
                //recurse right tree first then left tree
                closest = searchTrees(p, closest, node.right, node.left, true);
            }
        } else {
            if (p.y() < node.p.y()) {
                //recurse left tree first then right tree
                closest = searchTrees(p, closest, node.left, node.right, false);
            } else {
                // recurse right tree first then left tree
                closest = searchTrees(p, closest, node.right, node.left, true);
            }
        }
        return closest;
    }

    private Point2D searchTrees(Point2D p, Point2D closest, Node firstNode, Node secondNode, boolean vertical) {
        Point2D tempPoint = nearest(p, firstNode, vertical);
        if (tempPoint != null && p.distanceSquaredTo(tempPoint) < p.distanceSquaredTo(closest)) {
            closest = tempPoint;
        }
        if (secondNode != null && secondNode.rect.distanceSquaredTo(p) < p.distanceSquaredTo(closest)) {
            Point2D right = nearest(p, secondNode, vertical);
            if (right != null && p.distanceSquaredTo(right) < p.distanceSquaredTo(closest)) {
                closest = right;
            }
        }
        return closest;
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

    /**
     * inner class that provides the functionality of a Node with references left/right, a point and
     * a RectHV object that is optional
     */
    private class Node {

        Node left;
        Node right;
        Point2D p;
        RectHV rect;

        public Node(Point2D p) {
            this.p = p;
        }
    }
}

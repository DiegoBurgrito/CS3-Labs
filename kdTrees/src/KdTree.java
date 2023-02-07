import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdDraw;

import java.util.ArrayList;

public class KdTree {
    private Node root;
    private int size;

    public KdTree() {
        size = 0;
        root = null;
    }

    public static void main(String[] args) {
        String filename = "input10.txt";
        In in = new In(filename);

        StdDraw.enableDoubleBuffering();

        KdTree kdtree = new KdTree();
        while (!in.isEmpty()) {
            double x = in.readDouble();
            double y = in.readDouble();
            Point2D p = new Point2D(x, y);

            kdtree.insert(p);
        }
        kdtree.draw();
        StdDraw.show();
        System.out.println(kdtree.nearest(new Point2D(.34, .766)));
    }

    public boolean isEmpty() {
        return root == null;
    }

    public int size() {
        return size;
    }


    public void insert(Point2D p) {
        checkIfNull(p);
        if (root == null) {
            size++;
            root = new Node(p);
            root.rect = new RectHV(0, 0, 1, 1);
        }
        else if (!contains(p)) {
            size++;
            insert(p, root, true, 0, 0, 1, 1);
        }
    }

    private Node insert(Point2D p, Node node, boolean vertical, double xMin, double yMin, double xMax, double yMax) {
        if (node == null) {
            return new Node(p, new RectHV(xMin, yMin, xMax, yMax));
        }
        if (vertical) {
            if (p.x() < node.p.x()) {
                node.left = insert(p, node.left, false, node.rect.xmin(), node.rect.ymin(), node.p.x(), node.rect.ymax());
            } else {
                node.right = insert(p, node.right, false, node.p.x(), node.rect.ymin(), node.rect.xmax(), node.rect.ymax());
            }
        } else {
            if (p.y() < node.p.y()) {
                node.left = insert(p, node.left, true, node.rect.xmin(), node.rect.ymin(), node.rect.xmax(), node.p.y());
            } else {
                node.right = insert(p, node.right, true, node.rect.xmin(), node.p.y(), node.rect.xmax(), node.rect.ymax());
            }
        }
        return node;
    }

    public boolean contains(Point2D p) {
        checkIfNull(p);
        return contains(p.x(), p.y(), root, true);
    }

    private boolean contains(double x, double y, Node node, boolean vertical) {
        if (node == null) return false;
        if (node.p.x() == x && node.p.y() == y) return true;
        if (vertical) {
            return x < node.p.x() ? contains(x, y, node.left, false) : contains(x, y, node.right, false);
        }
        return y < node.p.y() ? contains(x, y, node.left, true) : contains(x, y, node.right, true);
    }

    public void draw() {
        draw(root, true);
    }

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

    public Iterable<Point2D> range(RectHV rect) {
        checkIfNull(rect);
        return isEmpty() ? null : range(rect, root, new ArrayList<Point2D>());
    }

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


    public Point2D nearest(Point2D p) {
        checkIfNull(p);
        return isEmpty() ? null : nearest(p, root, true);
    }

    private Point2D nearest(Point2D p, Node node, boolean vertical) {
        if (node == null) {
            return null;
        }
        Point2D closest = node.p;
        if (vertical) {
            if (p.x() < node.p.x()) {
                // recurse left tree first then right tree
                closest = searchTrees(p, closest, node.left, node.right, false);
            } else {
                // recurse right tree first then left tree
                closest = searchTrees(p, closest, node.right, node.left, false);
            }
        } else {
            if (p.y() < node.p.y()) {
                // recurse left tree first then right tree
                closest = searchTrees(p, closest, node.left, node.right, true);
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


    private void checkIfNull(Object obj) {
        if (obj == null) {
            throw new IllegalArgumentException();
        }
    }

    private class Node {

        Node left;
        Node right;
        Point2D p;
        RectHV rect;


        public Node(Point2D p) {
            this.p = p;
        }

        public Node(Point2D p, RectHV rect) {
            this.p = p;
            this.rect = rect;
        }
    }
}

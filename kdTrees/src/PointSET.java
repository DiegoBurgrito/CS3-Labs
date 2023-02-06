import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdDraw;


import java.util.Set;
import java.util.TreeSet;

public class PointSET {

    private final Set<Point2D> set;

    public PointSET() {
        set = new TreeSet<>();
    }

    public static void main(String[] args) {
        String filename = "circle100.txt";
        In in = new In(filename);

        StdDraw.enableDoubleBuffering();

        PointSET brute = new PointSET();

        while (!in.isEmpty()) {
            double x = in.readDouble();
            double y = in.readDouble();
            Point2D p = new Point2D(x, y);

            brute.insert(p);
        }
        brute.draw();
        StdDraw.show();
    }

    public boolean isEmpty() {              // is the set empty?
        return set.isEmpty();
    }

    public int size() {                      // number of points in the set
        return set.size();
    }

    public void insert(Point2D p) {
        checkIfNull(p);
        set.add(p);
    }

    public boolean contains(Point2D p) {           // does the set contain point p?
        checkIfNull(p);
        return set.contains(p);
    }

    public void draw() {
        StdDraw.setPenRadius(0.01);
        StdDraw.setPenColor(StdDraw.BLACK);
        for (Point2D point : set) {
            StdDraw.point(point.x(), point.y());
        }
    }

    public Iterable<Point2D> range(RectHV rect) {
        checkIfNull(rect);
        Set<Point2D> range = new TreeSet<>();
        for (Point2D point : set) {
            if (rect.contains(point)) {
                range.add(point);
            }
        }
        return range;
    }

    public Point2D nearest(Point2D p) {
        checkIfNull(p);
        double min = Double.POSITIVE_INFINITY;
        if (set.isEmpty()) {
            return null;
        }
        Point2D nearest = null;
        for (Point2D point : set) {
            if (nearest == null || p.distanceSquaredTo(point) < min) {
                min = p.distanceSquaredTo(point);
                nearest = point;
            }
        }
        return nearest;
    }

    private void checkIfNull(Object obj) {
        if (obj == null) {
            throw new java.lang.IllegalArgumentException();
        }
    }
}


import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdDraw;

import java.util.*;

public class PointSET {

    private Set<Point2D> set;

    public PointSET() {                     // construct an empty set of points 
        set = new HashSet<>();
    }

    public boolean isEmpty() {              // is the set empty?
        return set.isEmpty();
    }

    public int size() {                      // number of points in the set 
        return set.size();
    }

    public void insert(Point2D p) {             // add the point to the set (if it is not already in the set)
        set.add(p);
    }

    public boolean contains(Point2D p) {           // does the set contain point p? 
        return set.contains(p);
    }

    public void draw() {                    // draw all points to standard draw with p.draw()
        StdDraw.setPenRadius(0.01);
        StdDraw.setPenColor(StdDraw.BLACK);
        for (Point2D point : set) {
            StdDraw.point(point.x(), point.y());
        }
    }

    public Iterable<Point2D> range(RectHV rect) {           // all points that are inside the rectangle
        Set<Point2D> range = new HashSet<>();
        for (Point2D point : set) {
            if(rect.contains(point)){
                range.add(point);
            }
        }
        return range;
    }

    public Point2D nearest(Point2D p) {           // a nearest neighbor in the set to point p; null if the set is empty
        double min = Double.MAX_VALUE;
        Point2D nearest = null;
        for (Point2D point : set) {
            if((point.y() - p.y()) / (point.x() - p.x()) < min) {
                min = (point.y() - p.y()) / (point.x() - p.x());
                nearest = point;
            }
        }
        return nearest;
    }

    private void checkIfNull(Object o) {
        if (o == null) {
            throw new java.lang.NullPointerException();
        }
    }

    public static void main(String[] args) {                // unit testing of the methods (optional) 
        String filename = "circle10.txt";   // args[0];
        In in = new In(filename);

        StdDraw.enableDoubleBuffering();

        // initialize the data structures with N points from standard input
        PointSET brute = new PointSET();
        // KdTree kdtree = new KdTree();
        while (!in.isEmpty()) {
            double x = in.readDouble();
            double y = in.readDouble();
            Point2D p = new Point2D(x, y);
            // kdtree.insert(p);
            brute.insert(p);
        }
        brute.draw();
        StdDraw.show();
    }
}

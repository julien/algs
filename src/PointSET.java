import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeSet;

public class PointSET {
    private TreeSet<Point2D> points;

	public PointSET() {
        points = new TreeSet<>();
    }

    public boolean isEmpty() {
        return points.isEmpty();
    }

    public int size() {
        return points.size();
    }

    public void insert(Point2D p) {
		if (p == null) throw new NullPointerException();

        if (!points.contains(p)) points.add(p);
    }

    public boolean contains(Point2D p) {
		if (p == null) throw new NullPointerException();

        return points.contains(p);
    }

    public void draw() {}

    public Iterable<Point2D> range(RectHV rect) {
		if (rect == null) throw new NullPointerException();

        Point2D min = new Point2D(rect.xmin(), rect.ymin());
        Point2D max = new Point2D(rect.xmax(), rect.ymax());
        List<Point2D> results = new LinkedList<>();

        for (Point2D p : points.subSet(min, true, max, true))
            if (p.x() >= rect.xmin() && p.x() <= rect.xmax())
                results.add(p);

        return results;
    }

    public Point2D nearest(Point2D p) {
		if (p == null) throw new NullPointerException();

        if (isEmpty()) return null;

        Point2D next = points.ceiling(p);
        Point2D prev = points.floor(p);
        if (next == null && prev == null) {
            return null;
        }

        double distNext = next == null ? Double.MAX_VALUE : p.distanceTo(next);
        double distPrev = prev == null ? Double.MAX_VALUE : p.distanceTo(prev);
        double d = Math.min(distNext, distPrev);

        Point2D min = new Point2D(p.x(), p.y() - d);
        Point2D max = new Point2D(p.x(), p.y() + d);
        Point2D nearest = next == null ? prev : next;

        for (Point2D candidate: points.subSet(min, true, max, true))
            if (p.distanceTo(candidate) < p.distanceTo(nearest))
                nearest = candidate;


        return nearest;
    }

    public static void main(String[] args) {}
}

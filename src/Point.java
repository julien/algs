import java.util.Comparator;
import edu.princeton.cs.algs4.StdDraw;

public class Point implements Comparable<Point> {

    private final int x;
    private final int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void draw() {
        StdDraw.point(x, y);
    }

    public void drawTo(Point that) {
        StdDraw.line(this.x, this.y, that.x, that.y);
    }

    public double slopeTo(Point that) {
        if (compareTo(that) == 0) return Double.NEGATIVE_INFINITY;

        if (this.y == that.y) return +0.0;
        if (this.x == that.x) return Double.POSITIVE_INFINITY;

        return (double)(that.y - this.y) / (that.x - this.x);
    }

    public int compareTo(Point that) {
        if (this.y > that.y) return 1;
        if (this.y < that.y) return -1;

        if (this.x > that.x) return 1;
        if (this.x < that.x) return -1;

        return 0;
    }

    public Comparator<Point> slopeOrder() {
        return (p1, p2) -> {
            double slopeWithP1 = slopeTo(p1);
            double slopeWithP2 = slopeTo(p2);
            if (slopeWithP1 > slopeWithP2) return 1;
            if (slopeWithP2 > slopeWithP1) return -1;
            return 0;
        };
    }

    public String toString() {
        return "(" + x + ", " + y + ")";
    }
}

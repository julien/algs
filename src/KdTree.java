import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import java.util.LinkedList;
import java.util.List;

public class KdTree {
    private enum Separator { VERTICAL, HORIZONTAL }
    private Node root;
    private int size;

    private static class Node {
        private final Separator sep;
        private final RectHV rect;
        private final Point2D p;
        private Node left;
        private Node right;

        Node(Point2D p, Separator sep, RectHV rect) {
            this.p = p;
            this.sep = sep;
            this.rect = rect;
        }

        public Separator nextSep() {
            return sep == Separator.VERTICAL ?
                    Separator.HORIZONTAL : Separator.VERTICAL;
        }

        public RectHV bottomLeft() {
            return sep == Separator.VERTICAL
                    ? new RectHV(rect.xmin(), rect.ymin(), p.x(), rect.ymax())
                    : new RectHV(rect.xmin(), rect.ymin(), rect.xmax(), p.y());
        }

        public RectHV topRight() {
            return sep == Separator.VERTICAL
                    ? new RectHV(p.x(), rect.ymin(), rect.xmax(), rect.ymax())
                    : new RectHV(rect.xmin(), p.y(), rect.xmax(), rect.ymax());
        }

        public boolean isInBounds(Point2D q) {
            return (sep == Separator.HORIZONTAL && p.y() > q.y())
                    || (sep == Separator.VERTICAL && p.x() > q.x());
        }
    }

    public KdTree() {
        root = null;
        size = 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void insert(Point2D p) {
        if (p == null) throw new NullPointerException();

        if (root == null) {
            root = new Node(p, Separator.VERTICAL, new RectHV(0, 0, 1, 1));
            size++;
            return;
        }

        Node prev = null;
        Node curr = root;
        do {
            if (curr.p.equals(p)) {
                return;
            }
            prev = curr;
            curr = curr.isInBounds(p) ? curr.left : curr.right;
        } while (curr != null);

        if (prev.isInBounds(p))
            prev.left = new Node(p, prev.nextSep(), prev.bottomLeft());
        else
            prev.right = new Node(p, prev.nextSep(), prev.topRight());
        size++;
    }

    public boolean contains(Point2D p) {
        if (p == null) throw new NullPointerException();

        Node node = root;
        while (node != null) {
            if (node.p.equals(p)) {
                return true;
            }
            node = node.isInBounds(p) ? node.left : node.right;
        }
        return false;
    }

    public void draw() {}

    public Iterable<Point2D> range(RectHV rect) {
        if (rect == null) throw new NullPointerException();
        List<Point2D> results = new LinkedList<>();
        addAll(root, rect, results);
        return results;
    }

    private void addAll(Node node, RectHV rect, List<Point2D> results) {
        if (node == null) {
            return;
        }
        if (rect.contains(node.p)) {
            results.add(node.p);
            addAll(node.left, rect, results);
            addAll(node.right, rect, results);
            return;
        }
        if (node.isInBounds(new Point2D(rect.xmin(), rect.ymin()))) {
            addAll(node.left, rect, results);
        }
        if (!node.isInBounds(new Point2D(rect.xmax(), rect.ymax()))) {
            addAll(node.right, rect, results);
        }
    }

    public Point2D nearest(Point2D p) {
        if (p == null) throw new NullPointerException();
        return isEmpty() ? null : nearest(p, root.p, root);
    }

    private Point2D nearest(Point2D target, Point2D closest, Node node) {
        if (node == null) return closest;

        double closestDist = closest.distanceTo(target);
        if (node.rect.distanceTo(target) < closestDist) {
            double nodeDist = node.p.distanceTo(target);
            if (nodeDist < closestDist)
                closest = node.p;

            if (node.isInBounds(target)) {
                closest = nearest(target, closest, node.left);
                closest = nearest(target, closest, node.right);
            } else {
                closest = nearest(target, closest, node.right);
                closest = nearest(target, closest, node.left);
            }
        }
        return closest;
    }

    public static void main(String[] args) {}
}
